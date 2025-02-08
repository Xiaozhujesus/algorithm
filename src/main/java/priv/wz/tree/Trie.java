package priv.wz.tree;

/**
 * 实现 trie 树
 */
public class Trie {

    private Node root;

    public Trie() {
        this.root = new Node();
        this.root.childs = new Node[26];
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        Node curNode = root;
        for (char cur : word.toCharArray()) {
            if (curNode.childs == null) {
                curNode.childs = new Node[26];
                curNode.childs[cur - 'a'] = new Node(cur);
            } else if (curNode.childs[cur - 'a'] == null) {
                curNode.childs[cur - 'a'] = new Node(cur);
            }
            curNode = curNode.childs[cur - 'a'];
        }
        curNode.wold = true;
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        Node curNode = root;
        for (char cur : word.toCharArray()) {
            if (curNode.childs == null || curNode.childs[cur - 'a'] == null) {
                return false;
            }
            curNode = curNode.childs[cur - 'a'];
        }
        return curNode.wold;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null) {
            return false;
        }
        Node curNode = root;
        for (char cur : prefix.toCharArray()) {
            if (curNode.childs == null || curNode.childs[cur - 'a'] == null) {
                return false;
            }
            curNode = curNode.childs[cur - 'a'];
        }
        return true;
    }

    class Node {
        char c;
        Node[] childs;
        boolean wold;

        public Node() {
        }

        public Node(char c) {
            this.c = c;
        }
    }
}
