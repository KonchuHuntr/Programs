/* Euler Toitent Function
 * shoutout to https://www.geeksforgeeks.org/dsa/eulers-totient-function/
 *
 * */
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>

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
