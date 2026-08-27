package question208;

import java.util.Scanner;

/**
 * 208. 实现Trie（前缀树）
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补全和拼写检查。
 * 请你实现 Trie 类：
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *                  root
 *                 /    \
 *                a      b
 *                |      |
 *                p      a
 *                |      |
 *                p      n
 *               / \      |
 *              l   ?     a
 *              |         |
 *              e         n
 *              |
 *              ...
 */
public class Trie {

    private boolean isEnd;  // 用于标记该节点是否为叶子节点 用于搜索前缀是否存在
    private Trie[] next;    // 一个节点代表一个字符  一个节点可能会有多个子节点所以用数组表示

    public Trie() {
        this.isEnd = false;
        this.next = new Trie[26];
    }

    public void insert(String word) {
        Trie node = this;
        for(char c : word.toCharArray()){
            if(node.next[c - 'a'] == null){
                node.next[c - 'a'] = new Trie();
            }
            node = node.next[c - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = this;
        for(char c : word.toCharArray()){
            if(node.next[c - 'a'] == null){
                return false;
            }
            node = node.next[c - 'a'];
        }
        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie node = this;
        for(char c : prefix.toCharArray()){
            if(node.next[c - 'a'] == null){
                return false;
            }
            node = node.next[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 *
 * ACM 模式：
 * 输入：第一行为一个整数 n，表示操作次数；接下来 n 行，每行一个操作及其参数，例如：
 * 5
 * insert apple
 * search apple
 * search app
 * startsWith app
 * insert app
 * 输出：每次 search / startsWith 操作的结果（true / false），一行一个，例如：
 * true
 * false
 * true
 */
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();          // 操作次数
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            String op = scanner.next();     // 操作名：insert / search / startsWith
            String word = scanner.next();   // 操作的参数
            switch (op) {
                case "insert":
                    trie.insert(word);
                    break;
                case "search":
                    System.out.println(trie.search(word));
                    break;
                case "startsWith":
                    System.out.println(trie.startsWith(word));
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }
}