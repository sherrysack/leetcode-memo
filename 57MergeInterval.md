## 57 Merge Interval



------

Given a set of *non-overlapping* intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

**Example 1:**

```
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
```

**Example 2:**

```
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
```







```java
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        //my solution will merge the original intervals list too
        // [[1,10],[6,9]] [3,19]
        //[[1,19]]
        List<Interval> list = new ArrayList<Interval>();
        list.add(newInterval);
        int len = intervals.size();
        boolean merge = false;
        for(int i = 0; i < len; i++) {
            Interval temp = intervals.get(i);
            newInterval = list.get(list.size()-1);
            list.remove(list.size()-1);
            if(temp.start > newInterval.end) {
                list.add(newInterval);
                for(int j = i; j < len; j++) {
                    list.add(intervals.get(j));
                }
                return list;
            }else if(temp.end < newInterval.start) {
                list.add(temp);
                list.add(newInterval);
            }else {
                newInterval.start = Math.min(newInterval.start, temp.start);
                newInterval.end = Math.max(newInterval.end, temp.end);
                list.add(newInterval);
            }
        }
        return list;
    }
}
```

