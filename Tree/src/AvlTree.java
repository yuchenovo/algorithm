import java.util.Comparator;

/**
 * 平衡二叉树
 *
 * @author 97557
 * @date 2023/06/05
 */
public class AvlTree<E> extends BinarySearchTree<E> {
    private static class AvlNode<E> extends Node<E> {
        int height = 1;

        public AvlNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 计算平衡因子
         *
         * @return int
         */
        public int balanceFactor() {
            int leftHeight = (left == null) ? 0 : ((AvlNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AvlNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新高度
         */
        public void updateHeight() {
            int leftHeight = (left == null) ? 0 : ((AvlNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AvlNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 高子树
         *
         * @return {@link Node}<{@link E}>
         */
        public Node<E> tallerChild() {
            int leftHeight = (left == null) ? 0 : ((AvlNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AvlNode<E>) right).height;
            if (leftHeight > rightHeight) {
                return left;
            }
            if (leftHeight < rightHeight) {
                return right;
            }
            return isLeftChild() ? left : right;
        }
    }

    /**
     * 是否平衡
     *
     * @param node 节点
     * @return {@link Boolean}
     */
    private Boolean isBalanced(Node<E> node) {
        return Math.abs(((AvlNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 更新高度
     */
    private void updateHeight(Node<E> node) {
        ((AvlNode<E>) node).updateHeight();
    }

    /**
     * 重新平衡
     *
     * @param grand 大
     */
    private void reBalance(Node<E> grand) {
        Node<E> parent = ((AvlNode<E>) grand).tallerChild();
        Node<E> node = ((AvlNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            //LL
            if (node.isLeftChild()) {
                rotateLeft(grand);
            } else { //LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            //RR
            if (node.isRightChild()) {
                rotateRight(grand);
            } else { //RL
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }

    /**
     * 左旋转
     *
     * @param node 节点
     */
    private void rotateLeft(Node<E> node) {

    }

    /**
     * 向右旋转
     *
     * @param node 节点
     */
    private void rotateRight(Node<E> node) {

    }

    public AvlTree() {
        this(null);
    }

    public AvlTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AvlNode<>(element, parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                reBalance(node);
            }
        }
    }
}
