# 333 Largest BST Subtree

Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

**Note:**
A subtree must include all of its descendants.

**Example:**

```
Input: [10,5,15,1,8,null,7]

   10 
   / \ 
  5  15 
 / \   \ 
1   8   7

Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.
```

**Follow up:**
Can you figure out ways to solve it with O(n) time complexity?

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int res = 0;
    class Result {
        int size; int lower; int higher;
        Result(int size, int lower, int higher) {
            this.size =  size;
            this.lower = lower;
            this.higher = higher;
        }
    }
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return res;
    }
    public Result helper(TreeNode root) {
        if(root == null) return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        Result left = helper(root.left);
        Result right = helper(root.right);
        if(left.size == -1 || right.size == -1 || root.val <= left.higher ||
          root.val >= right.lower) {
            //the lower and higher is both 0, because nobody cares
            return new Result(-1, 0, 0);
        }
        int size = left.size+right.size+1;
        res = Math.max(size, res);
        //although if the root is null, it always return left.lower and right.higher,
        //it's written for the leaf node
        return new Result(size, Math.min(root.val, left.lower), Math.max(root.val, right.higher));

    }
}
```

