## 528 Random Pick With Weight ##

------

Given an array `w` of positive integers, where `w[i]` describes the weight of index `i`, write a function `pickIndex` which randomly picks an index in proportion to its weight.

Note:

1. `1 <= w.length <= 10000`
2. `1 <= w[i] <= 10^5`
3. `pickIndex` will be called at most `10000` times.

**Example 1:**

```
Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
```

**Example 2:**

```
Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
```

**Explanation of Input Syntax:**

The input is two lists: the subroutines called and their arguments. `Solution`'s constructor has one argument, the array `w`. `pickIndex`has no arguments. Arguments are always wrapped with a list, even if there aren't any.

Solution:

```java
//the difficult poitn lies in you need to know the interval is left contained and right incontained
class Solution {
    int[] range;
    int prev;
    public Solution(int[] w) {
        prev = 0;
        range = new int[w.length];
        for(int i = 0; i < w.length; i++) {
            range[i] = w[i]+prev;
            prev = range[i];
                    
        }
        };
    
    public int pickIndex() {
        int rand = (int)(Math.random() * prev);
        int res = binarySearch(range, rand);
        return res;
    }
    
    private int binarySearch(int[] src, int rand) {
        int left = 0;
        int right = src.length;
        while(left+1 < right) {
            int mid = left + (right-left)/2;
            //if the number is equalling to a number in src, then return the next interval's sequence number
            if(src[mid] == rand) return mid+1;
            if(src[mid] <= rand) {
                left = mid;
            }else {
                right = mid;
            }
        }
        return rand < src[left] ? left : right;
    }
}
```

