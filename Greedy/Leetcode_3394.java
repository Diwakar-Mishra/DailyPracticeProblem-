import java.util.*;

class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        int N = rectangles.length;
        // vertical check
        // sorting acc. to y coordinates
        Arrays.sort(rectangles, (a, b) -> Integer.compare(a[1], b[1]));
        int start = rectangles[0][1]; // starting point
        int end = rectangles[0][3]; // ending point
        int index = 1;
        int cutsPossible = 0;
        while (index < N) {
            if (rectangles[index][1] < end) {
                end = Math.max(end, rectangles[index][3]);
            } else {
                cutsPossible++;
                if (cutsPossible == 2)
                    return true;
                start = rectangles[index][1];
                end = rectangles[index][3];
            }
            index++;
        }
        // horizontally check karo
        // sorting w.r.t. x-co-ordinates
        Arrays.sort(rectangles, (a, b) -> Integer.compare(a[0], b[0]));
        for (int temp[] : rectangles) {
            System.out.println(temp[0] + "   " + temp[2]);
        }
        start = rectangles[0][0];
        end = rectangles[0][2];
        index = 1;
        cutsPossible = 0;
        while (index < N) {
            if (rectangles[index][0] < end) {
                end = Math.max(end, rectangles[index][2]);
            } else {
                cutsPossible++;
                if (cutsPossible == 2)
                    return true;
                start = rectangles[index][0];
                end = rectangles[index][2];
            }
            index++;
        }
        return false;

    }
}