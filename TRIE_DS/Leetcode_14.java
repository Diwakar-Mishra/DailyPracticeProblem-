import java.util.*;

public class Leetcode_14 {
    public static void main(String[] args) {
        String[] strs = { "flower", "flow", "flight" };
        Solution obj = new Solution();
        System.out.println(obj.longestCommonPrefix(strs));
    }
}

class Solution {
    public String longestCommonPrefix(String[] strs) {
        // // method1
        // Arrays.sort(strs);
        // int n = strs.length;
        // int len = 0;
        // for(int i=0;i<strs[0].length();i++){
        // if(strs[0].charAt(i) != strs[n-1].charAt(i)) break;
        // len++;
        // }
        // return strs[0].substring(0,len);
        // method-2
        // kyuki isme multiple strings given hai and isme un multiple strings k beech
        // mai humko common prefix chaahiye => toh trie ka use kar sakte hai
        Trie t = new Trie();
        int n = strs.length;
        Arrays.sort(strs);
        t.insert(strs[n - 1]);
        int len = 0;
        Node temp = t.root;
        for (char ch : strs[0].toCharArray()) {
            if (!temp.containsKey(ch))
                return strs[0].substring(0, len);
            len++;
            temp = temp.get(ch);
        }
        return strs[0].substring(0, len);
    }
}

// meraa trie ka boilerplate
class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch))
                node.put(ch, new Node());
            node = node.get(ch);
            node.increaseStartsWith();
        }
        node.increaseEndsWith();
    }

    public int countWordsEqualTo(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch))
                return 0;
            node = node.get(ch);
        }
        return node.getEndsWith();
    }

    public int countWordsStartingWith(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch))
                return 0;
            node = node.get(ch);
        }
        return node.getStartsWith();
    }

    public void erase(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            node = node.get(ch);
            node.decreaseStartsWith();
        }
        node.decreaseEndsWith();
    }

}

class Node {
    Node[] links;
    int endsWith;
    int startsWith;

    Node() {
        this.links = new Node[26];
        this.endsWith = 0;
        this.startsWith = 0;
    }

    boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    Node get(char ch) {
        return links[ch - 'a'];
    }

    void put(char ch, Node node) {
        links[ch - 'a'] = node;
    }

    void increaseStartsWith() {
        startsWith++;
    }

    void decreaseStartsWith() {
        startsWith--;
    }

    void increaseEndsWith() {
        endsWith++;
    }

    void decreaseEndsWith() {
        endsWith--;
    }

    int getStartsWith() {
        return startsWith;
    }

    int getEndsWith() {
        return endsWith;
    }
}
