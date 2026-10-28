class Solution {
        // This question can convert to HouseRobber problem
    // If you rob nums[i], then you can not rob nums[i-1] and nums[i+1]
    // You can not rob two adjacent houses(numbers)
    // First thing you need to do is to sum them up for the particular number
	
    // Subproblem=
    // robbery of current house + loot from houses before the previous one
    // OR loot from the previous house robbery and any loot captured before that
    // rob(i) = Math.max( rob(i - 2) + currentValue, rob(i - 1) )
	
	// for example:
	// After we sum up fpr each particular number, we can get
	// sum1,  sum2,  sum3,  sum4, sum5...
	//    1,     6,     3,     8,    10
	
	// if we reach to sum3,  prev1 = 6 , prev2=1
	// (prev2+sum3)=4  <  prev1=6
	// so new prev1 = 6,  new prev2 = prev1 =6
    // if we reach to sum4, (prev2+sum4)=6+8=14 < prev1=6
	// so new prev1 =14 , new prev2=prev1=6
	// if we reahc to sum5, (prev2+sum5)=6+10=16 > prev1=14
	// finally, new prev1=16 which is the largest sum
	// the key is to compare previous sum(prev1) and the sum before prev1(prev2)+current value
	
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] values = new int[10001];
        for (int num : nums)
            values[num] += num;

        int prev1 = 0;
        int prev2 = 0;

        for (int value : values) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + value, prev1);
            prev2 = tmp;
        }
        return prev1;
    }
}