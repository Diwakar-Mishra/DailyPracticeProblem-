package FloydWarshal;

import java.util.*;

public class Leetcode_3015 {

}

class Solution {
    public int[] countOfPairs(int n, int x, int y) {
        int[][] dist = new int[n][n];
        for (int[] temp : dist)
            Arrays.fill(temp, (int) (1e9));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    dist[i][j] = 0;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            dist[i][i + 1] = 1;
            dist[i + 1][i] = 1;
        }
        if (x != y) {
            dist[x - 1][y - 1] = 1;
            dist[y - 1][x - 1] = 1;
        }
        // finding the minimum distance between the all possible pairs
        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
                }
            }
        }
        for (int[] temp : dist)
            System.out.println(Arrays.toString(temp));
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == 0)
                    continue;
                ans[dist[i][j] - 1]++;
            }
        }
        // for(int i=n-2;i>=0;i--) ans[i] = ans[i] + ans[i+1];
        return ans;
    }
}
