## Trie





```java
class TrieNode {
    TrieNode[] children;
    boolean isWord;
    int numWords;

    //assume there is only lower case
    public boolean search(TrieNode root, String word) {
        TrieNode[] chi = root.children;
        for(int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i)-'a';
            if(chi[pos] != null) {
                chi = chi[pos].children;
            }
        }
        return chi.isWord;
    }

    public boolean searchN(TrieNode root, String word) {
        TrieNode[] chi = root.children;
        for(int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i)-'a';
            if(chi[pos] != null) {
                chi = chi[pos].children;
            }
        }
        return chi;
    }

    public boolean insert(TrieNode root, String word) {
        //if existed, don't need to insert
        if(search(root, word)) return false;
        TrieNode[] chi = root.children;
        for(int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i)-'a';
            if(chi[pos] == null) {
                chi[pos] = new TrieNode();
            }
        }
        chi.isWord = true;
        return true;
    } 

    public boolean delete(TrieNode root, String word) {
        //we can simply set the isWord to false;
        //but as an exercise, we want you to delete the useless edge
        //first we set the boolean of the word as false
        //method1: use a stack to record all the nodes on the nodes on the traversing path
        // if(node has not any children && !node.isWord) we can delete the node
        //method2: use recursion, do the deletion after return from the deeper level
        //sanity check applied
        if(!search(root, word)) {
            return false;
        }
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i)-'a';
            if(cur.children[pos] != null) {
                if(cur.children[pos].numWords == 1){
                    cur.children[pos] = null;
                    return true;
                }
                cur.children[pos].numWords -= 1;
                cur = cur.children[pos];
            }
        }
        cur.isWord = false;
        return true;
    }

    public List<String> findAllWithPrefix(TrieNode root, String prefix) {
        TrieNode matchNode = searchN(root, prefix);
        List<String> result = new ArrayList<>();
        if(matchNode == null) return result;
        dfsHelper(matchNode, new StirngBuilder(prefix), result);
        return result;
    }
    
    public void dfsHelper(TrieNode root, StringBuilder sb, List<String> res) {
        for(int i = 0; i < root.children.length; i++) {
            if(root.children[i] != null) {
                sb.append((char)'a'+i);
                if(root.children[i].isWord == true) {
                    res.add(sb.toString());
                }
                dfsHelper(root.children[i], sb, res);
                sb.setLength(sb.length()-1);
            }
        }
    }
}
```

It can be extended to support more operations, what other possible queries can it support?

Leetcode 10 Regular Expression

Leetcode 44 WildCard

Leetcode 212 WordSearchII.md

