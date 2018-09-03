Iterative way to do post order traversal with linked list(double insert):

```java


class Solution {
public List<Integer> postorderTraversal(TreeNode root) {
    LinkedList<Integer> result = new LinkedList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode p = root;
    while(!stack.isEmpty() || p != null) {
        if(p != null) {
            stack.push(p);
            result.addFirst(p.val);  // Reverse the process of preorder
            p = p.right;             // Reverse the process of preorder
        } else {
            TreeNode node = stack.pop();
            p = node.left;           // Reverse the process of preorder
        }
    }
    return result;
}
}
```





Iterative way to do postorder traversal with stack only:

**Maintain a previous node** , to record the previous visiting node on the traversing path, so that we know what the direction we are taking now and what is the direction we are taking next.

- Root = stack.top
- if previous is null ==> going down
- if previous is current's parent ==> going down
- if previous == current.left ==> left subtree finished. going current.right
- if previous == current.right ==> right subtree finished, pop current, going up

This solution is important because this is the closest imitation of how actually the recursion is  

```java
class Solution {
  public List<Integer> postorder(){
    while(!stack.isEmpty()) {
      TreeNode current = stack.peekFirst();
      //going down
      if(prev == null || current == prev.left || current == prev.right) {
        if(current.left != null) {
          stack.push(current.left);
        }else if(current.right != null) {
          stack.push(current.right);
        }else {
          res.add(current.val);
          stack.pollFirst();
        }
      }else if(current.left == prev) {
        if(current.right != null) {
          stack.offerFirst(current.right);
        }else {
          res.add(current.val);
          stack.pollFirst();
        }
      }else {
        res.add(current.val);
       	stack.pollFirst();
      }
      prev = current;
    }
  }
}
```

