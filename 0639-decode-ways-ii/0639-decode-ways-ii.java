class Solution {
    static int MOD = 1000000007;
    public int numDecodings(String s) {
        int n = s.length();
        // 0 has only 2 cases :
        // either 10 or 20 because 0 alone isn't possible and greater than 26 isn't possible 
        // Also for digits 1-26 either the digits can be considered one digit or 2 digit if the 2 digit number is <= 26
        // So only 2 ways for this case when 1<= num <= 26
        // Or else only 1 way

        long ways = 0;
        long prev2 = 1;
        long prev1 = 0;

        char first = s.charAt(0);
        if(first == '0') return 0;
        if(first == '*') prev1 = 9;
        else prev1 = 1;

        for(int i = 2; i <= n; i++) {
            char prev = s.charAt(i-2);
            char curr = s.charAt(i-1);
            long currWays = 0;

            // Case 1 : Single digit
            if(curr == '*') {
                currWays = (currWays + 9 * prev1) % MOD;
            } else if(curr != '0') {
                currWays = (currWays + prev1) % MOD;
            }

            // Case 2: Two digits
            if(prev == '*') {
                if(curr == '*') {
                    currWays = (currWays + 15 * prev2) % MOD;
                } else {
                    if(curr <= '6') {
                        currWays = (currWays + 2 * prev2) % MOD;
                    } else {
                        currWays = (currWays + prev2) % MOD;
                    }
                }
            } else if(prev == '1') {
                if(curr == '*') {
                    currWays = (currWays + 9 * prev2) % MOD;
                } else {
                    currWays = (currWays + prev2) % MOD;
                }
            } else if(prev == '2') {
                if(curr == '*') {
                    currWays = (currWays + 6 * prev2) % MOD;
                } else if(curr <= '6') {
                    currWays = (currWays + prev2) % MOD;
                }
            }

            prev2 = prev1;
            prev1 = currWays;
        }

        return (int) prev1;
    }
}