import java.util.*;

public class Leetcode_2033 {

}

class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        // for (int[] temp : meetings)
        // System.out.print(temp[0] + " " + temp[1] + " ");
        // System.out.println();
        int count = 0;
        int index = 1;
        int n = meetings.length;
        int start = meetings[0][0];
        if (start > 1)
            count += (start - 1);
        int end = meetings[0][1];
        while (index < n && end <= days) {
            if (meetings[index][0] < end) {
                end = Math.max(meetings[index][1], end);
            } else {
                if (meetings[index][0] == end) {
                    // System.out.println("count is increased by " + 0);
                } else {
                    // System.out.println("count is increased by " + (meetings[index][0] - end -
                    // 1));
                    count += (meetings[index][0] - end - 1);
                }
                start = meetings[index][0];
                end = meetings[index][1];
            }
            index++;
        }
        if (end < days)
            count += (days - end);
        return count;
    }
}
