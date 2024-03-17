package com.in28mins;

import java.util.List;
import java.util.function.*;

public class FP04_FunctionalInterfaces {

    static final List<Integer> NUMS = List.of(12,9,13,4,6,2,15);
    static final List<String> STRINGS = List.of("Spring", "API", "Kubernetes", "Microservice", "Spring Boot");

    public static void main(String[] args) {
        //predicate_BehaviourParameterization(NUMS);                // #1
        other_BehaviourParameterization(NUMS);                      // #2
        primitive_BehaviourParameterization(NUMS);                  // #4
        exploringMethodReferences();                                // #4
    }

    /**
     * 4. Exploring MethodReferences
     */
    private static void exploringMethodReferences() {
        STRINGS.stream()
//              .map(str -> str.toUpperCase())
                .map(String::toUpperCase)           // Refers in String class: public String toUpperCase()
                .forEach(System.out::println);      // Refers to             : public void println(String x)

        // Constructor References:
        Supplier<String> supplier1 = () -> new String();
        Supplier<String> supplier2 = String::new;
    }
    /**
     * 3. Behaviour Parametrization - Primitive Functional
     * IntPredicate
     * IntFunction
     * IntUnaryOperator,    IntBinaryOperator
     * IntConsumer
     * IntSupplier
     * IntToDoubleFunction, IntToLongFunction
     */
    private static void primitive_BehaviourParameterization(List<Integer> numbers) {

    }

    /**
     * 2. Behaviour Parametrization -
     * BiPredicate,
     * Function,      BiFunction,
     * UnaryOperator, BinaryOperator
     * Consumer,      BiConsumer
     * Supplier
     */
    private static void other_BehaviourParameterization(List<Integer> numbers) {
        BiPredicate<Integer, Integer> bothEvenPredicate = (x,y) -> x%2==0&&y%2==0;
        System.out.println(bothEvenPredicate.test(2,5));

        // ==================
        Function<Integer, Integer>  squareFunction      = x -> x*x;
        System.out.println(squareFunction.apply(2));

        Function<Integer, String>   squareFunctionString= x -> "First param::"+x;
        System.out.println(squareFunctionString.apply(3));

        BiFunction<Float, Integer, String> biFunction   = (x,y) -> "First param::"+x+"\tSecond param::"+y;
        System.out.println(biFunction.apply(3.14f, 3));

        // ==================
        UnaryOperator<Integer>      squareUnaryOperator    = x -> x*x;                  // 1 param, RETURN same type
        System.out.println(squareUnaryOperator.apply(5));

        BinaryOperator<Integer>     sumBinaryOperator   = (x, y) -> (x+y);              // 2 param of same type, RETURN same type
        System.out.println(sumBinaryOperator.apply(2,3));

        // ==================
        Consumer<Integer>           sysoutConsumer      = x -> System.out.println(x);   // ACCEPTS 1 param, RETURN void
        sysoutConsumer.accept(55);

        BiConsumer<String, Integer> sysoutBiConsumer    = (x,y) -> System.out.println(x +" "+y);
        sysoutBiConsumer.accept("Hi", 5);

        // ==================
        Supplier<Integer>           randomIntSupplier   = ()-> 2;                       // No Input, RETURN Something
        System.out.println(randomIntSupplier.get());
    }

    /**
     * 1. Behaviour Parametrization - Predicate, Function, Consumer
     * Here, example is for "Predicate".
     * Similarly it can be done for other Functional Interfaces - Function and Consumer
     */
    private static void predicate_BehaviourParameterization(List<Integer> numbers) {
        System.out.println("Using Even predicate");
        Predicate<Integer> evenPredicate = x -> x%2==0;
        filterAndPrint(numbers, evenPredicate);

        System.out.println("Using Odd predicate");
        Predicate<Integer> oddPredicate = x -> x%2!=0;
        filterAndPrint(numbers, oddPredicate);
    }
    private static void filterAndPrint(List<Integer> numbers, Predicate<Integer> predicate) {
        numbers.stream()
                .filter(predicate)
                .forEach(System.out::println);
    }

}