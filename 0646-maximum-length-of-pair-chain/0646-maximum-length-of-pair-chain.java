class Solution {
    public int findLongestChain(int[][] pairs) {

        // Sort pairs by their ending value
        Arrays.sort(pairs, Comparator.comparingDouble(o -> o[1]));

        // End value of the first selected pair
        int end = pairs[0][1];

        // We have selected the first pair
        int chain = 1;

        // Check all remaining pairs
        for(int i=1; i<pairs.length; i++){

            // Current pair can be added only if
            // its start is greater than the previous end
            if(pairs[i][0] > end){

                // Add current pair to the chain
                chain++;

                // Update the end of the last selected pair
                end = pairs[i][1];
            }
        }

        return chain;
    }
}