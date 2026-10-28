class Solution {
    int[] res = new int[] { 0, 0, 1, 3,
                        8, 19, 43, 94,
                        201, 423, 880, 1815,
                        3719, 7582, 15397, 31171,
                        62952, 126891, 255379, 513342,
                        1030865, 2068495, 4147936, 8313583,
                        16655823, 33358014, 66791053, 133703499,
                        267603416, 535524643, 1071563515, 2143959070 };
    
    public int findIntegers(int num) {
        return 1 + num - dew(num);
    }
    
    public int dew(int num) {
        if(num < 3) return 0;
        int pow = 0, mult = 1;
        while(mult*4 <= num) {
            ++pow;
            mult *= 2;
        }
        if(num/mult == 3) return res[pow+1] + res[pow] + 1 + num - mult*3;
        else return res[pow+1] + dew(num - mult*2);
    }
}