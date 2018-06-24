**151 Reverse Words in a String**



Given an input string, reverse the string word by word.

**Example:  **

```
Input: "the sky is blue",
Output: "blue is sky the".

```

**Note:**

- A word is defined as a sequence of non-space characters.
- Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
- You need to reduce multiple spaces between two words to a single space in the reversed string.

**Follow up: **For C programmers, try to solve it *in-place* in *O*(1) space.

```java
public class Solution {
    //this problem needs to deal with leading and trailing space, and reduce multiple spaces to single one
    public void reverse(char[] s, int sta, int end) {
        for(int i = sta, j = end-1; i <= j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
    public String reverseWords(String s) {
        char[] ar = s.toCharArray();
        //first, reverse all the string
        reverse(ar,0, ar.length);a
        //second, reverse each word
        //the chars between sta and end is the word that we need to reverse
        int sta = 0, end = 0;
        boolean newword = false;
        for(char c: ar) {
            if(c != ' ') {
                end++;
                newword = true;
            }else{
                if(newword == true) {
                    reverse(ar, sta, end);
                    newword = false;
                }
                end++;
                sta = end;
            }
        }
        if(newword == true) {
            reverse(ar, sta, end);
        }
        //last, delete all the unnecessary space
        int slow =0, f = 0, word_cnt = 0;
        while(f< ar.length) {
            while(f< ar.length && ar[f] == ' ' ) {f++;}
            if(f == ar.length) break;
            if(word_cnt > 0) {
                ar[slow++]=' ';
            }
            while(f<ar.length && ar[f] != ' ') {
                ar[slow++] = ar[f++]; 
            }
            word_cnt++;
        }
        String res = String.valueOf(ar).substring(0, slow);
        return res;
    }
}
```

