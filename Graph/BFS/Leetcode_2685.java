package BFS;

import java.util.*;

public class Leetcode_2685 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {
                { 0, 1 },
                { 0, 2 },
                { 1, 2 },
                { 3, 4 }
        };
        Solution obj = new Solution();
        System.out.println(obj.countCompleteComponents(n, edges));
    }
}

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<Integer>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // do the traversal now
                if (traverse(adj, i, visited))
                    count++;
            }
        }
        return count;
    }

    boolean traverse(ArrayList<ArrayList<Integer>> adj, int node, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] temp = new boolean[adj.size()];
        // iss node se connected kitne elements hai
        // unko maine temp mai true mark kar diyaa
        temp[node] = true;
        for (int i : adj.get(node)) {
            q.add(i);
            visited[i] = true;
            temp[i] = true;
        }
        int edges = q.size(); // 0th node se connected ... different number of nodes
        boolean ans = true;
        while (!q.isEmpty()) {
            int n = q.poll();
            if (adj.get(n).size() != edges) {
                ans = false;
            }
            for (int neigh : adj.get(n)) {
                if (!temp[neigh])
                    ans = false;
                else if (!visited[neigh]) {
                    q.add(neigh);
                    visited[neigh] = true;
                }
            }
        }
        return ans;
    }
}
