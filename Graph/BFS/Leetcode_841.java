package BFS;

import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        // room number 0 : is getted unlocked
        q.add(0);
        visited[0] = true;
        int countUnlockedRooms = 1; // as 0th room is unlocked
        while (!q.isEmpty()) {
            int room = q.poll();
            // this room have the keys and can unlocked the further more rooms
            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    // this room is opening for the very first time
                    // and this room can unlock further more rooms
                    countUnlockedRooms++;
                    visited[key] = true;
                    q.add(key);
                }
            }
        }
        return countUnlockedRooms == n;
    }
}