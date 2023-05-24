package Tree;

import javafx.util.Pair;

import java.util.*;


public class tree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root 根
     * @return int
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * 226. 翻转二叉树
     *
     * @param root 根
     * @return {@link TreeNode}
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 144. 二叉树的前序遍历
     *
     * @param root 根
     * @return {@link List}<{@link Integer}>
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    /**
     * 二叉树的中序遍历
     *
     * @param root 根
     * @return {@link List}<{@link Integer}>
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * 145. 二叉树的后序遍历
     *
     * @param root 根
     * @return {@link List}<{@link Integer}>
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

    /**
     * 102. 二叉树的层序遍历
     *
     * @param root 根
     * @return {@link List}<{@link List}<{@link Integer}>>
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list1 = new ArrayList<>();
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                TreeNode node = queue.poll();
                list1.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(list1);
        }
        return list;
    }

    /**
     * 107. 二叉树的层序遍历 II
     *
     * @param root 根
     * @return {@link List}<{@link List}<{@link Integer}>>
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list1 = new ArrayList<>();
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                TreeNode node = queue.poll();
                list1.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(0, list1);
        }
        return list;
    }

    /**
     * 101. 对称二叉树
     *
     * @param root 根
     * @return boolean
     */
    public boolean isSymmetric(TreeNode root) {
        return Symmetric(root, root);
    }

    private boolean Symmetric(TreeNode root, TreeNode root1) {
        if (root == null && root1 == null) {
            return true;
        }
        if (root == null || root1 == null) {
            return false;
        }
        return root.val == root1.val && Symmetric(root.left, root1.right) && Symmetric(root.right, root1.left);
    }

    /**
     * 662. 二叉树最大宽度
     *
     * @param root 根
     * @return int
     */
    public int widthOfBinaryTree(TreeNode root) {
        int res = 1;
        List<Pair<TreeNode, Integer>> list = new ArrayList<>();
        list.add(new Pair<>(root, 1));
        while (!list.isEmpty()) {
            List<Pair<TreeNode, Integer>> tmp = new ArrayList<>();
            for (Pair<TreeNode, Integer> pair : list) {
                TreeNode node = pair.getKey();
                Integer value = pair.getValue();
                if (node.left != null) {
                    tmp.add(new Pair<>(node.left, value * 2));
                }
                if (node.right != null) {
                    tmp.add(new Pair<>(node.right, value * 2 + 1));
                }
            }
            res = Math.max(res, list.get(list.size() - 1).getValue() - list.get(0).getValue() + 1);
            list = tmp;
        }
        return res;
    }

    /**
     * 114. 二叉树展开为链表
     *
     * @param root 根
     */
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    /**
     * N 叉树的先序遍历
     *
     * @param root 根
     * @return {@link List}<{@link Integer}>
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorder1(root, list);
        return list;
    }

    private void preorder1(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            preorder1(root.children.get(i), list);
        }
    }

    /**
     * 590. N 叉树的后序遍历
     *
     * @param root 根
     * @return {@link List}<{@link Integer}>
     */
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorder1(root, list);
        return list;
    }

    private void postorder1(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        for (int i = 0; i < root.children.size(); i++) {
            postorder1(root.children.get(i), list);
        }
        list.add(root.val);
    }

    /**
     * 429. N 叉树的层序遍历
     *
     * @param root 根
     * @return {@link List}<{@link List}<{@link Integer}>>
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list1 = new ArrayList<>();
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                Node node = queue.poll();
                list1.add(node.val);
                for (Node child : node.children) {
                    queue.offer(child);
                }
            }
            list.add(list1);
        }
        return list;
    }

    /**
     * 111. 二叉树的最小深度
     *
     * @param root 根
     * @return int
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<QueueNode> queue = new LinkedList<QueueNode>();
        queue.offer(new QueueNode(root, 1));
        while (!queue.isEmpty()) {
            QueueNode nodeDe = queue.poll();
            TreeNode node = nodeDe.node;
            int depth = nodeDe.depth;
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left != null) {
                queue.offer(new QueueNode(node.left, depth + 1));
            }
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, depth + 1));
            }
        }
        return 0;
    }

    class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    /**
     * 559. N 叉树的最大深度
     *
     * @param root 根
     * @return int
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int dept = 0;
        List<Node> children = root.children;
        for (Node child : children) {
            int depth = maxDepth(child);
            dept = Math.max(dept, depth);
        }
        return dept + 1;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     *
     * @param inorder   有条不紊地进行
     * @param postorder 后根次序
     * @return {@link TreeNode}
     */
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        int n = postorder.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hashMap.put(inorder[i], i);
        }
        return buildTree1(postorder, 0, n - 1, hashMap, 0, n - 1);
    }

    /**
     * 构建tree1
     *
     * @param postorder       后序遍历数组
     * @param postorder_left  后序遍历头
     * @param postorder_right 后序遍历尾
     * @param hashMap         散列映射
     * @param inorder_left    中序遍历头
     * @param inorder_right   中序遍历尾
     * @return {@link TreeNode}
     */
    private TreeNode buildTree1(int[] postorder, int postorder_left, int postorder_right, Map<Integer, Integer> hashMap, int inorder_left, int inorder_right) {
        if (postorder_left > postorder_right) {
            return null;
        }
        int postorder_root = postorder[postorder_right];
        //root为根节点
        TreeNode root = new TreeNode(postorder_root);
        //找到中序遍历的根节点下标
        int inorder_root = hashMap.get(postorder_root);
        root.left = buildTree1(postorder, postorder_left, postorder_right - inorder_right + inorder_root - 1, hashMap, inorder_left, inorder_root - 1);
        root.right = buildTree1(postorder, postorder_right - inorder_right + inorder_root, postorder_right - 1, hashMap, inorder_root + 1, inorder_right);
        return root;
    }

    /**
     * 889. 根据前序和后序遍历构造二叉树
     *
     * @param preorder  预订
     * @param postorder 后根次序
     * @return {@link TreeNode}
     */
    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        if(preorder==null || preorder.length==0) {
            return null;
        }
        if(preorder.length==1) {
            return new TreeNode(preorder[0]);
        }
        TreeNode root = new TreeNode(preorder[0]);
        int n = preorder.length;
        for(int i=0;i<postorder.length;++i) {
            if(preorder[1]==postorder[i]) {
                //根据前序数组第二个元素，确定后序数组左子树范围
                int left_count = i+1;
                //拆分前序和后序数组，分成四份 左开右闭
                int[] pre_left = Arrays.copyOfRange(preorder,1,left_count+1);
                int[] pre_right = Arrays.copyOfRange(preorder,left_count+1,n);
                int[] post_left = Arrays.copyOfRange(postorder,0,left_count);
                int[] post_right = Arrays.copyOfRange(postorder,left_count,n-1);
                //递归执行前序数组左边、后序数组左边
                root.left = constructFromPrePost(pre_left,post_left);
                //递归执行前序数组右边、后序数组右边
                root.right = constructFromPrePost(pre_right,post_right);
                break;
            }
        }
        //返回根节点
        return root;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     *
     * @param preorder 预订
     * @param inorder  有条不紊地进行
     * @return {@link TreeNode}
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hashMap.put(inorder[i], i);
        }
        return buildTree(preorder, hashMap, 0, n - 1, 0, n - 1);
    }

    /**
     * 构建树
     *
     * @param preorder       前序遍历数组
     * @param hashMap        散列映射
     * @param preorder_left  前序遍历头
     * @param preorder_right 前序遍历尾
     * @param inorder_left   中序遍历头
     * @param inorder_right  中序遍历尾
     * @return {@link TreeNode}
     */
    private TreeNode buildTree(int[] preorder, Map<Integer, Integer> hashMap, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        int preorder_root = preorder[preorder_left];
        //root为根节点
        TreeNode root = new TreeNode(preorder_root);
        //找到中序遍历的根节点下标
        int inorder_root = hashMap.get(preorder_root);
        root.left = buildTree(preorder, hashMap, preorder_left + 1, inorder_root - inorder_left + preorder_left, inorder_left, inorder_root - 1);
        root.right = buildTree(preorder, hashMap, inorder_root - inorder_left + preorder_left + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }
}
