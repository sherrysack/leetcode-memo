## 298. Binary Tree Longest Consecutive Sequence

Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

**Example 1:**

```
Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3

Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
```

**Example 2:**

```
Input:

   2
    \
     3
    / 
   2    
  / 
 1

Output: 2 

Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
```

I should be more confident with my own solution!!!!!



Remember to ask yourself, would it be safe to store max/min answer with the root node???

if not, store them in the global value!!!!

```java
class Solution {
    private int max = 0;
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return max;
    }
    private int helper(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) {
            max = Math.max(max, 1);
            return 1;
        }
        int ll =1; int rl = 1; int rmax = 1;
        if(root.left != null) {
            ll = helper(root.left);
            if(root.left.val == root.val+1) {
                rmax = ll+1;
            }
        }
        if(root.right != null) {
            rl = helper(root.right);
            if(root.right.val == root.val+1) {
                rmax = Math.max(rmax, rl+1);
            }
        }
        max = Math.max(rmax, max);
        //max = Math.max(max, ll);
        //max = Math.max(max, rl);
        return rmax;
    }
    
}
```



```java
class Solution {
    private int max = 0;
    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        int tar = root.val;
        helper(root, tar, 0);
        return max;
    }
    private void helper(TreeNode root, int tar, int res) {
        max = Math.max(res, max);
        if(root == null) {
            return;
        }
        if(root.val == tar) {
            res+=1;
        }else {
            res = 1;
        }
        helper(root.left, root.val+1, res);
        helper(root.right, root.val+1, res);
    }
}
```

