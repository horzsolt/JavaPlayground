package horzsolt.javaplayground;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Distinct_Neighbors {

    //6
    //1 1 2 2 2 3
    //2 1 2 1 2 3 -> YES
    //--------------------
    //11
    //10 1 1 1 1 4 5 6 7 8 9 -> YES
    //6
    //5 1 1 1 1 4 -> NO
    //6
    //5 0 0 0 0 4 -> NO
    //6
    //1 1 0 0 0 3 - > YES
    //6
    //5 -1 -1 -1 -1 4 -> NO
    //6
    //1 1 2 1 1 3 - > YES // 1 2 1

    //https://app.glider.ai/practice/problem/data-structures/distinct-neighbors-in-array/problem

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 0;
        if (sc.hasNext()) {
            n = sc.nextInt();
        }

        int[] arr = new int[n];

        if (sc.hasNext()) {
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                Integer previousValue = map.get(arr[i]);
                map.put(arr[i], previousValue + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        if ((map.isEmpty()) || (map.size() == 1)) {
            System.out.println("YES");
        } else {

            int max = 0;

            for (int i = 0; i < n; ++i)
                if (max < map.get(arr[i]))
                    max = map.get(arr[i]);

            if (max > (n + 1) / 2)
                System.out.println("NO");
            else
                System.out.println("YES");
        }

    }
}
