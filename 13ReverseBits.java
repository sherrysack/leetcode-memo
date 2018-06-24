public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int i = 0, j = 31;
        Integer a = Integer.MIN_VALUE;
        while(i<j) {
            if(((n >>> i) & 1) != ((n >>> j) & 1)) {
                n = n^((1<<i)|(1<<j));
            }
            i++; j--;
        }
        return n;
    }
}
