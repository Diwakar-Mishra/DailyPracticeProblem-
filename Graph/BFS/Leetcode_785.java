package BFS;

import java.util.*;

public class Leetcode_785 {

}

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (!bfs(graph, i, color))
                    return false;
            }
        }
        return true;
    }

    boolean bfs(int[][] graph, int node, int[] color) {
        int n = graph.length;
        // int[] color = new int[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        color[node] = 1;
        while (!q.isEmpty()) {
            int temp = q.poll();
            int clr = color[temp];
            for (int i : graph[temp]) {
                if (color[i] == 0) {
                    color[i] = (clr == 1) ? 2 : 1;
                    q.add(i);
                } else if (color[i] == clr)
                    return false;

            }
        }
        return true;
    }
}
