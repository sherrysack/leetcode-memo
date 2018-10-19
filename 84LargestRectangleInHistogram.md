## 84 Largest rectangle in Histogram

Given *n* non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

 

![img](https://assets.leetcode.com/uploads/2018/10/12/histogram.png)
Above is a histogram where width of each bar is 1, given height = `[2,1,5,6,2,3]`.

 

![img](https://assets.leetcode.com/uploads/2018/10/12/histogram_area.png)
The largest rectangle is shown in the shaded area, which has area = `10` unit.

 

**Example:**

```
Input: [2,1,5,6,2,3]
Output: 10
```



```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] newheights = new int[heights.length+1];
        newheights[heights.length] = -1;
        for(int i = 0; i < heights.length; i++) {
            newheights[i] = heights[i];
        }
        int global = 0;
        stack.push(0);
        for(int i = 1; i < newheights.length; i++) {
            global = Math.max(global, newheights[i]);
            if(newheights[i] >= newheights[stack.peek()]) {
                stack.push(i);
            }else {
                //left border is determined by stack.peek(), but need to set as -1 when the stack is empty
                int left = 0;
                int right = i-1;
                //height is determined by the last popping out element
                int height = heights[stack.peek()];
                while(!stack.isEmpty() && newheights[i] < heights[stack.peek()]) {
                    height = heights[stack.pop()];
                    left = stack.isEmpty()?-1:stack.peek();
                    global = Math.max(global, (right-left)*height);
                }
                stack.push(i);
            }
        }
        return global;
    }
}
```

