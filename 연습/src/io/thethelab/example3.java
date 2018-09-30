package io.thethelab;

import processing.data.JSONObject;

public class example3 {
    public static void main(String[] args) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.setString("type", "connect");

        JSONObject stateJsonObject = new JSONObject();
        stateJsonObject.setString("name", "jaejong");
        stateJsonObject.setInt("character", 10);

        JSONObject positionJsonObject = new JSONObject();
        positionJsonObject.setFloat("x", 10.0f);
        positionJsonObject.setFloat("y", 20.0f);

        stateJsonObject.setJSONObject("position", positionJsonObject);

        jsonObject.setJSONObject("state", stateJsonObject);

        System.out.println(jsonObject.toString());

        System.out.println(jsonObject.getString("type"));


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        maven.JSONObject jsonObject2 = new maven.JSONObject();
        jsonObject2.put("type", "connect");

        maven.JSONObject stateJsonObject2 = new maven.JSONObject();
        stateJsonObject2.put("name", "jaejong");
        stateJsonObject2.put("character", 10);

        maven.JSONObject positionJsonObject2 = new maven.JSONObject();
        positionJsonObject2.put("x", 10.0f);
        positionJsonObject2.put("y", 20.0f);

        stateJsonObject2.put("position", positionJsonObject2);

        jsonObject2.put("state", stateJsonObject2);

        System.out.println(jsonObject2.toString());
        System.out.println(jsonObject2.get("type"));


    }
}
