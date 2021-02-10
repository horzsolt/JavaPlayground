package horzsolt.javaplayground;

import java.util.*;

public class JavaDequeue {
    public static void main(String[] args) {
        test(6, 3, Arrays.asList(5,3,5,2,3,2));
    }

    public static void test(int n, int m, List<Integer> nums) {

        Deque cache = new ArrayDeque<>();
        Deque deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            if (i % m == 0) {

                deque.clear();
            }
            int num = nums.get(i);
            System.out.println(num);
        }
    }
}
