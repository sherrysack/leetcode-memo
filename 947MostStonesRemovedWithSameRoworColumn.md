# 947 Most Stones Removed With Same Row or Column

On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a *move* consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?

 

**Example 1:**

```
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
```

**Example 2:**

```
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
```

**Example 3:**

```
Input: stones = [[0,0]]
Output: 0
```

 

**Note:**

1. `1 <= stones.length <= 1000`
2. `0 <= stones[i][j] < 10000`







```java
class Solution {
    public int removeStones(int[][] stones) {
        //dfs find all the connected stones
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int[] stone: stones) {
            if(!map.containsKey(stone[0])) {
                map.put(stone[0], new ArrayList<Integer>());
            }
            map.get(stone[0]).add(stone[1]+10000);
            if(!map.containsKey(stone[1]+10000)) {
                map.put(stone[1]+10000, new ArrayList<Integer>());
            }
            map.get(stone[1]+10000).add(stone[0]);
        }
        Set<Integer> visited = new HashSet<>();
        int island = 0;
        for(int[] stone: stones) {
            if(!visited.contains(stone[0])) {
                island++;
                dfs(visited, stone[0], map);
                dfs(visited, stone[1]+10000, map);
            }
        }
        return stones.length-island;
    }
    private void dfs(Set<Integer> visited, int a, Map<Integer, ArrayList<Integer>> map) {
        visited.add(a);
        List<Integer> lists = map.get(a);
        for(Integer tar: lists) {
            if(!visited.contains(tar)) {
                dfs(visited, tar, map);
            }
        } 
    }
}
```

