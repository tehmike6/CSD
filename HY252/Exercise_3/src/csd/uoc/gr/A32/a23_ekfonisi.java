package csd.uoc.gr.A32;

import java.util.ArrayList;
import java.util.function.Consumer;

class a23_ekfonisi {
    public static void main(String[] args) {
        ArrayList<Double> items = new ArrayList<>();
        for (int i = 0; i < 30; i++)
            items.add(i * i / 3.3);
        // Start
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;
        double sum = 0.0;
        for (Double item : items) {
            if (item > max) max = item;
            if (item < min) min = item;
            sum += item;
        }
        System.out.println("Minimum = " + min);
        System.out.println("Maximum = " + max);
        System.out.println("Average = " + sum/(double)items.size());
        //end
    }
}

class new_a23_ekfonisi{
    public static void main(String[] args) {
        ArrayList<Double> items = new ArrayList<>();
        for(int i = 0; i < 30; i++)
           items.add(i*i / 3.3);
        // Start
        System.out.println("Minimum = " + items.stream().min(Double::compare).orElse(0.0));
        System.out.println("Maximum = " + items.stream().max(Double::compare).orElse(0.0));
        System.out.println("Average = " + items.stream().reduce(0.0, (x,y) -> x = x + y)/(double)items.size());
        // End
    }
}