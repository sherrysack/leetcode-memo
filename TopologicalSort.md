## Topological Sort

The sample programming problem is course schedule.

### Kahn 

```pseudocode
L--> Empty list that will contain the sorted elements

S --> Set of all nodes with no incoming edges

while S is nonempty do

	remove a node n from S

	insert n into L

	foreach node n with an edge from n to m do

		remove edge e from the graph

		if m has no other incoming edges then

			insert m into S

if graph has edges then
	return error(graph has at least one cycle)
else
	return L(a topologically sortedorder)
```

The point is to maintain a set that contain nodes with no incoming edges

```java
public class KahnTopological {
    private List<integer> result;
    private Queue<Integer> setOfZeroIndegree;
    private int[] indegrees;
    private int edges;
    private Digraph di;

    public KahnTopological(Digraph di) {

        this.di = di;
        this.edges = di.getE();
        this.indegrees = new int[di.getV()];
        this.result = new ArrayList<Integer>();
        this.setOfZeroIndegree = new LinkedList<Integer>();
        //intialize the indegrees array
        Iterable<Integer>[] adjs = di.getAdj();
        for(int i = 0; i < adjs.length; i++) {
            for(int w: adjs[i]) {
                indegrees[w]++;
            }
        }
        //initialize the set that contains nodes of zero indegrees
        for(int i = 0; i < indegrees.length; i++) {
            if(0 == indegress[i]) {
                setOfZeroIndegree.enqueue(i);
            }
        }
        process();
    }
    private void process() {
        while(!setOfZeroIndegree.isEmpty()) {
            int v = setOfZeroIndegree.dequeue();
            result.add(v);
            for(int w: dj.adj(v)) {
                //traverse every edge
                edges--;
                if(0 == --indegres[w]) {
                    setOfZeroIndegree.enqueue(w);
                }
            }
        }
        if(0 != edges) {
            throw new IllegalArgument("Has Cycle");
        }
    }


}
```

The time complexity of Kahn algorithm is O(E+V).



### the topological sort based on dfs ###

```pseudocode
L <-- Empty list that contain the sorted nodes
S <-- Set of all nodes with no outgoing edges
foreach node n in S do
	visit(n)
funciton visit(node n)
	if n has not been visited yet then
		mark n as visited
		for each node m with an edge from m to n do
			visit(m)
		add n to L
```

The implementation of DFS is much simpler. 

Actually it only adds one more line: add n to L

the reason behind this line is:  Whenever the node's visit is finished, it means there are no other nodes pointing to it that are  not in L. Of course, this will not be correct if the graph has cycles.

It means in a DAG, v-->w, then dfs(w) would never exit before dfs(v).

> The pseudo code provided aforehand can't fit in the graph that is not DAG, it would cause dead loop. The dfs solution for 207 fix this by change visit[node] to false at every end of visiting node.

Hamilton Path:

the path that visit each node in the graph once and only once.



## 207 Course Schedule

Khan Algorithm

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int[][] edges = new int[numCourses][numCourses];
        int cnt = 0;
        for(int[] relation: prerequisites) {
            int take = relation[0];
            int prev = relation[1];
            if(edges[take][prev] != 0) {
                //there is a cycle
                return false;
            }
            if(edges[prev][take] == 1) {
                continue;
            }else {
                edges[prev][take] = 1;
                cnt++;
            }
            indegree[take]++;
        }
        Queue<Integer> que = new LinkedList<Integer>();
        for(Integer i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                que.offer(i);
            }
        }
        while(!que.isEmpty()) {
            int node = que.poll();
            int[] edgeofn = edges[node];
            for(int k = 0; k < edgeofn.length; k++) {
                if(edgeofn[k] == 1) {
                    cnt--;
                    if(0 == --indegree[k]) {
                        que.offer(k);
                    }
                }
            }
        }
        if(cnt == 0) {
            return true;
        }
        return false;
    }
}
```

DFS

pay attention to the comment part, this fix makes it possible to solve graph that's not DAG, but it's time complexity has also changed from O(V+E) to O(V+E^2).

```java
public class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            ArrayList[] graph = new ArrayList[numCourses];
            for(int i=0;i<numCourses;i++)
                graph[i] = new ArrayList();
                
            boolean[] visited = new boolean[numCourses];
            for(int i=0; i<prerequisites.length;i++){
                graph[prerequisites[i][1]].add(prerequisites[i][0]);
            }

            for(int i=0; i<numCourses; i++){
                if(!dfs(graph,visited,i))
                    return false;
            }
            return true;
        }

        private boolean dfs(ArrayList[] graph, boolean[] visited, int course){
            if(visited[course])
                return false;
            else
                visited[course] = true;;

            for(int i=0; i<graph[course].size();i++){
                if(!dfs(graph,visited,(int)graph[course].get(i)))
                    return false;
            }
            //this is hugely important because it needs to handle the in-DAG situation
            visited[course] = false;
            return true;
        }
    }
```



