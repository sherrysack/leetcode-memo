## DP Part

### 416 Partition Equal Subset Sum

Given a **non-empty** array containing **only positive integers**, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

**Note:**

1. Each of the array element will not exceed 100.
2. The array size will not exceed 200.



**Example 1:**

```
Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
```



**Example 2:**

```
Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
```

```java
class Solution {
    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0 || nums.length == 1) return false;
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if(sum%2 == 1) return false;
        boolean[][] dp = new boolean[nums.length+1][sum/2+1];
        dp[0][0] = true;
        for(int i = 1; i < nums.length+1; i++) {
            dp[i][0] = true;
        }
        for(int j = 1; j < sum/2+1; j++) {
            dp[0][j] = false;
        }
        for(int i = 1; i < nums.length+1; i++) {
            for(int j = 1; j < sum/2+1; j++) {
                dp[i][j] = dp[i-1][j];
                if(j >= nums[i-1]) {
                    dp[i][j] = (dp[i][j] || dp[i-1][j-nums[i-1]]);
                }
            }
        }
        return dp[nums.length][sum/2];
    }
}
```

