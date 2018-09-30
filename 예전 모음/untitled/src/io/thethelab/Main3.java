package io.thethelab;

import java.util.ArrayList;
import java.util.List;

public class Main3 {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        strings.add("hi");
        strings.add("hello");

        System.out.println(strings);

        strings.remove("hi");

        System.out.println(strings);


    }


}
