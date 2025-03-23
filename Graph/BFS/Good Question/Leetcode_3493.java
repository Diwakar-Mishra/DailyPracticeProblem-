import java.util.*;

class Solution {
    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        ArrayList<HashSet<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new HashSet<>());
            for (int j : properties[i]) {
                arr.get(i).add(j);
            }
        }
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected(arr, i, j, k)) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
        // counting the number of connected components
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(adj, i, visited);
                count++;
            }
        }
        return count;

    }

    public void bfs(ArrayList<ArrayList<Integer>> adj, int node, boolean[] visited) {
        visited[node] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            int temp = q.poll();
            for (int neigh : adj.get(temp)) {
                if (!visited[neigh]) {
                    visited[neigh] = true;
                    q.add(neigh);
                }
            }
        }
    }

    public boolean isConnected(ArrayList<HashSet<Integer>> arr, int i, int j, int k) {
        int count = 0;
        for (int temp : arr.get(j)) {
            if (arr.get(i).contains(temp))
                count++;
        }
        return count >= k;
    }
}