package com.in28mins;

import java.util.List;

public class FP02_AddNumsInList {

    static final List<Integer> NUMS = List.of(12,9,13,4,6,2,4,12,15);

    public static void main(String[] args) {
        System.out.println(addNumsInListStructured(NUMS));          // #1
        System.out.println(addNumsInListFunctional(NUMS));          // #2
        System.out.println(addNumsInListLambda(NUMS));              // #3
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
                .reduce(0, (a,b)-> a+b);
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
                .reduce(0, FP02_AddNumsInList::sum);
    }
    private static int sum(int a, int b) {
        return a+b;
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