package io.thethelab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonSimpleExample {
    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {

//            Object obj = parser.parse(new FileReader("test.json"));
            Object obj = parser.parse(new FileReader("Button.json"));

            JSONObject jsonObject = (JSONObject) obj;

            String name = (String) jsonObject.get("Button_One");
            System.out.println(name);

//            long posX = (Long) jsonObject.get("posX");
//            System.out.println(posX);
//
//            long posY = (Long) jsonObject.get("posY");
//            System.out.println(posY);
//
//            long width = (Long) jsonObject.get("width");
//            System.out.println(width);
//
//            long height = (Long) jsonObject.get("height");
//            System.out.println(height);
//
//            // loop array
//            JSONArray color = (JSONArray) jsonObject.get("color");
//            Iterator<Integer> iterator = color.iterator();
//            while (iterator.hasNext()) {
//                System.out.println(iterator.next());
            } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }

}

