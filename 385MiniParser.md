## 385. Mini Parser

Medium

Given a nested list of integers represented as a string, implement a parser to deserialize it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

**Note:** You may assume that the string is well-formed:

- String is non-empty.
- String does not contain white spaces.
- String contains only digits `0-9`, `[`, `-` `,`, `]`.



**Example 1:**

```
Given s = "324",

You should return a NestedInteger object which contains a single integer 324.
```



**Example 2:**

```
Given s = "[123,[456,[789]]]",

Return a NestedInteger object containing a nested list with 2 elements:

1. An integer containing value 123.
2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789.
```



When you use stack to deal with nested problem, you should first think about how to solve it in the recursive way. You should mimic the way that recursive function use the stack.

```java
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
//each number itself is a nestedInteger
class Solution {
    public NestedInteger deserialize(String s) {
        if(s == null || s.length() == 0) return new NestedInteger();
        if(s.charAt(0) != '[') {
            return new NestedInteger(Integer.valueOf(s));
        }
        Stack<NestedInteger> sta = new Stack<>();
        int i = 0;
        NestedInteger ni = new NestedInteger();
        Queue<NestedInteger> que = new LinkedList<>();
        while( i < s.length()) {
            //this is where you start to call the dfs, so you should store 
            //an empty list on the top of stack
            if(s.charAt(i) == '[') {
                sta.push(new NestedInteger());
            }
            if(s.charAt(i) == ',') {
                //because the next letter is '[', so it will start another new function in the next step, but we haven't store this level's result yet
                //and store this level's function's result on the stack
                if(s.charAt(i+1) == '[') {
                    while(!que.isEmpty()) {
                        sta.peek().add(que.poll());
                    }
                }
            }
            //this is where you pop out the result
            //this is to imitate you return the last result to the upper level
            //and the function return to the upper level
            //so you store the result in que
            if(s.charAt(i) ==']') {
                NestedInteger temp = sta.pop();
                while(!que.isEmpty()) {
                    temp.add(que.poll());
                }
                que.offer(temp);
             }
            if(s.charAt(i) == '-' || Character.isDigit(s.charAt(i))) {
                int val = 0;
                boolean positive = true;
                if(s.charAt(i) == '-') {
                    positive = false;
                    i++;
                }
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    val = val*10+s.charAt(i)-'0';
                    i++;
                }
                i--;
                if(!positive) val *= -1;
                que.offer(new NestedInteger(val));
            }
            i++;
        }
        return que.poll();
        
    }
}
```

