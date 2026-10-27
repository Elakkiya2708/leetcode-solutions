class Solution {
    public int countDigitOne(int n) {
        int count = 0;
        long factor = 1;
        
        while (factor <= n) {
            long divider = factor * 10;
            count += (n / divider) * factor + Math.min(Math.max(n % divider - factor + 1, 0L), factor);
            factor = divider;
        }
        
        return count;
    }
}