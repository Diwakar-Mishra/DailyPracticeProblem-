import java.util.*;
public class Leetcode_2492 {
    
}
class Solution {
    public int minScore(int n, int[][] roads) {
        // preparing the graph right now 
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i=0;i<=n;i++) adj.add(new ArrayList<>());
        for(int[] road : roads){
            adj.get(road[0]).add(new int[]{road[1],road[2]});
            adj.get(road[1]).add(new int[]{road[0],road[2]});
        }
        // for(ArrayList<int[]> list : adj){
        //     for(int[] s : list) System.out.print(Arrays.toString(s) + "   ");
        //     System.out.println();
        // }
        int dist = Integer.MAX_VALUE;
        Queue<int[]> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        visited.add(n);
        for(int[] temp : adj.get(n)){
            q.add(temp);
            visited.add(temp[0]);
        }
        while(!q.isEmpty()){
            int[] temp = q.poll();
            dist = Math.min(dist,temp[1]);
            int node = temp[0];
            for(int[] arr : adj.get(node)){
                if(visited.contains(arr[0])){
                    // take that distance into the consideration 
                    // do not add that node into the queue
                    dist = Math.min(dist,arr[1]);
                }else{
                    q.add(arr);
                    visited.add(arr[0]);
                }
            }
        }
        return dist;
    }
}
