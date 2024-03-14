package com.in28mins;

import java.util.List;

public class FP01_IterateNumListApproach {

    static final List<Integer> NUMS = List.of(12,9,13,4,6,2,4,12,15);

    public static void main(String[] args) {

//        printAllNumsInListStructured(NUMS);           // #1
//        printAllNumsInListFunctional(NUMS);           // #2
//        printAllNumsInListLambda(NUMS);               // #3
//        printEvenNumsInListFunctional(NUMS);          // #4
//        printEvenNumsInListLambda(NUMS);              // #5
        printSquaresOfEvenNumsInListLambda(NUMS);       // #6
    }

    /**
     * 6. Lambda Approach:      Print EVEN each element on a new line
     */
    private static void printSquaresOfEvenNumsInListLambda(List<Integer> numbers) {
        // What to do?
        numbers.stream()
                .filter(k -> k%2==0)
                .map(k -> k*k)
                .forEach(System.out::println);
    }
    // ==============================================================================
    /**
     * 5. Lambda Approach:      Print EVEN each element on a new line
     */
    private static void printEvenNumsInListLambda(List<Integer> numbers) {
        // What to do?
        numbers.stream()
                .filter(k -> k%2==0)
                .forEach(k -> System.out.println(k));
    }
    // ==============================================================================
    /**
     * 4. Functional Approach:      Print EVEN each element on a new line
     */
    private static void printEvenNumsInListFunctional(List<Integer> numbers) {
        // What to do?
        numbers.stream()
                .filter(FP01_IterateNumListApproach :: isEven)
                .forEach(System.out::println);
    }
    private static boolean isEven(int number) {
        return number%2==0;
    }
    // ==============================================================================
    /**
     * 3. Lambda Approach:      Print each element on a new line
     */
    private static void printAllNumsInListLambda(List<Integer> numbers) {
        // What to do?
        numbers.stream()
                .forEach(k -> {
                    System.out.println(k);
        });
    }
    // ==============================================================================
    /**
     * 2. Functional Approach:      Print each element on a new line
     */
    private static void printAllNumsInListFunctional(List<Integer> numbers) {
        // What to do?
        numbers.stream()
                .forEach(FP01_IterateNumListApproach::print2);       // This is Method Reference
    }
    private static void print2(int number) {
        System.out.println(number);
    }

    // ==============================================================================
    /**
     * 1. Traditional Approach:     Print each element on a new line
     */
    private static void printAllNumsInListStructured(List<Integer> numbers) {
        // How to loop the nums?
        for(int i: numbers) {
            System.out.println(i);
        }
    }

}