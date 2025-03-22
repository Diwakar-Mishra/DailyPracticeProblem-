import java.util.*;

public class Leetcode_1707 {
    public static void main(String[] args) {
        int[] nums = { 0, 1, 2, 3, 4 }; // Array of numbers

        int[][] queries = { // 2D array of queries
                { 3, 1 },
                { 1, 3 },
                { 5, 6 }
        };

        Solution obj = new Solution();
        System.out.println(obj.maximizeXor(nums, queries));
    }
}

class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];

        ArrayList<Query> q = new ArrayList<Query>();

        for (int i = 0; i < n; i++) {
            q.add(new Query(queries[i][0], queries[i][1], i));
        }

        Collections.sort(q, (a, b) -> Integer.compare(a.m, b.m));

        int index = 0;
        Arrays.sort(nums);
        Trie t = new Trie();

        for (Query temp : q) {
            int x = temp.x;
            int m = temp.m;
            int i = temp.i;

            while (index < nums.length && nums[index] <= m) {
                t.insert(nums[index]);
                index++;
            }

            if (index == 0) {
                // there is no element in the trie right now
                ans[i] = -1;
            } else {
                ans[i] = t.findMax(x);
            }

        }

        return ans;
    }
}

class Query {
    int x, m, i;

    Query(int x, int m, int i) {
        this.x = x;
        this.m = m;
        this.i = i;
    }
}

// bit-wise waale k liye Trie
class Node {
    Node one;
    Node zero;
    int num;
    boolean endsWith;

    boolean containsKey(int bit) {
        if (bit == 1)
            return one != null;
        else
            return zero != null;
    }

    void put(int bit, Node node) {
        if (bit == 1)
            one = node;
        else
            zero = node;
    }

    Node get(int bit) {
        return bit == 1 ? one : zero;
    }
}

class Trie {
    Node root;

    Trie() {
        root = new Node();
    }

    void insert(int num) {
        Node temp = root;
        for (int i = 31; i >= 0; i--) {
            int bit = ((num & (1 << i)) != 0) ? 1 : 0;
            if (!temp.containsKey(bit))
                temp.put(bit, new Node());
            temp = temp.get(bit);
        }
        temp.num = num;
    }

    int findMax(int num) {
        Node temp = root;
        for (int i = 31; i >= 0; i--) {
            int bit = ((num & (1 << i)) != 0) ? 1 : 0;
            int req = bit == 1 ? 0 : 1;
            if (!temp.containsKey(req))
                temp = temp.get(bit);
            else
                temp = temp.get(req);
        }
        return num ^ temp.num;
    }
}
