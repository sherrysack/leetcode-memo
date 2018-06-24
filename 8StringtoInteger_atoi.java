class Solution {
    //ltong
    public int myAtoi(String str) {
        //read the digit
        //record the number's sign, once positive turned negative, return INT_MAX;
        //negative turned positive, return INT_MIN
        int result = 0;
        boolean positive = true;
        boolean overflow = false;
        //consider the corner case with empty string
        if (str.length() == 0) {
            return 0;
        }
        while(str.charAt(0) == ' '){
            str = str.substring(1);
        }
        //decide if this is digit
        if(!Character.isDigit(str.charAt(0))) {
            if(str.charAt(0) == '-') {
                positive = false;
                str = str.substring(1);
            } else if (str.charAt(0) == '+') {
                str = str.substring(1);
            } else{
                //cannot be converte to numerical value
                return 0;
            }
        }
        for (int i = 0; i<str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                int ndigit = Character.getNumericValue(str.charAt(i));
                int newResult = result*10+ndigit;
                //judge whether the number is overflow
                if (newResult < 0 || ((newResult-ndigit) /10) !=result) {
                    System.out.println("i should be heere");
                    if (positive) {
                        return Integer.MAX_VALUE;
                    }else {
                        return Integer.MIN_VALUE;
                    }
                }
                result = newResult;
            System.out.println(ndigit);
            System.out.println("the result is "+result);
            } else {
                break;
            }
            
        }
        if (positive) {
            return result;
        } else {
            return -1*result;
        }
    }
}

