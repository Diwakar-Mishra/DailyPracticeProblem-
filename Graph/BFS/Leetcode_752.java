package BFS;

import java.util.*;

public class Leetcode_752 {

}

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n * n + 1];
        q.add(1);
        visited[1] = true;
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 1; i <= size; i++) {
                int num1 = q.poll();
                // System.out.println(num1);
                if (num1 == n * n)
                    return steps;
                for (int j = 1; j <= 6; j++) {
                    int num2 = num1 + j;
                    if (num2 > n * n)
                        break;
                    int[] cell = cell(num2, n);
                    // System.out.println(num1 + " => " + num2 + " cell => " +
                    // Arrays.toString(cell));
                    int directTo = board[cell[0]][cell[1]] == -1 ? num2 : board[cell[0]][cell[1]];
                    if (!visited[directTo]) {
                        q.add(directTo);
                        visited[directTo] = true;
                        // System.out.println(num1 + " => " + directTo);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public int[] cell(int num, int n) {
        // n X n => ka chess board hai
        int r = (n - 1) - ((num - 1) / n);
        int dirn;
        if (n % 2 == 0) {
            dirn = (r % 2 == 0) ? 1 : 0; // 1 => right to left and 0 => left to right
        } else {
            dirn = (r % 2 == 0) ? 0 : 1;
        }
        int start = n * (n - 1 - r) + 1;
        int offset = num - start;
        int c;
        if (dirn == 1) {
            // right to left
            c = (n - 1) - offset;
        } else {
            // left to right
            c = 0 + offset;
        }
        return new int[] { r, c };
    }
}
