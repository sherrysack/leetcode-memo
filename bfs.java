class Solution {
    public boolean canFinish(int numCourses, int[][] prerequites) {
        int[][] matrix = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        for(int i = 0; i < prerequites.length; i++) {
            int take = prerequites[i][0];
            int pre = prerequites[i][1];
            if(matrix[pre][take] == 0) {
                indegree[take]++;
            }
            matrix[pre][take] = 1;
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) queue.offer(1);
        }
        while(!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for(int i =0; i < numCourse; i++) {
                if(matrix[course][i] != 0) {
                    
                }
            }
        }
    }
}