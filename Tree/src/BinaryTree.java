import printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 *
 * @author 97557
 * @date 2023/05/31
 */
@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo {

    protected int size;
    protected Node<E> root;

    protected static class Node<E> {
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

        public Boolean isLeftChild(){
            return (parent != null) && (this == parent.left);
        }

        public Boolean isRightChild(){
            return (parent != null) && (this == parent.right);
        }

    }
    protected Node<E> createNode(E element, Node<E> parent){
        return new Node<>(element,parent);
    }

    /**
     * 前驱节点
     *
     * @param node 节点
     * @return {@link Node}<{@link E}>
     */
    protected Node<E> forerunnerNode(Node<E> node) {
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

    /**
     * 后继节点
     *
     * @param node 节点
     * @return {@link Node}<{@link E}>
     */
    public Node<E> successorNode(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> postdesessor = node.right;
        if (postdesessor != null) {
            while (postdesessor.left != null) {
                postdesessor = postdesessor.left;
            }
            return postdesessor;
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

    public abstract static class Visitor<E> {
        boolean stop;

        abstract Boolean visit(E element);
    }
    public interface Visitor1<E> {
        void visit(E element);
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

}
