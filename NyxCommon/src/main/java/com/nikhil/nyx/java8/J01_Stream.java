package com.nikhil.nyx.java8;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class J01_Stream {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "HP Laptop", 98000f, Arrays.asList("H1", "H2", "H3")));
        productList.add(new Product(2, "Dell Laptop", 30000f, Arrays.asList("D1", "D2")));
        productList.add(new Product(3, "Lenovo Laptop", 97000f, Arrays.asList("L1", "L2", "L3")));
        productList.add(new Product(4, "Sony Laptop", 28000f, Arrays.asList("S1", "S2")));
        productList.add(new Product(5, "Apple Laptop", 90000f, Arrays.asList("A1", "A2")));
        System.out.println("-------------------------- Products added\n");

        System.out.println("-------------------------- Processing (filter,map,collect in depth)");
        Stream<Product> stream  = productList.stream();
        Stream<Product> filter  = stream.filter(p -> p.getPrice()>30000);
        Stream<Float>   map     = filter.map(p -> p.getPrice());
        List<Float>     priceList=map.collect(Collectors.toList());
        System.out.println("priceList :: "+priceList);

        System.out.println("\n-------------------------- Processing done (filter,map,collect in short)");
        List<Float>  priceList2 = productList.stream()
                                    .filter(product -> product.getPrice()>30000)
                                    .map(product -> product.getPrice())
                                    .collect(Collectors.toList());
        System.out.println("priceList :: "+priceList);

        System.out.println("\n-------------------------- Processing done (count)");
        long  totalExpensiveProducts = productList.stream()
                                    .filter(product -> product.getPrice()>30000)
                                    .count();
        System.out.println("totalExpensiveProducts :: "+totalExpensiveProducts);


        System.out.println("\n--------------------------- Processing done (sorted - with Comparator)");
        Comparator<Product> compare = ((p1, p2) -> (p1.getPrice()<p2.getPrice())
                ?-1
                :(p1.getPrice()>p2.getPrice())?1:0);

        List<Product> productListSorted2 = productList.stream()
                .sorted(compare)
                .collect(Collectors.toList());
        System.out.println("productListSorted2 :: "+ objectMapper.writeValueAsString(productListSorted2));


        System.out.println("\n-------------------------- Processing done (min - with Comparator)");
        Product minPriceProduct = productList.stream()
                .min(compare)
                .get();
        System.out.println("minPriceProduct :: "+ objectMapper.writeValueAsString(minPriceProduct));


        System.out.println("\n-------------------------- Processing done (forEach loop)");
        productList.stream().forEach(p -> {
            try {
                System.out.println(objectMapper.writeValueAsString(p));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("\n-------------------------- Convert ArrayList to Array and iterate");
        List<Integer> numList = Arrays.asList(1,3,2,4);
        Integer[] numArr = numList.stream().toArray(Integer[]::new);    // Convert ArrayList to Array
        Stream.of(numArr).forEach(System.out::println);                 // Iterate Array (with StreamAPI)


        System.out.println("\n-------------------------- FlatMap");
        List<String> modelsList = productList.stream()
                .flatMap(p -> p.getModels().stream())
                .collect(Collectors.toList());
        System.out.println("modelsList :: "+ modelsList);

    }
}




