import java.util.*;

public class Leetcode_1162 {

}

class Solution {
    public int maxDistance(int[][] grid) {
        // rotten oranges waala sawaal hai
        int n = grid.length;
        int[][] dist = new int[n][n];
        for (int[] temp : dist)
            Arrays.fill(temp, (int) (1e9));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    dist[i][j] = 0;
            }
        }
        // for(int[] temp : dist) System.out.println(Arrays.toString(temp));
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == 0)
                    q.add(new int[] { i, j });
            }
        }
        int[] deltaRow = { 0, -1, -1, -1, 0, 1, 1, 1 };
        int[] deltaCol = { -1, -1, 0, 1, 1, 1, 0, -1 };
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            for (int i = 0; i < 8; i++) {
                int nr = cell[0] + deltaRow[i];
                int nc = cell[1] + deltaCol[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    int manhattanDist = dist[cell[0]][cell[1]] + Math.abs(nr - cell[0]) + Math.abs(nc - cell[1]);
                    if (manhattanDist < dist[nr][nc]) {
                        q.add(new int[] { nr, nc });
                        dist[nr][nc] = manhattanDist;
                    }
                }
            }
        }
        // for(int[] temp : dist) System.out.println(Arrays.toString(temp));
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(dist[i][j], ans);
            }
        }
        if (ans == 0 || ans == (int) (1e9))
            return -1;
        else
            return ans;
    }
}
