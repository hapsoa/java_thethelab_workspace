package io.thethelab;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        View view1 = new View("view1");
        View view2 = new View("view2");
        View view3 = new View("view3");
        View view4 = new View("view4");
        View view5 = new View("view5");
        View view6 = new View("view6");
        View view7 = new View("view7");


        List<View> list = new ArrayList<>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        list.add(view4);
        list.add(view5);

        System.out.println(list);


        List<View> bin = new ArrayList<>();
        bin.add(view5);
        bin.add(view6);
        bin.add(view7);

        list.removeAll(bin);

        System.out.println(list);


    }
}
