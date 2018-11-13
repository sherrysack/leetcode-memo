## 294 Flip Game II

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: `+` and `-`, you and your friend take turns to flip two **consecutive** `"++"` into `"--"`. The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

**Example:**

```
Input: s = "++++"
Output: true 
Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
```

**Follow up:**
Derive your algorithm's runtime complexity.





```java
class Solution {
    public boolean canWin(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        
        Map<String, Boolean> memo = new HashMap<>();
        StringBuilder sb = new StringBuilder(s);
        return dfs(sb, memo);
    }
    
    private boolean dfs(StringBuilder sb, Map<String, Boolean> memo) {
        if (memo.containsKey(sb.toString())) {
            return memo.get(sb.toString());
        }
        
        for (int i = 0; i < sb.length()-1; i++) {
            if (sb.charAt(i) == '+' && sb.charAt(i+1) == '+') {
                sb.setCharAt(i, '-');
                sb.setCharAt(i+1, '-');
                if (!dfs(sb, memo)) {
                    sb.setCharAt(i, '+');
                    sb.setCharAt(i+1, '+');
                    memo.put(sb.toString(), true);
                    return true;
                }
                sb.setCharAt(i, '+');
                sb.setCharAt(i+1, '+');
            }
        }
        memo.put(sb.toString(), false);
        return false;
    }
}
```

