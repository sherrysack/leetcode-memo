class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0 || strs[0].length() == 0) {
            return "";
        }
        int len = strs[0].length();
        int i = 1;
            for(; i< strs.length && len>0; i++) {
                if(strs[i].length() < len) {
                    len = strs[i].length();
                }
                while(len> 0 && !strs[i].substring(0, len).equals(strs[0].substring(0, len))) {
                    len--;
                }
            }
        if(len>0) {
            return strs[0].substring(0, len);
        }
        return "";


    }
}
