class Pair{
    int first;
    int second;
    int third;

    Pair(int first , int second , int third){
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] res = new int[m][n];
        Queue<Pair> q = new LinkedList<>();

        for(int i = 0 ; i<m ; i++){
            for(int j = 0 ; j<n ; j++){
                if(mat[i][j] == 0){
                    q.offer(new Pair(i , j, 0));
                }
            }
        }

        int[] delrow = {-1 , 0 , 1 , 0};
        int[] delcol = {0 , -1 , 0 , 1};

        while(!q.isEmpty()){
            int row = q.peek().first;
            int col = q.peek().second;
            int steps = q.poll().third;
            res[row][col] = steps;            
            for(int i = 0 ; i<4 ; i++){
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];

                if(nrow>=0 && ncol>=0 && nrow<m && ncol<n && mat[nrow][ncol] == 1){
                    mat[nrow][ncol] = 0;
                    q.offer(new Pair(nrow , ncol , steps + 1));
                }
            }
        }

        return res;
    }
}