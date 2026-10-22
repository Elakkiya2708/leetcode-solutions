import java.util.ArrayList;
import java.util.List;

class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int factorial = 1;
        
        for (int i = 1; i < n; i++) {
            factorial *= i;
            numbers.add(i);
        }
        numbers.add(n);
        
        // Convert k to 0-indexed
        k--;
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = n; i > 0; i--) {
            int index = k / factorial;
            sb.append(numbers.get(index));
            numbers.remove(index);
            
            if (i > 1) {
                k %= factorial;
                factorial /= (i - 1);
            }
        }
        
        return sb.toString();
    }
}