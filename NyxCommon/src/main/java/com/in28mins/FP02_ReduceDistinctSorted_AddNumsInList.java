package com.in28mins;

import java.util.Comparator;
import java.util.List;

public class FP02_ReduceDistinctSorted_AddNumsInList {

    static final List<Integer> NUMS = List.of(12,9,13,4,6,2,4,12,15);
    static final List<String> STRINGS = List.of("Spring", "API", "Kubernetes", "Microservice", "Spring Boot");

    public static void main(String[] args) {
//        System.out.println(addNumsInListStructured(NUMS));          // #1
//        System.out.println(addNumsInListFunctional(NUMS));          // #2
//        System.out.println(addNumsInListLambda(NUMS));              // #3
//
//        System.out.println("distinctNumsInListLambda :: ");
//        distinctNumsInListLambda(NUMS);                             // #4

        sortedList(NUMS, STRINGS);                                    // #5
    }

    /**
     * 5. Sorted:
     */
    private static void sortedList(List<Integer> numbers, List<String> strings) {
        System.out.println("\nSorted Ints :: ");
        numbers.stream().sorted().forEach(System.out::println);

        System.out.println("\nSorted Strings #1 :: ");
        strings.stream().sorted().forEach(System.out::println);

        System.out.println("\nSorted Strings #2 :: ");
        strings.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);

        System.out.println("\nReverse Sorted Strings :: ");
        strings.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        System.out.println("\nCUSTOM Sorted Strings :: ");
        strings.stream().sorted(Comparator.comparing(str -> str.length())).forEach(System.out::println);

    }
    /**
     * 4. Distinct:
     */
    private static void distinctNumsInListLambda(List<Integer> numbers) {
        numbers.stream().distinct().forEach(System.out::println);
    }

    /**
     * 3. Lambda Approach:
     */
    private static int addNumsInListLambda(List<Integer> numbers) {
        // What to do?
        return numbers.stream()
                // Stream of values => One result Value
                // Combine into 1 result => One Value
                // 0 and FP02_AddNumsInList::sum
//              .reduce(0, (aggr,nextNum)-> aggr + nextNum);      // Option #1
                .reduce(0, Integer::sum);                 // Option #2
    }
    // ==============================================================================
    /**
     * 2. Functional Approach:
     */
    private static int addNumsInListFunctional(List<Integer> numbers) {
        // What to do?
        return numbers.stream()
                // Stream of values => One result Value
                // Combine into 1 result => One Value
                // 0 and FP02_AddNumsInList::sum
                .reduce(0, FP02_ReduceDistinctSorted_AddNumsInList::sum);
    }
    private static int sum(int aggregate, int nextNum) {
        return aggregate + nextNum;
    }
    // ==============================================================================
    /**
     * 1. Traditional Approach:
     */
    private static int addNumsInListStructured(List<Integer> numbers) {
        // How to loop the nums?
        // How to store the sum?
        int sum = 0;
        for(int i: numbers) {
            sum += i;
        }
        return sum;
    }

}