**Symmetric Tree**

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree `[1,2,2,3,4,4,3]` is symmetric:

```
    1
   / \
  2   2
 / \ / \
3  4 4  3

```

But the following `[1,2,2,null,3,null,3]` is not:

```
    1
   / \
  2   2
   \   \
   3    3

```

**Note:**
Bonus points if you could solve it both recursively and iteratively.





Thoughts: Waste a lot of time trying solving this problem as "[judging the tree is balanced or not](https://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/)". Stupid!

Only the top-down solution applies to this problem. Think about how you usually know whether a tree is symmetric. The procedure naturally leads to recursive solution.

***Recursive Solution***

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
    public boolean isSymmetric(TreeNode root) {
        return root == null || finddiff(root.left, root.right);
    }

    public boolean finddiff(TreeNode left, TreeNode right) {
        if(left == null && right == null) {
            return true;
        }
        if(left== null || right == null) {
            return false;
        }
        if(left.val != right.val) {
            return false;
        }else {
            return finddiff(left.left, right.right) && finddiff(right.left, left.right);
        }
    }
}
```

***Non Recursive solution***

```java
public boolean isSymmetric(TreeNode root) {
    if(root==null)  return true;
    
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode left, right;
    if(root.left!=null){
        if(root.right==null) return false;
      //if not, then root has both left child and right child, push their values and wait for judgment
        stack.push(root.left);
        stack.push(root.right);
    }
    else if(root.right!=null){
        return false;
    }
        
    while(!stack.empty()){
      //judge whether the left child equals to right child
        if(stack.size()%2!=0)   return false;
        right = stack.pop();
        left = stack.pop();
        if(right.val!=left.val) return false;
        //if the left child has left child
        if(left.left!=null){
          //if the right child doesn't have right child, then the tree is not balanced
            if(right.right==null)   return false;
            stack.push(left.left);
            stack.push(right.right);
        }
        else if(right.right!=null){
            return false;
        }
            
        if(left.right!=null){
            if(right.left==null)   return false;
            stack.push(left.right);
            stack.push(right.left);
        }
        else if(right.left!=null){
            return false;
        }
    }
    
    return true;
}
```

we can add a wrapper to system malloc function and have a statical varibale to record the allocated memory and free 