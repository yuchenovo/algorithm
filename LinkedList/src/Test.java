import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
//        CircleLinkedList<Object> list = new CircleLinkedList<>();
//        list.add(11);
//        list.add(0,22);
        //list.add(33);
        //list.add(44);
        //list.add(4,55);
        System.out.println(multiply(1,918795921));
    }
    public static int multiply(int A, int B) {
        int max = Math.max(A, B);
        int min = Math.min(A, B);
        if (min == 0){
            return 0;
        }
        if (min == 1){
            return max;
        }
        return multiply(A-1,B) + max;
    }
}
