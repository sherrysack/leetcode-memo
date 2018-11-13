## 140 Word Break II

Given a **non-empty** string *s* and a dictionary *wordDict* containing a list of **non-empty** words, add spaces in *s* to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

**Note:**

- The same word in the dictionary may be reused multiple times in the segmentation.
- You may assume the dictionary does not contain duplicate words.

**Example 1:**

```
Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
```

**Example 2:**

```
Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
```

**Example 3:**

```
Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
```

my own solution but exceeded the memory limit. but a good exercise for implementing my idea. and pay attention to the time complexity, the dp solution

```java
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        List<List<Integer>> cons = new ArrayList<List<Integer>>();
        if(s == null || s.length() == 0) return res;
        int[] dp = new int[s.length()+1];
        Arrays.fill(dp, -1);
        int cnt = 0;
        dp[0] = 0;
        Map<Integer, List<String>> map = new HashMap<>();
        List<String> lst = new ArrayList<String>();
        lst.add("");
        map.put(0, lst);
        for(int i = 1; i <= s.length(); i++) {
            List<String> list = new ArrayList<String>();
            map.put(i, list);
            for(int j = 0; j < i; j++) {
                for(int k = 0; k < wordDict.size(); k++) {
                    String str = wordDict.get(k);
                    if(i-j == str.length() && dp[j] >= 0 && s.substring(j, i).equals(str)) {
                        dp[i] = k;
                        List<String> temp = map.get(i);
                        for(String part: map.get(j)) {
                            temp.add(part+str+" ");
                        }
                        map.put(i, temp);
                    }

                        
                    }
                }
            }
        
        for(String tempres: map.get(s.length())) {
            res.add(tempres.substring(0, tempres.length()-1));
        }
        return res;
    }
}
```



word break's solution is different from word break(which uses dp), you should think of dfs whenever you need to list the permutation.

```java
public List<String> wordBreak(String s, Set<String> wordDict) {
    return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
}       

// DFS function returns an array including all substrings derived from s.
List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
    if (map.containsKey(s)) 
        return map.get(s);
        
    LinkedList<String>res = new LinkedList<String>();     
    if (s.length() == 0) {
        res.add("");
        return res;
    }               
    for (String word : wordDict) {
        //use startsWith really save a lot of code....
        if (s.startsWith(word)) {
            List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
            for (String sub : sublist) 
                res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
        }
    }       
    map.put(s, res);
    return res;
}
```

