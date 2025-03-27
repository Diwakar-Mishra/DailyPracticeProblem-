import java.util.*;

public class Leetcode_1129 {

}

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // hum adjacency matrix banaa lete hai
        // matrix[i][j] = 1; i to j ek edge hai jiska color red hai
        // matrix[i][j] = 2; i to j ek edge hai jiska color blue hai
        // matrix[i][j] = 3; i to j 2 edges hai red bhi and blue bhi
        int[][] adj = new int[n][n];
        for (int[] temp : redEdges) {
            adj[temp[0]][temp[1]] = 1; // red color hai jee
        }
        for (int[] temp : blueEdges) {
            if (adj[temp[0]][temp[1]] == 0) {
                adj[temp[0]][temp[1]] = 2;
            } else {
                adj[temp[0]][temp[1]] = 3;
            }
        }
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);
        Queue<int[]> q = new LinkedList<>();
        boolean[] blue = new boolean[n];
        boolean[] red = new boolean[n];

        q.add(new int[] { 0, 1 }); // blue color k saath bhi
        q.add(new int[] { 0, 2 }); // red color k saath bhi

        blue[0] = true;
        red[0] = true;

        int steps = 1;
        ans[0] = 0;

        while (!q.isEmpty()) {

            int size = q.size();
            for (int k = 1; k <= size; k++) {

                int[] temp = q.poll();
                int node = temp[0];
                int lastColor = temp[1];

                for (int i = 0; i < n; i++) {
                    if (adj[node][i] != 0 && adj[node][i] != lastColor) {
                        // mai iss node ko toh visit kar hi sakta hoon
                        if (adj[node][i] == 1 && !red[i]) {
                            red[i] = true;
                            ans[i] = Math.min(ans[i], steps);
                            q.add(new int[] { i, 1 });
                        }
                        if (adj[node][i] == 2 && !blue[i]) {
                            blue[i] = true;
                            ans[i] = Math.min(ans[i], steps);
                            q.add(new int[] { i, 2 });
                        }
                        if (adj[node][i] == 3) {
                            if (lastColor == 1 && !blue[i]) {
                                blue[i] = true;
                                ans[i] = Math.min(ans[i], steps);
                                q.add(new int[] { i, 2 });
                            }
                            if (lastColor == 2 && !red[i]) {
                                red[i] = true;
                                ans[i] = Math.min(ans[i], steps);
                                q.add(new int[] { i, 1 });
                            }
                        }

                    }
                }

            }
            steps++;

        }
        for (int i = 0; i < n; i++) {
            if (ans[i] == Integer.MAX_VALUE)
                ans[i] = -1;
        }
        return ans;

    }
}
