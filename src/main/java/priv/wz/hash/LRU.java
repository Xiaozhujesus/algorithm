package priv.wz.hash;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    private class Node {
        int key;
        int val;
        Node pre;
        Node next;

        Node() {
        }

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node dummyHead;
    private Node dummyTail;
    private int capacity;
    private Map<Integer, Node> cache;

    public LRU(int capacity) {
        dummyHead = new Node();
        dummyTail = new Node();
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
    }

    public int get(int key) {
        Node cur = cache.get(key);
        if (cur == null) {
            return -1;
        } else {
            moveToHead(cur);
            return cur.val;
        }
    }

    public void put(int key, int val) {
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
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
        cache.remove(node.key);
    }

    private void removeTail() {
        remove(dummyTail.pre);
    }

    private void addToHead(Node node) {
        node.pre = dummyHead;
        node.next = dummyHead.next;
        dummyHead.next.pre = node;
        dummyHead.next = node;
        cache.put(node.key, node);
    }
}
