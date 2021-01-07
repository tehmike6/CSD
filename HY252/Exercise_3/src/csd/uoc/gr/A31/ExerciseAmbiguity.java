package csd.uoc.gr.A31;

import java.util.function.Predicate;

interface LongPredicate{
    public boolean test(int v);
}

public class ExerciseAmbiguity {
    private static void method(Predicate<Long> p){
        System.out.print("Predicate");
    }

    private static void method(LongPredicate p){
        System.out.print("IntPredicate");
    }
    /**
     * We encounter an error because we do not specify which method we want to call, the Lambda Expression doesn't know
     * which type is the argument x. If we want to call the method
     * with the parameter Predicate<Long> we must use a cast and cast it to (Predicate<Long>) or if we want to use
     * the method with the parameter LongPredicate we must cast it to (LongPredicate).
     * */
    public static void main(String args[]){
        method((LongPredicate) (x) -> true);
    }
}