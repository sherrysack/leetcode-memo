Given a binary tree in which each node contains an integer number. Determine if there exists a path **(the path can only be from one node to itself or to any of its descendants),** the sum of the numbers on the path is the given target number.

**Examples**

​    5

  /    \

2      11

​     /    \

​    6     14

  /

 3

If target = 17, There exists a path 11 + 6, the sum of the path is target.

If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.

If target = 10, There does not exist any paths sum of which is target.

If target = 11, There exists a path only containing the node 11.

**How is the binary tree represented?**

We use the level order traversal sequence with a special symbol "#" denoting the null node.

**For Example:**

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

​    1

  /   \

 2     3

​      /

​    4



```java
/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  public boolean exist(TreeNode root, int target) {
    // Write your solution here
    if(root == null) return false;
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    return dfs(root, map, target, 0);
  }
   private boolean dfs(TreeNode root, HashMap<Integer, Integer> map, int target, int prefix) {
     if(root == null) return false;
     if(map.containsKey(prefix+root.key-target)) {
       return true;
     }else {
       if(map.containsKey(prefix+root.key)) {
         map.put(prefix+root.key, map.get(prefix+root.key)+1);
       }else{
         map.put(prefix+root.key, 1);
       }
     }
     if(dfs(root.left, map, target, prefix+root.key)
        || dfs(root.right, map, target, prefix+root.key)) {
        return true;
     }else {
       if(map.get(prefix+root.key) == 1) {
         map.remove(prefix+root.key);
       }else {
        map.put(prefix+root.key, map.get(prefix+root.key)-1);
       }
     }
     return false;
   }
}
```

