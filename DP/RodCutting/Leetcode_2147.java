package DP.RodCutting;

import java.util.*;

public class Leetcode_2147 {

}

class Solution {
    int mod = (int) (1e9) + 7;
    int n;

    public int numberOfWays(String corridor) {
        n = corridor.length();
        char[] arr = corridor.toCharArray();
        int[] dp = new int[corridor.length()];
        Arrays.fill(dp, -1);
        int last = (arr[n - 1] == 'S') ? n - 1 : n;
        int[] pre = new int[n];
        pre[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            pre[i] = last;
            if (arr[i] == 'S')
                last = i;
        }
        if (pre[0] == n)
            return 0;
        int start = (arr[0] == 'S') ? 0 : pre[0];
        return solve(arr, start, dp, pre);
    }

    int solve(char[] arr, int index, int[] dp, int[] pre) {
        if (dp[index] != -1)
            return dp[index];
        int nextSIndex = pre[index];
        if (nextSIndex == n)
            return dp[index] = 0;
        int nextOfNextIndex = pre[nextSIndex];
        if (nextOfNextIndex == n)
            return dp[index] = 1;
        int result = solve(arr, nextOfNextIndex, dp, pre);
        if (result != 0) {
            result = (int) (((long) result * (long) (nextOfNextIndex - nextSIndex)) % mod);
        }
        return dp[index] = result;
    }

}
