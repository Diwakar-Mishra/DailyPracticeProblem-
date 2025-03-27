import java.util.*;

public class Leetcode_2059 {

}

class Solution {
    public int minimumOperations(int[] nums, int start, int goal) {
        Queue<int[]> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        q.add(new int[] { start, 0 });
        visited.add(start);
        int n = nums.length;
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int val = temp[0];
            int steps = temp[1];
            if (val == goal)
                return steps;
            // if(val<0 || val>1000) continue;
            for (int i = 0; i < n; i++) {
                int val1 = val + nums[i];
                if (val1 >= 0 && val1 <= 1000 && !visited.contains(val1)) {
                    q.add(new int[] { val1, steps + 1 });
                    visited.add(val1);
                }
                int val2 = val - nums[i];
                if (val2 >= 0 && val2 <= 1000 && !visited.contains(val2)) {
                    q.add(new int[] { val2, steps + 1 });
                    visited.add(val2);
                }
                int val3 = val ^ nums[i];
                if (val3 >= 0 && val3 <= 1000 && !visited.contains(val3)) {
                    q.add(new int[] { val3, steps + 1 });
                    visited.add(val3);
                }
                if (val1 == goal || val2 == goal || val3 == goal)
                    return steps + 1;
            }
        }
        return -1;
    }
}
