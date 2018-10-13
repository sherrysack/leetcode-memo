## 166. Fraction to Recurring Decimal

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

**Example 1:**

```
Input: numerator = 1, denominator = 2
Output: "0.5"
```

**Example 2:**

```
Input: numerator = 2, denominator = 1
Output: "2"
```

**Example 3:**

```
Input: numerator = 2, denominator = 3
Output: "0.(6)"
```

### Solution

```java
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        //calculate the integral part
        //deal with sign
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-":"");
        long num = Math.abs((long)numerator);
        //long num = (long)Math.abs(numerator); will overflow still........
        long den = Math.abs((long)denominator);
        res.append(Long.toString(num/den));
        long remain = num%den;
        if(remain == 0) return res.toString();
        //deal with the float part
        Map<Long, Integer> map = new HashMap<>();
        //set.add(remain);
        //set is used to detect whether there are any loop
        StringBuilder rem = new StringBuilder(".");
        map.put(remain, rem.length());
        while(remain != 0) {
            remain *= 10;
            rem.append(Long.toString(remain / den));
            remain = remain%den;
            if(map.containsKey(remain)) {
                rem.insert(map.get(remain), "(");
                rem.append(")");
                break;
            }else {
                map.put(remain, rem.length());
            }

        }
        res.append(rem.toString());
      
        return res.toString();
    }
}
```

