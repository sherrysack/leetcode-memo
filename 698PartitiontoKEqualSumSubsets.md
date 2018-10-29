## 698 Partition to K Equal Sum Subsets



Given an array of integers `nums` and a positive integer `k`, find whether it's possible to divide this array into `k` non-empty subsets whose sums are all equal.

**Example 1:**

```
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
```



**Note:**

`1 <= k <= len(nums) <= 16`.

`0 < nums[i] < 10000`.



```java
// my own solution


class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || nums.length == 0) return true;
        long sum = 0;
        for(int n: nums) {
            sum += n;
        }
        long target = sum/k;
        if(sum%k != 0 || nums.length < k) return false;
        int[] visited  = new int[nums.length];
        Arrays.fill(visited, 0);
        return dfs(nums, visited, target, k, 0, 0);
    }
    private boolean dfs(int[] nums, int[] visited, long target, int k, int cursum, int index) {
        if(cursum == target) {
            k--;
            cursum = 0;
            index = 0;
        }
        if(k == 0) return true;
        for(int i = index; i < nums.length; i++) {
            if(visited[i] != 1) {
                visited[i] = 1;
                if(!dfs(nums, visited, target, k, cursum+nums[i], i+1)) {
                    visited[i] = 0;
                }else {
                    return true;
                }
             }
        }
        return false;
    }
    
}
```

- ![image-20181028220316391](/var/folders/22/f323f7p11t9dshf3kbmt7xwr0000gn/T/abnerworks.Typora/image-20181028220316391.png)