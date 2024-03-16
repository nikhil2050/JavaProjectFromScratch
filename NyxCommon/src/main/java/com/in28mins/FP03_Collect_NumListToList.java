package com.in28mins;

import java.util.List;
import java.util.stream.Collectors;

public class FP03_Collect_NumListToList {

    static final List<Integer> NUMS = List.of(12,9,13,4,6,2,4,12,15);

    public static void main(String[] args) {
        List<Integer> outList = getSquaredNumList(NUMS);              // #1

    }

    /**
     * 1. Traditional Approach:
     */
    private static List<Integer> getSquaredNumList(List<Integer> numbers) {
        return numbers.stream()
                .map(x -> x*x )
                .collect(Collectors.toList());
    }

}