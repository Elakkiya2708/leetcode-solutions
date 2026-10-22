class Solution {
    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            int maxRow = 0;
            int maxCol = 0;
            for (int j = 0; j < n; j++) {
                // Top projection: count non-zero cubes
                if (grid[i][j] > 0) {
                    ans++;
                }
                // Track max height in current row (yz-plane projection)
                maxRow = Math.max(maxRow, grid[i][j]);
                // Track max height in current column (zx-plane projection)
                maxCol = Math.max(maxCol, grid[j][i]);
            }
            ans += maxRow + maxCol;
        }
        
        return ans;
    }
}