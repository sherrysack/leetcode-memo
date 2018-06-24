class Solution {
//if use KMP, it would be too much for the easy tag;
//if not, it would be too easy
    public int strStr(String haystack, String needle) {
      for (int i = 0; i<=haystack.length()-needle.length(); i++) {
        for (int j = 0; j <= needle.length(); j++) {
          if (j == needle.length()) return i;//successfully find the needle and return the index
            //failed to find the needle even in the last possible place and return -1
          if (needle.charAt(j) != haystack.charAt(i + j)) break;
        }
      }
    return -1;
    }
}
