## 218 The Skyline Problem

A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are **given the locations and height of all the buildings** as shown on a cityscape photo (Figure A), write a program to **output the skyline** formed by these buildings collectively (Figure B).

![Buildings](https://assets.leetcode.com/uploads/2018/10/22/skyline1.png) 



![Skyline Contour](https://assets.leetcode.com/uploads/2018/10/22/skyline2.png)



The geometric information of each building is represented by a triplet of integers `[Li, Ri, Hi]`, where `Li` and `Ri` are the x coordinates of the left and right edge of the ith building, respectively, and `Hi` is its height. It is guaranteed that `0 ≤ Li, Ri ≤ INT_MAX`, `0 < Hi ≤ INT_MAX`, and `Ri - Li > 0`. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: `[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] `.

The output is a list of "**key points**" (red dots in Figure B) in the format of `[ [x1,y1], [x2, y2], [x3, y3], ... ]` that uniquely defines a skyline. **A key point is the left endpoint of a horizontal line segment**. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:`[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]`.

**Notes:**

- The number of buildings in any input list is guaranteed to be in the range `[0, 10000]`.
- The input list is already sorted in ascending order by the left x position `Li`.
- The output list must be sorted by the x position.
- There must be no consecutive horizontal lines of equal height in the output skyline. For instance, `[...[2 3], [4 5], [7 5], [11 5], [12 7]...]` is not acceptable; the three lines of height 5 should be merged into one in the final output as such: `[...[2 3], [4 5], [12 7], ...]`



```java
class Solution {
    class Point implements Comparable<Point> {
        int x; int height; boolean isStart;
        public int compareTo(Point o) {
            if(this.x != o.x) {
                return this.x-o.x;
            }else {
                return (this.isStart ? -this.height:this.height)-
                    (o.isStart ? -o.height:o.height);
            }
        }
        
    }
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<int[]>();
        Point[] builds = new Point[buildings.length*2];
        int index = 0;
        for(int[] build: buildings) {
            Point s = new Point();
            s.x= build[0]; s.height =  build[2]; s.isStart = true;
            builds[index++] = s;
            Point e = new Point();
            e.x = build[1]; e.height= build[2]; e.isStart = false;
            builds[index++] = e;
        }
        Arrays.sort(builds);
        TreeMap<Integer, Integer> que = new TreeMap<>();
        que.put(0, 1); //use this to make sure the last keypoint is 0
        int prevMax = 0;
        for(Point p: builds) {
            if(p.isStart) {
                que.compute(p.height, (key, value)-> {
                    if(value!=null) {return value+1;}
                    return 1;
                });
            }else {
                que.compute(p.height, (key, value)-> {
                    if(value == 1) {return null;}
                    return value-1;
                });
            }
            int currentHeight = que.lastKey();
            if(currentHeight != prevMax) {
                result.add(new int[]{p.x, currentHeight});
                prevMax = currentHeight;
            }
        }
        return result;
    }
}
```

