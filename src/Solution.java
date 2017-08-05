import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {

        public static class TrieNode {

        private char letter;
        private boolean isEnd;
        private TrieNode[] children = new TrieNode[26];
        private int wordsFromHere;

        public TrieNode(char c) {
            letter = c;
            isEnd = false;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            wordsFromHere = 1;
        }

    }

    public static class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode(' ');
            //System.out.println("root obj: " + root.toString());
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (char ch : word.toCharArray()) {
                if (cur.children[ch - 'a'] == null) {
                    cur.children[ch - 'a'] = new TrieNode(ch);
                }
                else {
                	cur.children[ch - 'a'].wordsFromHere++;
                }
                cur = cur.children[ch - 'a'];
                //System.out.println("this letter: " + ch + " ,count: " + cur.wordsFromHere + ", TrieNode obj id:" + cur.toString());
            }
            cur.isEnd = true;
        }

        /*
        public int count(TrieNode cur) {
            if (cur == null) {
                return 0;
            }
            //System.out.println("cur node letter: " + cur.letter);
            int nodes = 0;
            if (cur.isEnd) {
                nodes++;
            }
            for (int i = 0; i < 26; i++) {
                nodes += count(cur.children[i]);
            }
            return nodes;
        }
        */
        
        public int count(TrieNode node) {
        	//System.out.println("the cur TrieNode found : " + (node == null ? "null" : node.toString()));
            return node == null ? 0 : node.wordsFromHere;
        }
        
        public TrieNode findNode(String prefix) {
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                if (cur.children[ch - 'a'] == null) {
                    return null;
                }
                else {
                    cur = cur.children[ch - 'a'];
                }
            }
            return cur;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Trie trie = new Trie();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            if (op.equals("add")) {
                trie.insert(contact);
            }
            else {
                System.out.println(trie.count(trie.findNode(contact)));
            }
        }
    }
}
