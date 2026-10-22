import java.util.Arrays;

class Solution {
    public int[] closestPrimes(int left, int right) {
        // Step 1: Use Sieve of Eratosthenes up to 'right'
        boolean[] isPrime = new boolean[right + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        if (right >= 1) {
            isPrime[1] = false;
        }
        
        for (int p = 2; p * p <= right; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= right; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        
        // Step 2: Collect all prime numbers within the range [left, right]
        int prevPrime = -1;
        int minDiff = Integer.MAX_VALUE;
        int[] ans = new int[] {-1, -1};
        
        for (int i = left; i <= right; i++) {
            if (isPrime[i]) {
                if (prevPrime != -1) {
                    int diff = i - prevPrime;
                    if (diff < minDiff) {
                        minDiff = diff;
                        ans[0] = prevPrime;
                        ans[1] = i;
                    }
                    // Optimization: Twin primes have a difference of 2, which is the minimum possible gap for primes > 2.
                    if (minDiff == 2) {
                        break;
                    }
                }
                prevPrime = i;
            }
        }
        
        return ans;
    }
}