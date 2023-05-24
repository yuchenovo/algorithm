import Tree.tree;

import static Tree.tree.constructFromPrePost;

public class test {


    public static void main(String[] args) {
//        Integer[] indices = new Integer[10];
//        int[] nums = new int[]{9,10,8,4,5,6,7,1,2,3};
//        for (int i = 0; i < 10; i++) {
//            indices[i] = i;
//        }
//        Arrays.sort(indices, (a, b) -> nums[b] - nums[a]);
//        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[indices[i]]);
//        }
//        tree.TreeNode node = new tree.TreeNode(1);
//        node.left = new tree.TreeNode(2);
//        node.right = new tree.TreeNode(3);
//        node.left.left = new tree.TreeNode(4);
//        node.left.right = new tree.TreeNode(5);
        int[] preorder = new int[]{2,1};
        int[] postorder = new int[]{1,2};
        //node.right.right = new tree.TreeNode(6);
        //System.out.println(minDepth(node));
        constructFromPrePost(preorder,postorder);
    }
}
