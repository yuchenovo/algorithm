import printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public Boolean isLeaf() {
            return left == null && right == null;
        }

        public Boolean hasTwoChildren() {
            return left != null && right != null;
        }

    }

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void add(E element) {
        checkElement(element);
        //添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        //添加非第一个节点
        Node<E> node = root;
        Node<E> parent = root;
        int compare = 0;
        do {
            compare = compare(element, node.element);
            parent = node;
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        } while (node != null);
        Node<E> newNode = new Node<>(element, parent);
        if (compare > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return true;
    }

    /**
     * 前驱节点
     *
     * @param node 节点
     * @return {@link Node}<{@link E}>
     */
    private Node<E> predesessor1(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> predesessor;
        if (node.left != null) {
            predesessor = node.left;
            while (predesessor.right != null) {
                predesessor = predesessor.right;
            }
            return predesessor;
        } else if (node.parent != null) {
            predesessor = node.parent;
            while (predesessor.parent != null && predesessor == predesessor.parent.left) {
                predesessor = predesessor.parent;
            }
            return predesessor;
        } else {
            return null;
        }
    }

    private Node<E> predesessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> predesessor = node.left;
        if (predesessor != null) {
            while (predesessor.right != null) {
                predesessor = predesessor.right;
            }
            return predesessor;
        }
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    private Node<E> postdesessor1(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> predesessor;
        if (node.right != null) {
            predesessor = node.right;
            while (predesessor.left != null) {
                predesessor = predesessor.left;
            }
            return predesessor;
        } else if (node.parent != null) {
            predesessor = node.parent;
            while (predesessor.parent != null && predesessor == predesessor.parent.right) {
                predesessor = predesessor.parent;
            }
            return predesessor;
        } else {
            return null;
        }
    }
    private Node<E> postdesessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> predesessor = node.right;
        if (predesessor != null) {
            while (predesessor.left != null) {
                predesessor = predesessor.left;
            }
            return predesessor;
        }
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 前序遍历
     *
     * @param visitor 游客们
     */
    public void preOrder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        preOrder(root, visitor);
    }

    private void preOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        preOrder(node.left, visitor);
        preOrder(node.right, visitor);
    }

    /**
     * 中序遍历
     *
     * @param visitor 游客们
     */
    public void inOrder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        inOrder(root, visitor);
    }

    private void inOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        inOrder(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        inOrder(node.right, visitor);
    }

    /**
     * 后序遍历
     *
     * @param visitor 游客们
     */
    public void postOrder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        postOrder(root, visitor);
    }

    private void postOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层序遍历
     *
     * @param visitor 游客们
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) {
                return;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 是否完全二叉树
     *
     * @return boolean
     */
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                leaf = true;
            }
        }
        return true;
    }
//    public boolean isComplete() {
//        if (root == null) {
//            return false;
//        }
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root);
//        boolean leaf = false;
//        while (!queue.isEmpty()) {
//            Node<E> node = queue.poll();
//            if (leaf && !node.isLeaf()) {
//                return false;
//            }
//            if (node.hasTwoChildren()) {
//                queue.offer(node.left);
//                queue.offer(node.right);
//            } else if (node.left == null && node.right != null) {
//                return false;
//            } else {
//                leaf = true;
//                if (node.left != null){
//                    queue.offer(node.left);
//                }
//            }
//        }
//        return true;
//    }

    /**
     * 二叉树高度
     *
     * @param root 根
     * @return int
     */
    public int height(Node<E> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public abstract static class Visitor<E> {
        boolean stop;

        abstract Boolean visit(E element);
    }

    public interface Visitor1<E> {
        void visit(E element);
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void checkElement(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element is null");
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }
//    public void preOrderTraversal() {
//        preOrderTraversal(root);
//    }
//
//    private void preOrderTraversal(Node<E> node) {
//        if (node == null) {
//            return;
//        }
//        System.out.println(node.element);
//        preOrderTraversal(node.left);
//        preOrderTraversal(node.right);
//    }
//
//    public void inOrderTraversal() {
//        inOrderTraversal(root);
//        //inDesOrderTraversal(root);
//    }
//
//    private void inOrderTraversal(Node<E> node) {
//        if (node == null) {
//            return;
//        }
//        inOrderTraversal(node.left);
//        System.out.println(node.element);
//        inOrderTraversal(node.right);
//    }
//
//    private void inDesOrderTraversal(Node<E> node) {
//        if (node == null) {
//            return;
//        }
//        inDesOrderTraversal(node.right);
//        System.out.println(node.element);
//        inDesOrderTraversal(node.left);
//    }
//
//    public void postOrderTraversal() {
//        postOrderTraversal(root);
//    }
//
//    private void postOrderTraversal(Node<E> node) {
//        if (node == null) {
//            return;
//        }
//        postOrderTraversal(node.left);
//        postOrderTraversal(node.right);
//        System.out.println(node.element);
//    }
//
//    public void levelOrderTraversal() {
//        if (root == null) {
//            return;
//        }
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            Node<E> node = queue.poll();
//            System.out.println(node.element);
//            if (node.left != null) {
//                queue.offer(node.left);
//            }
//            if (node.right != null) {
//                queue.offer(node.right);
//            }
//        }
//    }
}
