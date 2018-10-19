## 547 Friend Circles



There are **N** students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a **direct** friend of B, and B is a **direct** friend of C, then A is an **indirect** friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a **N\*N** matrix **M** representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are **direct** friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

**Example 1:**

```
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
```



**Example 2:**

```
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
```



**Note:**

1. N is in range [1,200].
2. M[i][i] = 1 for all students.
3. If M[i][j] = 1, then M[j][i] = 1.



------




  find it hard to write dfs solution:

let's review how we write dfs:

```pseudocode
int cnt = 0
foreach node i in S:
	if i is unvisited:
		cnt+=1
		find node j is connected with i:
			mark j as visited
			dfs(j)
return cnt
```









union find

```java
class Solution {
    private int find(int[] father, int i) {
        if(father[i] != i) {
            father[i] = find(father, father[i]);
            return father[i];
        }
        return i;
    }
    private void union(int[] father, int i, int j) {
        int fi = find(father, i);
        int fj = find(father, j);
        if(fi != fj) {
            father[fi] = fj;
        }
    }
    public int findCircleNum(int[][] M) { 
        //these must be symmetric matrix
        if(M == null || M.length == 0 || M[0].length == 0) return 0;
        int[] father = new int[M.length];
        for(int i = 0; i < M.length; i++) {
            father[i] = i;
        }
        int res = M.length;
        for(int i = 0; i < M.length; i++) {
            for(int j = i+1; j < M.length; j++) {
                if(M[i][j] == 1){
                    if(find(father, i)!=find(father, j)) {
                        //System.out.println(i+","+find(father, i));
                        //System.out.println(j+","+find(father, j));
                        res-=1;
                        union(father, i, j);
                    }
                }
            }
        }
        return res;

    }
}
```

