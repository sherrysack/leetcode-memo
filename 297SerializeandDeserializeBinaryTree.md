## 297. Serialize and Deserialize Binary Tree

Hard

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

**Example:** 

```
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
```

**Clarification:** The above format is the same as [how LeetCode serializes a binary tree](https://leetcode.com/faq/#binary-tree). You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

**Note:** Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.





The way you encode is not the same with the way that leetcode do.

For example, this is the tree you are going to encode: [1,2,3,null,null,4,5]

Your encode result is: 1,2,#,#,3,4,#,#,5,#,#,

And this type is much better than calculating each level's nodes number, because you can build tree recursively. In order not need to count, you should constantly remove nodes once it's consumed, that's the reason you choose to use Deque.

Attention: Little trick to transfer between String[] to Deque<String>

```java
        String[] split = data.split(comma);
        Deque<String> nodes = new ArrayDeque<>(Arrays.asList(split));
```



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
public class Codec {
    private static final String nullNode = "#";
    private static final String comma = ",";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        if(root == null) return nullNode;
        encodeHelper(res, root);
        System.out.println(res.toString());
        return res.toString();
    }
    public void encodeHelper(StringBuilder res, TreeNode root) {
        if(root == null) {
            res.append(nullNode).append(comma);
            return;
        }
        res.append(String.valueOf(root.val)).append(comma);
        encodeHelper(res, root.left);
        encodeHelper(res, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(comma);
        Deque<String> nodes = new ArrayDeque<>(Arrays.asList(split));
        //nodes.addAll(split);
        return decode(nodes);
    }
    public TreeNode decode(Deque<String> nodes) {
        String str = nodes.remove();
        if(str.equals(nullNode)) return null;
        TreeNode root = new TreeNode(Integer.valueOf(str));
        if(nodes.size() > 0) {
            root.left = decode(nodes);
        }
        if(nodes.size() > 0) {
            root.right = decode(nodes);
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```



## 428 Serialize and Deserialize N-ary Tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following `3-ary` tree

 

![img](https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png)

 

as `[1 [3[5 6] 2 4]]`. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

**Note:**

1. `N` is in the range of `[1, 1000]`
2. Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.



```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Codec {
    private static final String NN = "#";
    private static final String comma = ",";
    private static final String end = "$";
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        if(root == null) return NN;
        encode(root, sb);
        //System.out.println(sb.toString());
        return sb.toString();
    }
    public void encode(Node root, StringBuilder sb) {
        sb.append(root.val).append(comma);
        for(Node n: root.children) {
            encode(n, sb);
        }
        sb.append(end).append(comma);
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        String[] src = data.split(comma);
        Deque<String> nodes = new ArrayDeque<String>(Arrays.asList(src));
        return decode(nodes);
    }
    public Node decode(Deque<String> nodes) {
        String str = nodes.remove();
        if(str.equals(NN)) return null;
        Node root = new Node(Integer.valueOf(str), new ArrayList<Node>());
        while(!nodes.isEmpty() && !nodes.getFirst().equals(end)) {
            root.children.add(decode(nodes));
        }
        if(!nodes.isEmpty()) {
            nodes.remove();
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```



