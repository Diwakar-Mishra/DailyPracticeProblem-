package Good;

import java.util.*;

public class Leetcode_2780 {

}

class Solution {
    public int minimumIndex(List<Integer> nums) {
        // moore`s voting algorithm
        int x = nums.get(0);
        int count = 1;
        int n = nums.size();
        for (int i = 1; i < n; i++) {
            if (nums.get(i) != x) {
                count--;
            } else {
                count++;
            }
            if (count == 0) {
                x = nums.get(i);
                count = 1;
            }
        }
        System.out.println("dominant waala : " + x);
        int[] prefix = new int[n];
        prefix[0] = (x == nums.get(0)) ? 1 : 0;
        for (int i = 1; i < n; i++) {
            if (nums.get(i) == x)
                prefix[i] = prefix[i - 1] + 1;
            else
                prefix[i] = prefix[i - 1];
        }
        System.out.println(Arrays.toString(prefix));
        for (int i = 0; i < n - 1; i++) {
            // split possible
            int left = (i + 1);
            int right = (n - left);
            int leftX = prefix[i];
            int rightX = prefix[n - 1] - prefix[i];
            right = right / 2;
            left = left / 2;
            if (leftX > left && rightX > right)
                return i;
        }
        return -1;
    }
}
