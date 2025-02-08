package priv.wz.list;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 *
 * 构造这个链表的深拷贝。深拷贝应该正好由 n 个全新节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应
 * 指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 *
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 返回复制链表的头节点。
 */
public class ListDeepCopy {
    public Node deepCopy(Node head) {
        if (head == null) {
            return null;
        }
        Node ans = new Node(head.val);
        Node srcCur = head;
        Node desCur = ans;
        while (srcCur.next != null) {
            desCur.next = new Node(srcCur.next.val);
            srcCur = srcCur.next;
            desCur = desCur.next;
        }
        srcCur = head;
        desCur = ans;
        Node p1, p2;
        while (srcCur != null) {
            if (srcCur.random == null) {
                desCur.random = null;
            } else {
                p1 = head;
                p2 = ans;
                while (p1 != srcCur.random) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
                desCur.random = p2;
            }
            srcCur = srcCur.next;
            desCur = desCur.next;
        }
        return ans;
    }

    public Node deepCopy2(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node ans = new Node(head.val);
        Node srcCur = head;
        Node desCur = ans;
        map.put(head, ans);
        while (srcCur.next != null) {
            desCur.next = new Node(srcCur.next.val);
            srcCur = srcCur.next;
            desCur = desCur.next;
            map.put(srcCur, desCur);
        }
        srcCur = head;
        desCur = ans;
        while (srcCur != null) {
            if (srcCur.random == null) {
                desCur.random = null;
            } else {
                desCur.random = map.get(srcCur.random);
            }
            srcCur = srcCur.next;
            desCur = desCur.next;
        }
        return ans;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
