class Solution {
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0.0;
        int n = points.length;
        
        // Try every possible combination of 3 distinct points
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    double area = calculateArea(points[i], points[j], points[k]);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        
        return maxArea;
    }
    
    private double calculateArea(int[] p1, int[] p2, int[] p3) {
        // Area using the determinant / cross-product formula:
        // 0.5 * |x1(y2 - y3) + x2(y3 - y1) + x3(y1 - y2)|
        return 0.5 * Math.abs(
            p1[0] * (p2[1] - p3[1]) + 
            p2[0] * (p3[1] - p1[1]) + 
            p3[0] * (p1[1] - p2[1])
        );
    }
}