package others;

/**
 * 509-斐波那契数
 *
 * @author 97557
 * @date 2023/03/10
 */
public class fib {
    public static void main(String[] args) {
        System.out.println(fib(45));
        System.out.println(fib2(45));
    }

    public static int fib(int value) {
        if (value < 2) {
            return value;
        }
        return fib(value - 1) + fib(value - 2);
    }

    public static int fib2(int value) {
        if (value < 2) {
            return value;
        }
        int first = 0;
        int second = 1;
        for (int i = 0; i < value - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }
}
