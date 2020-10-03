package com.MidtermPrep;

//import com.MidtermPrep.util.ArrayList;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int first;
        int second;

        first = scanner.nextInt();
        second = scanner.nextInt();

        int total = first + second - 1;

        int[] result = new int[2];

        result[0] = total - first;
        result[1] = total - second;

        System.out.println(Arrays.toString(result));
//        Test.test();
    }
}
