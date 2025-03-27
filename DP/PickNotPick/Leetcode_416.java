package DP.PickNotPick;

import java.util.*;

public class Leetcode_416 {

}

class Solution {
    public boolean canPartition(int[] nums) {
        int total = findTotal(nums);
        if (total % 2 == 1)
            return false; // partition possible hi bahi hogaa
        int target = total / 2;
        int[][] dp = new int[nums.length][target + 1];
        for (int[] temp : dp)
            Arrays.fill(temp, -1);
        return solve(nums, 0, target, dp);
    }

    int findTotal(int[] nums) {
        int sum = 0;
        for (int i : nums)
            sum += i;
        return sum;
    }

    boolean solve(int[] nums, int index, int target, int[][] dp) {
        if (target == 0)
            return true;
        if (index == nums.length)
            return false;
        if (dp[index][target] != -1)
            return dp[index][target] == 1;
        boolean pick = false;
        if (nums[index] <= target)
            pick = solve(nums, index + 1, target - nums[index], dp);
        // if(pick) return true;
        boolean not_pick = solve(nums, index + 1, target, dp);
        boolean result = not_pick || pick;
        if (result)
            dp[index][target] = 1;
        else
            dp[index][target] = 0;
        return result;
    }

}