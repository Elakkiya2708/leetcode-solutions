class Solution {
    long mod = 1000000007L;

    public int findPaths(int m, int n, int move, int i, int j) {
        long[][][] dp = new long[m + 1][n + 1][move + 1];
        for (long[][] a : dp) for (long[] b : a) Arrays.fill(b, -1);
        return (int) solve(m, n, move, i, j, dp);
    }

    long solve(int m, int n, int move, int i, int j, long[][][] dp) {
        if (i < 0 || j < 0 || i >= m || j >= n) return 1;
        if (move == 0) return 0;
        if (dp[i][j][move] != -1) return dp[i][j][move];
        long ans = (solve(m, n, move - 1, i + 1, j, dp) + solve(m, n, move - 1, i, j + 1, dp) + solve(m, n, move - 1, i - 1, j, dp) + solve(m, n, move - 1, i, j - 1, dp)) % mod;
        return dp[i][j][move] = ans;
    }
}