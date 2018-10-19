## 212 Word Search II

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

**Example:**

```
Input: 
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Output: ["eat","oath"]
```

**Note:**
You may assume that all inputs are consist of lowercase letters `a-z`.

**bruteforce+DFS**

```java
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        
    }
}
```

*<u>time complexity</u>* 

Assumption: m*n matrix, length of word is l, size of the dictionary is k.

for each element in the matrix, there is a DFS, the time complexity is O(4^l)

Total complexity is O(kmn* 4^l).

What is the inefficient part of our current solution, How can you improve it?

[app, apple, appple]

group all the common prefix together, and only searched for once —> we need to use Trie to represent the whole dictionary —>Use the trie to prune the DFS procedure.

1 how many levels?  — length of target

2 what do we do at each level? — try to match one of the character in the target to a neighbor

3 what are all possible ways for each level going the next — for all possible 4 directions, for all the children of the current node in the tries, if there are any matchings.



**Trie + dfs backtracking**

![mage-20181016223543](/var/folders/22/f323f7p11t9dshf3kbmt7xwr0000gn/T/abnerworks.Typora/image-201810162235436.png)

![mage-20181016223717](/var/folders/22/f323f7p11t9dshf3kbmt7xwr0000gn/T/abnerworks.Typora/image-201810162237176.png)

```java

```

Time complexity

Assumption: m*n matrix, length of word is l, size of the dictionary is k.

for each element in the matrix, there is a DFS, the time complexity is O(4^l).

Total complexity is O(kl + mn* 4^l).

O(kl) the time to build the trie.









