## 636. Exclusive Time of Functions

Given the running logs of **n** functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

Each function has a unique id, start from **0** to **n-1**. A function may be called recursively or by another function.

A log is a string has this format : `function_id:start_or_end:timestamp`. For example, `"0:start:0"` means function 0 starts from the very beginning of time 0. `"0:end:0"` means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

**Example 1:**

```
Input:
n = 2
logs = 
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
Output:[3, 4]
Explanation:
Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. 
Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. 
So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
```



**Note:**

1. Input logs will be sorted by timestamp, NOT log id.
2. Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
3. Two functions won't start or end at the same time.
4. Functions could be called recursively, and will always end.
5. 1 <= n <= 100

pay attention to the detail.

think about how to deal with this kind of problem...

( ( ( ) ) ( ) )

```java
class Solution {
    private class Log {
        int id;
        int time;
        public Log(int a, int b, int s) {
            this.id = a;
            this.time = b;
        }
    }
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Deque<Log> stack = new ArrayDeque<>();
        for(int i = 0; i < logs.size(); i++) {
            String temp = logs.get(i);
            String[] str = temp.split(":");
            Integer id = Integer.parseInt(str[0]);
            Integer time = Integer.parseInt(str[2]);
            if(str[1].equals("start")) {
                if(!stack.isEmpty()) {
                    res[stack.peek().id]+= time-stack.peek().time;
                 }
                Log log = new Log(id, time, i);
                stack.push(log);
            }else {
                Log log = stack.pop();
                res[id]+=time-log.time+1;
                if(!stack.isEmpty()) {
                    stack.peek().time = time+1;  
                }    
            }
        }
        return res;
    }
}
```

