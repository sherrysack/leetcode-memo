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
