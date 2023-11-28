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
        //*******构建测试数据*********************//
        ListNode listNode = new ListNode(1);
        for (int i = 0; i < 9; i++) {
            ListNode p;
            for (p = listNode; p.next != null; p = p.next) {
            }
            p.next = new ListNode(9, null);
        }
        //**************************************//
//        deleteNode(new ListNode(3));
//        ListNode node = removeElements(listNode, 2);
//        ListNode node1 = getKthFromEnd(listNode, 2);
//        ListNode node2 = middleNode(listNode);
        ListNode l1 = new ListNode(9);
        ListNode l2 = listNode;
        ListNode node = addTwoNumbers(l1, l2);
    }

    /**
     * 876. 链表的中间结点
     * 快慢指针
     *
     * @param head 头
     * @return {@link ListNode}
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
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
        if (head == null) {
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
//          *********递归*************
//        if (head == null) {
//            return head;
//        }
//        head.next = removeElements(head.next, val);
//        return head.val == val ? head.next : head;

        //添加虚拟头节点
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
    }

    /**
     * 141. 环形链表
     *
     * @param head 头
     * @return boolean
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
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
        while (head != null) {
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

    /**
     * 2. 两数相加
     *
     * @param l1 l1
     * @param l2 l2
     * @return {@link ListNode}
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode l1head = l1;
        ListNode l2head = l2;
        ListNode listNode = new ListNode(0);
        ListNode p = listNode;
        long sum = 0L, sum1 = 0L, sum2 = 0L;
        for (int i = 0; l1head != null; i++) {
            sum1 += (long) l1head.val * (int) Math.pow(10, i);
            l1head = l1head.next;
        }
        for (int i = 0; l2head != null; i++) {
            sum2 += (long) l2head.val * (int) Math.pow(10, i);
            l2head = l2head.next;
        }
        sum = sum1 + sum2;
        if (sum == 0) {
            return new ListNode(0);
        }
        int i = 0;
        while (sum >= (int) Math.pow(10, i)) {
            int val = (int) ((sum / (int) Math.pow(10, i)) % 10);
            p.next = new ListNode(val, null);
            i++;
            p = p.next;
        }
        return listNode.next;
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
        }
        if(carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }
}
