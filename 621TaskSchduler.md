## 621 Task Schduler



Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval **n** that means between two **same tasks**, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the **least** number of intervals the CPU will take to finish all the given tasks.

 

**Example:**

```
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
```

 

**Note:**

1. The number of tasks is in the range [1, 10000].
2. The integer n is in the range [0, 100].







```java
class Solution {
    private class Task implements Comparable<Task> {
        Character c;
        int num;
        Task(char ch) {
            this.c = ch;
            this.num = 1;
        }
        public int compareTo(Task b) {
            //sort descendingly
            return this.num != b.num ? b.num-this.num:b.c-this.c;
        }
    }
    public int leastInterval(char[] tasks, int n) {
        if(tasks == null || tasks.length == 0) return 0;
        PriorityQueue<Task> pq = new PriorityQueue<>();
        Map<Character, Task> map = new HashMap<>();
        for(char c: tasks) {
            if(map.containsKey(c)) {
                map.get(c).num+=1;
            }else {
                map.put(c, new Task(c));
            }
        }
        for(Map.Entry<Character, Task> entry: map.entrySet()) {
            pq.add(entry.getValue());
        }
        int sum = 0;
        int cnt = n+1;
        while(!pq.isEmpty()) {
            List<Task> templist = new ArrayList<>();
            while(!pq.isEmpty() && cnt > 0) {
                Task temp = pq.poll();
                temp.num-=1;
                templist.add(temp);
                cnt--;
                sum++;  
            }
            for(int i = 0; i < templist.size(); i++) {
                if(templist.get(i).num > 0) {
                    pq.offer(templist.get(i));
                }
            }

            if(pq.isEmpty()) break;
            sum+=cnt;
            cnt = n+1;
        }
        return sum;
    }
}
```

