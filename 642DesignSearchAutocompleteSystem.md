## 642 Design Search Autocomplete System

Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character `'#'`). For **each character** they type **except '#'**, you need to return the **top 3** historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

1. The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
2. The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
3. If less than 3 hot sentences exist, then just return as many as you can.
4. When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.

Your job is to implement the following functions:

The constructor function:

`AutocompleteSystem(String[] sentences, int[] times):` This is the constructor. The input is **historical data**. `Sentences` is a string array consists of previously typed sentences. `Times` is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

`List<String> input(char c):` The input `c` is the next character typed by the user. The character will only be lower-case letters (`'a'`to `'z'`), blank space (`' '`) or a special character (`'#'`). Also, the previously typed sentence should be recorded in your system. The output will be the **top 3** historical hot sentences that have prefix the same as the part of sentence already typed.

**Example:**
**Operation:** AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2]) 
The system have already tracked down the following sentences and their corresponding times: 
`"i love you"` : `5` times 
`"island"` : `3` times 
`"ironman"` : `2` times 
`"i love leetcode"` : `2` times 
Now, the user begins another search: 

**Operation:** input('i') 
**Output:** ["i love you", "island","i love leetcode"] 
**Explanation:** 
There are four sentences that have prefix `"i"`. Among them, "ironman" and "i love leetcode" have same hot degree. Since `' '` has ASCII code 32 and `'r'` has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored. 

**Operation:** input(' ') 
**Output:** ["i love you","i love leetcode"] 
**Explanation:** 
There are only two sentences that have prefix `"i "`. 

**Operation:** input('a') 
**Output:** [] 
**Explanation:** 
There are no sentences that have prefix `"i a"`. 

**Operation:** input('#') 
**Output:** [] 
**Explanation:** 
The user finished the input, the sentence `"i a"` should be saved as a historical sentence in system. And the following input will be counted as a new search. 

**Note:**

1. The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
2. The number of **complete sentences** that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
3. Please use double-quote instead of single-quote when you write test cases even for a character input.
4. Please remember to **RESET** your class variables declared in class AutocompleteSystem, as static/class variables are **persisted across multiple test cases**. Please see [here](https://leetcode.com/faq/#different-output) for more details.

```java
//my own solution
class AutocompleteSystem {
    private class TrieNode {
        TrieNode[] children;
        boolean isWord;
        int cnt;
        public TrieNode() {
            //the last one is space
            this.children = new TrieNode[27];
            this.isWord = false;
            this.cnt = 0;
        }
    }
    Map<String, Integer> map = new HashMap<>();
    TrieNode origin=  new TrieNode();
    public AutocompleteSystem(String[] sentences, int[] times) {
        buildTrie(sentences, times);

    }
    public void buildTrie(String[] sentences, int[] times) {
        int j = 0;

        for(String str: sentences) {
            TrieNode root = origin;
            map.put(str, times[j]);
            for(int i = 0; i < str.length(); i++) {
                char a = str.charAt(i);
                if(Character.isLetter(a) && root.children[a-'a'] == null) {
                    root.children[a-'a'] = new TrieNode();
                    root = root.children[a-'a'];
                } else if(a == ' ') {
                    if(root.children[26] == null) root.children[26] = new TrieNode();
                    root = root.children[26];
                }else {
                    root = root.children[a-'a'];
                }
            }
            root.isWord = true;
            root.cnt = times[j++];
        }
    }
    StringBuilder sb = new StringBuilder();
    TrieNode cur = origin;

    private class candidate implements Comparable<candidate>{
        String cand;
        int cnt;
        public candidate(String a, int b) {
            this.cand = a;
            this.cnt = b;
        }
        public int compareTo(candidate c) {
            if(c.cnt != this.cnt) {
                return c.cnt > this.cnt ? 1:-1;
            }else {
                return this.cand.compareTo(c.cand);
            }
            
        }
    }

    public List<String> input(char c) {
        PriorityQueue<candidate> pq = new PriorityQueue<>();
        int idx = 0;
        List<String> res = new ArrayList<>();
        if(c == '#') {
            String[] src = new String[1];
            src[0] =sb.toString();
            int[] count = new int[1];
            if(map.containsKey(src[0])) {
                count[0] = map.get(src[0]);
            }
            count[0] += 1;
            map.put(src[0], count[0]);
            buildTrie(src, count);
            sb = new StringBuilder();
            cur = origin;
            return res;
        }else if(c == ' ') {
            idx = 26;
        }else {
            idx = c-'a';
        }
        sb.append(c);
        //the place to put this 
        if(cur == null) return new ArrayList<String>();
        if(cur.children[idx] == null) {
            cur = null;
            return res;
        }else {
            cur = cur.children[idx];
        }
        dfs(cur, pq, sb);
        int k = 3;
        
        while(k-- > 0 && pq.size() > 0) {
            candidate can = pq.poll();
           res.add(can.cand);
            //System.out.println(can.cnt);
        }
        return res;
        
    }

    private void dfs(TrieNode cur, PriorityQueue<candidate> pq, StringBuilder sb) {
        if(cur.isWord == true) {
            
            pq.offer(new candidate(sb.toString(), cur.cnt));
        }
        for(int i = 0; i < 27; i++) {
            if(cur.children[i] != null) {
                if(i != 26)  dfs(cur.children[i], pq, sb.append((char)('a'+i)));
                else dfs(cur.children[i], pq, sb.append(" "));
                sb.setLength(sb.length()-1);
            }
            
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
```





```java
//other solution
public class AutocompleteSystem {
    class TrieNode {
        Map<Character, TrieNode> children;
        Map<String, Integer> counts;
        boolean isWord;
        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
            counts = new HashMap<String, Integer>();
            isWord = false;
        }
    }
    
    class Pair {
        String s;
        int c;
        public Pair(String s, int c) {
            this.s = s; this.c = c;
        }
    }
    
    TrieNode root;
    String prefix;
    
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";
        
        for (int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }
    }
    
    private void add(String s, int count) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            TrieNode next = curr.children.get(c);
            if (next == null) {
                next = new TrieNode();
                curr.children.put(c, next);
            }
            curr = next;
            curr.counts.put(s, curr.counts.getOrDefault(s, 0) + count);
        }
        curr.isWord = true;
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            add(prefix, 1);
            prefix = "";
            return new ArrayList<String>();
        }
        
        prefix = prefix + c;
        TrieNode curr = root;
        for (char cc : prefix.toCharArray()) {
            TrieNode next = curr.children.get(cc);
            if (next == null) {
                return new ArrayList<String>();
            }
            curr = next;
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (a.c == b.c ? a.s.compareTo(b.s) : b.c - a.c));
        for (String s : curr.counts.keySet()) {
            pq.add(new Pair(s, curr.counts.get(s)));
        }

        List<String> res = new ArrayList<String>();
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            res.add(pq.poll().s);
        }
        return res;
    }
}
```

