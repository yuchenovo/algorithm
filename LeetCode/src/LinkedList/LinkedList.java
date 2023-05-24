package LinkedList;

/**
 * 237 删除链表中的节点
 *
 * @author 97557
 * @date 2023/03/10
 */
public class LinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int x) {
            val = x;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(5);
//        deleteNode(new ListNode(3));
        //ListNode node = removeElements(listNode, 2);
//        ListNode node1 = getKthFromEnd(listNode, 2);
        ListNode node2 = middleNode(listNode);
        System.out.println(node2);
    }

    /**
     * 876. 链表的中间结点
     *
     * @param head 头
     * @return {@link ListNode}
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 83. 删除排序链表中的重复元素
     *
     * @param head 头
     * @return {@link ListNode}
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode temp = head;
        while (temp.next != null) {
            if (temp.next.val == temp.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

    /**
     * 203. 移除链表元素
     *
     * @param head 头
     * @param val  瓦尔
     * @return {@link ListNode}
     */
    public static ListNode removeElements(ListNode head, int val) {
//        if (head == null) {
//            return head;
//        }
//        head.next = removeElements(head.next, val);
//        return head.val == val ? head.next : head;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummyHead.next;
//        ListNode first = head;
//        if (head == null || (head.next == null && head.val != val)){
//            return head;
//        }
//        if (head.next == null && head.val == val){
//            return null;
//        }
//        while (first.next != null){
//            if(head == null){
//                return head;
//            }
//            else if(head.val == val){
//                head = head.next;
//            }else if (first.next.val == val){
//                first.next = first.next.next;
//            }else {
//                first = first.next;
//            }
//        }
//        return head;
    }

    /**
     * 141. 环形链表
     *
     * @param head 头
     * @return boolean
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            if (slow == fast){
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    /**
     * 206. 反转链表
     *
     * @param head 头
     * @return {@link ListNode}
     */
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = null;
        while (head != null){
            ListNode tmp = head.next;
            head.next = newNode;
            newNode = head;
            head = tmp;
        }
        return newNode;
    }

    /**
     * 237 删除链表中的节点
     *
     * @param node 节点
     */
    public static void deleteNode(ListNode node) {
//        while (node.next != null) {
//            ListNode node1 = node.next;
//            node.val = node1.val;
//            if (node1.next == null) {
//                node.next = null;
//            } else {
//                node = node1;
//                node1 = node1.next;
//            }
//        }
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 剑指 Offer 18 删除链表的节点
     *
     * @param head 节点
     */
    public static ListNode deleteNode1(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode first = head;
        while (first.next.val != val) {
            first = first.next;
        }
        first.next = first.next.next;
        return head;
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     *
     * @param head 头
     * @param k    k
     * @return {@link ListNode}
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode size = head, cur = head;
        int i = 0;
        while (size != null) {
            size = size.next;
            i++;
        }
        for (int j = 0; j < i - k; j++) {
            cur = cur.next;
        }
        return cur;
    }
}
