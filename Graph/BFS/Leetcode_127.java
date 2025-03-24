package BFS;

import java.util.*;

public class Leetcode_127 {

}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int steps = 0;
        Queue<String> q = new LinkedList<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++)
            visited.put(wordList.get(i), false);
        // visited.put(endWord,false);
        q.add(beginWord);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 1; i <= n; i++) {
                String word = q.poll();

                if (word.equals(endWord))
                    return steps + 1;

                // try to convert it to every possible string

                StringBuilder s = new StringBuilder(word);

                for (int index = 0; index < s.length(); index++) {
                    char temp = s.charAt(index);
                    // try to make all of the possible string by altering that index
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        s.setCharAt(index, ch);

                        if (visited.containsKey(s.toString()) && !visited.get(s.toString())) {
                            System.out.println(steps + " => " + s.toString());
                            q.add(s.toString());
                            visited.put(s.toString(), true);
                        }
                    }

                    s.setCharAt(index, temp);
                }

            }
            steps++;
        }
        return 0;
    }
}
