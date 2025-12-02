/*
1. Key Generation
Choose two large prime numbers, say p and q. These prime numbers should be kept secret.
Calculate the product of primes, n = p * q. This product is part of the public as well as the private key.
Calculate Euler Totient FunctionΦ(n) as Φ(n) = Φ(p * q) = Φ(p) * Φ(q) = (p - 1) * (q - 1).
Choose encryption exponent e, such that
1 < e < Φ(n), and
gcd(e, Φ(n)) = 1, that is e should be co-prime with Φ(n).
Calculate decryption exponent d, such that
(d * e) ≡ 1 mod Φ(n), that is d is modular multiplicative inverse of e mod Φ(n). Some common methods to calculate multiplicative inverse are: Extended Euclidean Algorithm, Fermat's Little Theorem, etc.
We can have multiple values of d satisfying (d * e) ≡ 1 mod Φ(n) but it does not matter which value we choose as all of them are valid keys and will result into same message on decryption.
Finally, the Public Key = (n, e) and the Private Key = (n, d).

shoutout to:
https://www.geeksforgeeks.org/computer-networks/rsa-algorithm-cryptography/# 
for the instructions on RSA

https://github.com/bauripalash/bpp 
for the primeNum txt

*/
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>
#include <time.h>
#define SIZE 35
#define PRIMETOTAL 50847534 // amount of primes in the billion-primes.txt

typedef struct {
	long p;
	long q;
	long d;
	long long n;
} primeHold;

typedef struct{
	long long sharedX;
	long uniqueY;
       	long uniqueZ;	
} keyPair;

//Euler Toitent Function
int gcd(long a, long b){
        if(a == 0){
                return (int)b;
        }
        return gcd(b%a , a);
}

long etf(long long n){
        long result;
                for(long i = 2; i<n; i++){
                        if(gcd(i,n) == 1)
                                result++;
                }
        return result;
}


// C function for extended Euclidean Algorithm
long gcdExtended(long a, long b, long* x, long* y);

// Function to find modulo inverse of a
long modInverse(long A, long M)
{
    long x, y;
    long g = gcdExtended(A, M, &x, &y);
    if (g != 1)
        printf("Inverse doesn't exist");
    else {
        // m is added to handle negative x
        long res = (x % M + M) % M;
        return res;
    }
    return -1;
}

// C function for extended Euclidean Algorithm
long gcdExtended(long a, long b, long* x, long* y)
{
    // Base Case
    if (a == 0) {
        *x = 0, *y = 1;
        return b;
    }

    long x1, y1; // To store results of recursive call
    long gcd = gcdExtended(b % a, a, &x1, &y1);

    // Update x and y using results of recursive
    // call
    *x = y1 - (b / a) * x1;
    *y = x1;

    return gcd;
}

keyPair primeGeneration(int x){ // returns a primePair with p and q holding prime numbers
	primeHold keyVar;
	FILE *fileQ, *fileP;
	long eulerN = 0;
	char line[SIZE], pbuff[SIZE], qbuff[SIZE], filenameP[SIZE+5], filenameQ[SIZE+5];
        long primeP,primeQ;
        long counter = 0;
        long e = 0;
	srand(time(NULL));
	long pIndex = (rand()%(PRIMETOTAL +1));
        long qIndex = (rand()%(PRIMETOTAL +1));
	printf("pIndex: %ld \n qIndex: %ld",pIndex,qIndex);
	if(x == 1){ //fast gen w billionP txt
		printf("Fast Generation \n");
		printf("pIndex: %ld \n qIndex: %ld",pIndex,qIndex);

		if (qIndex < pIndex){ //Ensure p is less than q for finding in the file
			long temp = pIndex;
			pIndex = qIndex;
			qIndex = temp;
		}
		
		snprintf(filenameP, sizeof(filenameP), "BillionPrimeSections/%ld million.txt", pIndex / 1000000);
		snprintf(filenameQ, sizeof(filenameQ), "BillionPrimeSections/%ld million.txt", qIndex / 1000000);
		fileP = fopen(filenameP,"r");
		fileQ = fopen(filenameQ,"r");
		pIndex = pIndex%1000000;
		qIndex = qIndex%1000000;
		printf("\n %s \n %s",filenameP, filenameQ);
		while (fgets(line, sizeof(line), fileP) != NULL) {
			if(counter == pIndex){

				strcpy(pbuff,line);
				break;
			}
			counter++;
		}
		counter = 0;
		while(fgets(line, sizeof(line), fileQ) != NULL){
			if(counter == qIndex){
				strcpy(qbuff,line);
				break;
			}
			counter++;
		}
		sscanf(pbuff, "%ld", &keyVar.p);
		sscanf(qbuff, "%ld", &keyVar.q);
		keyVar.n = keyVar.p * keyVar.q;
		printf("Completed");
	}
	else{ //seive of erathanoses or the other one still deciding
		primeGeneration(1);//temp redirection to fast for now
	}
	eulerN = etf(keyVar.n);
	for(long i = eulerN/2; i<eulerN; i++){
		if(gcd(i,eulerN) == 1)
			e = i;
	}
	if (e = 0){
		for(long j = eulerN/2; j>1; j--){
			if(gcd(j,eulerN) == 1)
				e = j;
		}
	}

	keyVar.d = modInverse(e,eulerN);
	keyPair keys;
	keys.sharedX = keyVar.n;
       	keys.uniqueY = e;
	keys.uniqueZ = keyVar.d;	
	return keys;
}

void main(){
	char buffer[SIZE];
	int choice = 0;
	printf("Key Gen Testing!\n Do select 1 for fast generation or 2 for slow (if you fuck up it will choose fast)");
	fgets(buffer,SIZE,stdin);
	buffer[strcspn(buffer, "\n")] = '\0';
	sscanf(buffer, "%d", &choice);
	primeGeneration(choice);


}
