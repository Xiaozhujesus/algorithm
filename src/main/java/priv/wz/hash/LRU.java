package priv.wz.hash;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    private class Node {
        String key;
        int val;
        Node pre;
        Node next;

        Node() {
        }

        Node(String key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int capacity;
    private Map<String, Node> cache;

    public LRU(int capacity) {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
    }

    public int get(String key) {
        Node cur = cache.get(key);
        if (cur == null) {
            return -1;
        } else {
            moveToHead(cur);
            return cur.val;
        }
    }

    public void put(String key, int val) {
        Node cur = cache.get(key);
        if (cur == null) {
            if (cache.size() == capacity) {
                removeTail();
            }
            addToHead(new Node(key, val));
        } else {
            cur.val = val;
            moveToHead(cur);
        }
    }

    private void moveToHead(Node node) {
        remove(node);
        addToHead(node);
    }

    private void remove(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
        node.pre = null;
        node.next = null;
        cache.remove(node.key);
    }

    private void removeTail() {
        remove(tail.pre);
    }

    private void addToHead(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        cache.put(node.key, node);
    }
}
