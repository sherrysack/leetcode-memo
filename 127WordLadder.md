## 127 Word Ladder

Given two words (*beginWord* and *endWord*), and a dictionary's word list, find the length of shortest transformation sequence from *beginWord* to *endWord*, such that:

1. Only one letter can be changed at a time.
2. Each transformed word must exist in the word list. Note that *beginWord* is *not* a transformed word.

**Note:**

- Return 0 if there is no such transformation sequence.
- All words have the same length.
- All words contain only lowercase alphabetic characters.
- You may assume no duplicates in the word list.
- You may assume *beginWord* and *endWord* are non-empty and are not the same.

**Example 1:**

```
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
```

**Example 2:**

```
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
```



Solution:





```java
class Solution {
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean contain = false;
        for(String word: wordList) {
            if(word.equals(endWord)) contain = true;
        }
        if(!contain) return 0;
        Set<String> src = new HashSet<String>(wordList);
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> visited = new HashSet<>();
        end.add(endWord);
        begin.add(beginWord);
        int res= 0;
        visited.add(beginWord);
        visited.add(endWord);
        while(!begin.isEmpty() && !end.isEmpty()) {
            if(begin.size() > end.size()) {
                Set<String> tempSet = begin;
                begin = end;
                end = tempSet;
            }
            res++;
            Set<String> nextbegin = new HashSet<String>();
            for(String start: begin) {
                char[] arr = start.toCharArray();
                for(int i = 0; i < arr.length; i++) {
                    char old = arr[i];
                    for(char c = 'a'; c <= 'z'; c++) {
                        arr[i] = c;
                        String news = String.valueOf(arr);
                        if(end.contains(news)) return res+1;
                        if(src.contains(news) && !visited.contains(news)) {
                            nextbegin.add(news);
                            visited.add(news);
                        }
                    }
                    arr[i] = old;
                }

            }
            begin = nextbegin;
        }
        return 0;
    }
}
```

