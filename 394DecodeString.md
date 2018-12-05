## 394 Decode String

Given an encoded string, return it's decoded string.

The encoding rule is: `k[encoded_string]`, where the *encoded_string* inside the square brackets is being repeated exactly *k* times. Note that *k* is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, *k*. For example, there won't be input like `3a` or `2[4]`.

**Examples:**

```
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
```

run the test case:
2[pq3[b]c]ef:
letter exists outside the last parentheses
2[3[a]]:
no letter exists between 2 left parentheses
2[3[a]c]:
letter exists between the right parentheses and right parenthese;

You need to think out a rule to work on all kinds of string:
(1) use a strStack to store string and a cntStack to store number, use a String of res to store the current result
(2) Read the digit in a while loop, then push them into the cntStack
(3) Encounter "[", push res into the strStack, and set res to ""
(4) Encounter "]", pop the number n from the cntStack, and repeat the res for n times, strStack.peek() += res, res = strStack.poll()




**Solution**

sta and sta2 don't need to match, you just use sta2 to store whatever you have finished combining.



```java
public class Solution {
    public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                //even if it is empty, push it into the stack
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                //get it from the stack and work as a header
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}
```

