## 300 Longest Increasing Sequence ##

Given an unsorted array of integers, find the length of longest increasing subsequence.

**Example:**

```
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
```

**Note:**

- There may be more than one LIS combination, it is only necessary for you to return the length.
- Your algorithm should run in O(*n2*) complexity.

**Follow up:** Could you improve it to O(*n* log *n*) time complexity?

**First Solution**

dp and memoization

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int dp[] = new int[nums.length];
        int res = 1;
        if(nums == null || nums.length == 0) return 0;
        Arrays.fill(dp, 1);
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```



**Second Solution**

DP and Binary Search

The dp array is meant to store the increasing subsequence formed by including the currently encountered

element. While traversing the nums array, we keep on filing the dp array with the elements encountered so far. For the element corresponding to the jth index(nums[j]), we determine its correct position in the dp array by making use of binary search, which can be used since the dp array is soring ascedently, and alos insert it at the correct position.

Note: dpdp array does not result in longest increasing subsequence, but length of dpdp array will give you length of LIS.

Consider the example:

input: [0, 8, 4, 12, 2]

dp: [0]

dp: [0, 8]

dp: [0, 4]

dp: [0, 4, 12]

dp: [0 , 2, 12] which is not the longest increasing subsequence, but length of dpdp array results in length of Longest Increasing Subsequence.



```java
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}
```



