## 239 Sliding Window Maximum

Given an array *nums*, there is a sliding window of size *k* which is moving from the very left of the array to the very right. You can only see the *k* numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

**Example:**

```
Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

**Note:** 
You may assume *k* is always valid, 1 ≤ k ≤ input array's size for non-empty array.

**Follow up:**
Could you solve it in linear time?

use priority queue(O(nlog(k)))

```java
class Solution {
    private class Elem implements Comparable<Elem>{
        int index;
        int val;
        public Elem(int a, int b) {
            this.index = a;
            this.val = b;
        }
        public int compareTo(Elem b) {
            return b.val - this.val;
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null) return new int[0];
        if(nums.length < k) return new int[0];
        if(k==0) return new int[0];
        int[] res = new int[nums.length-k+1];
        PriorityQueue<Elem> pq = new PriorityQueue<>();
        for(int i = 0; i < nums.length; i++) {
            pq.offer(new Elem(i, nums[i]));
            if(pq.size() >= k) {
                pq.offer(new Elem(i, nums[i]));
                while(pq.peek().index <= i-k) {
                    pq.poll();
                }
                res[i-k+1] = pq.peek().val;  
            }
        }
        return res;
    }
}
```

linear time using deque

```java
public int[] maxSlidingWindow(int[] a, int k) {		
		if (a == null || k <= 0) {
			return new int[0];
		}
		int n = a.length;
		int[] r = new int[n-k+1];
		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < a.length; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				r[ri++] = a[q.peek()];
			}
		}
		return r;
	}
```

