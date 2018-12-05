# 503 Next Greater Element II

Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

**Example 1:**

```
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
```



**Note:** The length of given array won't exceed 10000.



This note should already remind you of the stack.....

```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if(nums == null || nums.length == 0) return new int[0];
        if(nums.length == 1) return new int[]{-1};
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Stack<Integer> sta = new Stack<Integer>();
        for(int i = 0; i < 2*nums.length; i++) {
            int idx= i%(nums.length);
            while(!sta.isEmpty() && nums[sta.peek()] < nums[idx]) {
                result[sta.pop()] = nums[idx];
            }
            if(i < nums.length) sta.push(i);
        }
        return result;
    }
}
```

// A little notes about stack



First I wanted to know the stack size that the VM chooses for threads. I found that the 

```
ulimit -s
```

parameter has no direct influence on the stack size chosen by the VM. The default is apparently 512kb, and can be changed freely with the 

```
-Xss
```

 and 

```
-XX:ThreadStackSize
```

 parameters. But I could not find a difference in behaviour between those parameters. They appear to do the same thing. Further I found that this Linux machine is able to create new threads at a rate of about 5000 per second.

I performed these tests by creating new threads and setting them asleep in a blocking wait call immediately. I kept creating threads until the VM ran out of memory. With 512k stacks the number of threads was around 3700, for 256k stacks around 7300, for 128k around 13700. That leads to the following memory consumption by the stacks:

```
 3700 x 512kB = 1850MB
 7300 x 256kB = 1825MB
13700 x 128kB = 1713MB
```

Of course a 32-bit process is limited to 4GB of address space (minus 1 or 2 GB for the kernel). So it is only natural that the memory is close to these 2GB minus the heap size. (Note that Stack is never allocated from the heap.)

Another persons answer:

Since java don't support tail recursion optimization, it does has a maximum stack depth, while Scala would never stack overflow for tail recursion.

I tested on my system and didn't find any constant value, sometimes stack overflow occurs after 8900 calls, sometimes only after 7700, random numbers.

```java
public class MainClass {

    private static long depth=0L;

    public static void main(String[] args){
        deep(); 
    }

    private static void deep(){
        System.err.println(++depth);
        deep();
    }

}
```