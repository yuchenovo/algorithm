import java.util.Comparator;

/**
 * 二叉搜索树
 *
 * @author 97557
 * @date 2023/05/31
 */
@SuppressWarnings("unchecked")
public class BinarySearchTree<E> extends BinaryTree<E> {
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
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
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;
        //节点度为2
        if (node.hasTwoChildren()) {
            Node<E> predesessor = forerunnerNode(node);
            node.element = predesessor.element;
            node = predesessor;
        }
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            //度为1
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            }
        } else if (node.parent == null) {
            //根节点
            root = null;
        } else {
            //叶子节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            }
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public boolean contains(E element) {
        return node(element) != null;
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
