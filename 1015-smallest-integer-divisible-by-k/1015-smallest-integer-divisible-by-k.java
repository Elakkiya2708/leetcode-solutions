class Solution {
    public int smallestRepunitDivByK(int k) {
        // If k is divisible by 2 or 5, no such repunit can exist because numbers made of 1s always end in 1.
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        
        int remainder = 0;
        for (int length = 1; length <= k; length++) {
            remainder = (remainder * 10 + 1) % k;
            if (remainder == 0) {
                return length;
            }
        }
        
        return -1;
    }
}