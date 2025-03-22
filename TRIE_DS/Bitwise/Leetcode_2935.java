
import java.util.*;

public class Leetcode_2935 {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5 };
        Solution solution = new Solution();
        System.out.println(solution.maximumStrongPairXor(nums));
    }
}

class Solution {
    public int maximumStrongPairXor(int[] nums) {
        Arrays.sort(nums);
        int maxi = 0;
        int l = 0;
        int r = 1;
        int n = nums.length;
        Trie t = new Trie();
        t.insert(nums[0]);

        while (r < n) {

            while (r < n && (nums[r] - nums[l] <= nums[l])) {
                t.insert(nums[r]);
                r++;
            }

            // valid window
            maxi = Math.max(maxi, t.findMax(nums[l]));
            t.erase(nums[l]);
            l++;
        }
        while (l < n) {
            maxi = Math.max(maxi, t.findMax(nums[l]));
            t.erase(nums[l]);
            l++;
        }
        return maxi;
    }
}

class Node {
    Node zero, one;
    int startsWith;
    int num;

    boolean containsKey(int bit) {
        return (bit == 1) ? one != null : zero != null;
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
        Node node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num & (1 << i)) != 0 ? 1 : 0;
            if (!node.containsKey(bit))
                node.put(bit, new Node());
            node = node.get(bit);
            node.startsWith++;
        }
        node.num = num;
    }

    int findMax(int num) {
        Node node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num & (1 << i)) != 0 ? 1 : 0;
            int req = (bit == 1) ? 0 : 1;
            if (!node.containsKey(req) || node.get(req).startsWith == 0) {
                // required waali bit aapko nahi milii hai
                // then, jo bhi bit hai usii se kaam chalaao
                node = node.get(bit);
            } else {
                // required bit mil chukii hai
                node = node.get(req);
            }
        }
        return num ^ node.num;
    }

    void erase(int num) {
        Node node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num & (1 << i)) != 0 ? 1 : 0;
            node = node.get(bit);
            node.startsWith--;
        }
    }
}
