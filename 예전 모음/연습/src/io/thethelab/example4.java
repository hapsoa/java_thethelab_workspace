package io.thethelab;

import maven.JSONArray;
import maven.JSONObject;

import java.util.Arrays;

public class example4 {
    public static void main(String[] args) {

        int arr[] = {1, 1, 1, 2, 3};
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body", arr);

        System.out.println(jsonObject);

        Object mapArray = jsonObject.get("body");
        int[] mapArray2 = (int[]) mapArray;

        System.out.println(mapArray);

    }
}
