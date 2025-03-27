package TopoSort;

import java.util.*;

public class Leetcode_207 {

}

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // practice waala hai
        int n = numCourses;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<Integer>());
        for (int[] temp : prerequisites) {
            adj.get(temp[1]).add(temp[0]);
        }
        int[] indegree = new int[n];
        for (ArrayList<Integer> list : adj) {
            for (int i : list)
                indegree[i]++;
        }
        System.out.println("indegree : " + Arrays.toString(indegree));
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> topo = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                indegree[i]--; // taaki isey ab dubaara na le sake
            }
        }
        System.out.println(adj);

        while (!q.isEmpty()) {
            int node = q.poll();
            topo.add(node);
            for (int i : adj.get(node)) {
                indegree[i]--;
                if (indegree[i] == 0)
                    q.add(i);
            }
        }
        System.out.println(topo);
        return topo.size() == n;
    }
}
