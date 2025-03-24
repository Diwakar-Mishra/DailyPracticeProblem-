import java.util.*;

class Solution {
    public int minReorder(int n, int[][] connections) {
        ArrayList<ArrayList<Integer>> incoming = new ArrayList<>();
        ArrayList<ArrayList<Integer>> outgoing = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            incoming.add(new ArrayList<>());
            outgoing.add(new ArrayList<>());
        }
        for (int[] city : connections) {
            int u = city[0];
            int v = city[1];
            // edge from u to v
            incoming.get(v).add(u);
            outgoing.get(u).add(v);
        }
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        while (!q.isEmpty()) {
            int city = q.poll();
            for (int neigh : incoming.get(city)) {
                if (!visited[neigh]) {
                    q.add(neigh); // no need to reverse the edge
                    visited[neigh] = true;
                }
            }
            for (int neigh : outgoing.get(city)) {
                if (!visited[neigh]) {
                    q.add(neigh); // no need to reverse the edge
                    visited[neigh] = true;
                    count++;
                }
            }
        }
        return count;
    }
}