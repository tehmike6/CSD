package csd.uoc.gr.A32;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

class a24 {
    private static void calculate(ArrayList<Integer> list, Predicate<Integer> pre){
        list.stream().filter(pre).forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }
    public static void main(String[] a) {
        final int K = 20;
        ArrayList<Integer> items = new ArrayList<>();
        System.out.println("Initial list: ");
        for (int i = 0; i < K; i++) {
            items.add(new Random().nextInt(100) + 1);
            System.out.print(items.get(i) + " ");
        }
        System.out.print("\n");

        // start
        int val1 = 15;
        System.out.println("Numbers bigger than " + val1 + ":");
        for (int i = 0; i < K; i++) {
            if (items.get(i) > val1) {
                System.out.print(items.get(i) + " ");
            }
        }
        System.out.print("\n");
        calculate(items, item -> item > val1);

        System.out.println("Numbers smaller than " + val1 + ":");
        for (int i = 0; i < K; i++) {
            if (items.get(i) < val1) {
                System.out.print(items.get(i) + " ");
            }
        }
        System.out.print("\n");
        calculate(items,item -> item < val1);

        System.out.println("Numbers equal to 2 or between 10 and 20: ");
        for (int i = 0; i < K; i++) {
            if ((items.get(i) >= 10 && items.get(i) <= 20) || items.get(i) == 2) {
                System.out.print(items.get(i) + " ");
            }
        }
        System.out.print("\n");
        calculate(items, item -> item == 2 || (item > 10 && item < 20));
        // end
    }
}

// This class contains only the implementation of my code.
class new_a24{

    private static void calculate(ArrayList<Integer> list, Predicate<Integer> pre){
        list.stream().filter(pre).forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }

    public static void main(String[] args) {
        final int K = 20;
        int val1 = 15;
        ArrayList<Integer> items = new ArrayList<>();
        System.out.println("Initial list: ");
        for (int i = 0; i < K; i++) {
            items.add(new Random().nextInt(100) + 1);
            System.out.print(items.get(i) + " ");
        }
        System.out.print("\n");
        // Start
        System.out.println("Numbers bigger than " + val1 + ":");
        calculate(items, item -> item > val1);

        System.out.println("Numbers smaller than " + val1 + ":");
        calculate(items,item -> item < val1);

        System.out.println("Numbers equal to 2 or between 10 and 20: ");
        calculate(items, item -> item == 2 || (item > 10 && item < 20));
        //end
    }
}