package com.nikhil.nyx.java8;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Product {
    private int     id;
    private String  name;
    private float   price;
    private List<String> models;

}