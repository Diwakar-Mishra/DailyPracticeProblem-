import java.util.*;

public class Leetcode_421 {
    public static void main(String[] args) {
        int[] nums = { 3, 10, 5, 25, 2, 8 };
        Solution obj = new Solution();
        System.out.println(obj.findMaximumXOR(nums));
    }
}

class Solution {
    public int findMaximumXOR(int[] nums) {
        Trie t = new Trie();
        int n = nums.length;
        t.insert(nums[n - 1]);
        int maxi = 0;
        for (int i = n - 2; i >= 0; i--) {
            maxi = Math.max(maxi, t.findMax(nums[i]));
            t.insert(nums[i]);
        }
        return maxi;
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