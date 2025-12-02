// C program to find multiplicative modulo inverse using
// Extended Euclid algorithm.
// Credit to GeekforGeeks
// https://www.geeksforgeeks.org/dsa/multiplicative-inverse-under-modulo-m/
#include <stdio.h>

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

