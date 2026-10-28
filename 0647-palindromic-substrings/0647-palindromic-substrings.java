class Solution {
    private boolean isPalin(int l, int r, String s, Boolean[][] memo) {
        if (l >= r) return true;

        if (memo[l][r] != null) {
            return memo[l][r];
        }

        memo[l][r] =
            s.charAt(l) == s.charAt(r)
            && isPalin(l + 1, r - 1, s, memo);

        return memo[l][r];
    }

    public int countSubstrings(String s) {
        int count = 0;
        int n = s.length();

        // Bottom-up DP
        boolean[][] dp = new boolean[n][n];

        // Every single character is a palindrome.
        for (int i = 0; i < n; ++i) {
            dp[i][i] = true;
            count++;
        }

        // Build from smaller substrings to larger substrings.
        for (int len = 2; len <= n; ++len) {
            for (int l = 0; l <= n - len; ++l) {
                int r = l + len - 1;

                dp[l][r] =
                    s.charAt(l) == s.charAt(r)
                    && (len == 2 || dp[l + 1][r - 1]);

                if (dp[l][r]) {
                    count++;
                }
            }
        }

        return count;

        /*
        // Top-down alternative
        Boolean[][] memo = new Boolean[n][n];

        for (int i = 0; i < n; ++i) {
            memo[i][i] = true;
        }

        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                if (isPalin(i, j, s, memo)) {
                    count++;
                }
            }
        }

        return count;
        */
    }
}