import printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {

        Integer[] data = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 1
        };

        AvlTree<Integer> avlTree = new AvlTree<>();
        for (Integer datum : data) {
            avlTree.add(datum);
        }
        BinaryTrees.print(avlTree);
        //bst.preOrder();
        //bst.inOrderTraversal();
        //bst.postOrderTraversal();
        //bst.levelOrderTraversal();
        //System.out.println(bst.isComplete());
//        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public Boolean visit(Integer element) {
//                System.out.print(element+"_");
//                return false;
//            }
//        });
//        System.out.println();
//        bst.postOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public Boolean visit(Integer element) {
//                System.out.print(element+"_");
//                return element == 4;
//            }
//        });
//        System.out.println();
//        bst.inOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public Boolean visit(Integer element) {
//                System.out.print(element+"_");
//                return element == 4;
//            }
//        });
//        System.out.println();
//        bst.preOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public Boolean visit(Integer element) {
//                System.out.print(element+"_");
//                return element == 9;
//            }
//        });
    }
}