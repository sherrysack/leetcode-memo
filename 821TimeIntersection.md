## 821 Time Intersection

### 821. Time Intersection

Give two users' ordered online time series, and each section records the user's login time point `x` and offline time point `y`. Find out the time periods when both users are online at the same time, and output in ascending order.

### Example

Given seqA = `[(1,2),(5,100)]`, seqB = `[(1,6)]`, return `[(1,2),(5,6)]`.

```
Explanation:
In these two time periods (1,2),(5,6), both users are online at the same time.
```

Given seqA = `[(1,2),(10,15)]`, seqB = `[(3,5),(7,9)]`, return `[]`.

```
Explanation:
There is no time period, both users are online at the same time.
```

### Notice

- We guarantee that the length of online time series meet `1 <= len <= 1e6`.
- For a user's online time series, any two of its sections do not intersect.











```java
/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param seqA: the list of intervals
     * @param seqB: the list of intervals
     * @return: the time periods
     */
    public List<Interval> timeIntersection(List<Interval> seqA, List<Interval> seqB) {
        // Write your code here
        if(seqA.get(0).start > seqB.get(0).start) {
            return timeIntersection(seqB, seqA);
        }
        int[] startA = new int[seqA.size()];
        int idx = 0;
        for(Interval inta: seqA) {
            startA[idx++] = inta.start;
        }
        List<Interval> result = new ArrayList<>();
        for(Interval intB: seqB) {
            int x = intB.start; int y = intB.end;
            int left = 0; int right = startA.length-1;
            int mid = left+(right-left)/2;
            while(left+1 < right) {
                if(startA[mid] == x) {
                    break;
                }else if(startA[mid] > x) {
                    right = mid;
                }else{
                    left = mid;
                }
                mid = left+(right-left)/2;
            }
            int i = mid;
            while(i < startA.length && startA[i] < y) {
                if(seqA.get(i).end > x) {
                    result.add(new Interval(Math.max(seqA.get(i).start, intB.start),
                        Math.min(seqA.get(i).end, intB.end)));
                }
                i++;
            }
        }
        return result;
    }
}
```

