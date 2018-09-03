```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lst = new ArrayList<>();
        inorder(root, lst);
        return lst;
    }
    public void inorder(TreeNode root, List<Integer> lst) {
        if(root == null) return;
        inorder(root.left, lst);
        lst.add(root.val);
        inorder(root.right, lst);
    }
}
```

```Java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if(root == null) {
            return res;
        }
        TreeNode next = root;
        while(!stack.isEmpty() || next != null) {
            if(next != null) {
                stack.push(next);
                next = next.left;
            } else {
                next = stack.poll();
                res.add(next.val);
                next = next.right;
            }
        }
        return res;
    }
}
```

