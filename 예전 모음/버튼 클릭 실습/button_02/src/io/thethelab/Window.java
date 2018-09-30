package io.thethelab;

import com.sun.corba.se.impl.encoding.CodeSetConversion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import processing.core.PApplet;
import processing.event.MouseEvent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Window extends PApplet{



    private ArrayList<Button> buttonArrayList;

    Button b1, b2, b3;


    @Override
    public void settings() { size(1080, 840);
    }


    @Override
    public void setup() {
        background(0);
        buttonArrayList = new ArrayList<>();

        initButtonWithJSON();

        //JSON 파일을 읽는 코드 작성

        //json[3] 값이 나오면


    }

    void initButtonWithJSON(){


        JSONParser parser = new JSONParser();
        String name;
        float posX;
        float posY;
        float width;
        float height;
        int r;
        int g;
        int b;
        String text;

        try{

            Object obj = parser.parse(new FileReader("/Users/jaejong/Desktop/Button.json"));
            JSONObject jsonObject = (JSONObject) obj;

            Object obj1 = parser.parse(jsonObject.get("Button_One").toString());
            JSONObject jsonObject1 = (JSONObject) obj1;

            name = jsonObject1.get("name").toString();
            posX = parseFloat(jsonObject1.get("posX").toString());
            posY = parseFloat(jsonObject1.get("posY").toString());
            width = parseFloat(jsonObject1.get("width").toString());
            height = parseFloat(jsonObject1.get("height").toString());
            r = parseInt(jsonObject1.get("r").toString());
            g = parseInt(jsonObject1.get("g").toString());
            b = parseInt(jsonObject1.get("b").toString());
            text = jsonObject1.get("text").toString();

            b1 = new Button(name, posX, posY, width, height, r, g, b, text);

            Object obj2 = parser.parse(jsonObject.get("Button_Two").toString());
            JSONObject jsonObject2 = (JSONObject) obj2;

            name = jsonObject2.get("name").toString();
            posX = parseFloat(jsonObject2.get("posX").toString());
            posY = parseFloat(jsonObject2.get("posY").toString());
            width = parseFloat(jsonObject2.get("width").toString());
            height = parseFloat(jsonObject2.get("height").toString());
            r = parseInt(jsonObject2.get("r").toString());
            g = parseInt(jsonObject2.get("g").toString());
            b = parseInt(jsonObject2.get("b").toString());
            text = jsonObject2.get("text").toString();

            b2 = new Button(name, posX, posY, width, height, r, g, b, text);

            Object obj3 = parser.parse(jsonObject.get("Button_Three").toString());
            JSONObject jsonObject3 = (JSONObject) obj3;

            name = jsonObject3.get("name").toString();
            posX = parseFloat(jsonObject3.get("posX").toString());
            posY = parseFloat(jsonObject3.get("posY").toString());
            width = parseFloat(jsonObject3.get("width").toString());
            height = parseFloat(jsonObject3.get("height").toString());
            r = parseInt(jsonObject3.get("r").toString());
            g = parseInt(jsonObject3.get("g").toString());
            b = parseInt(jsonObject3.get("b").toString());
            text = jsonObject3.get("text").toString();

            b3 = new Button(name, posX, posY, width, height, r, g, b, text);

//            buttonArrayList.add(b1);


        } catch (Exception e){
            e.printStackTrace();
        }


        buttonArrayList.add(b1);
        buttonArrayList.add(b2);
        buttonArrayList.add(b3);
    }

    @Override
    public void draw() {

        background(0);
        fill(255,255,255);


        blendMode(ADD);


        update();

        render();

    }

    void update(){

        //충돌검사 버튼 위치랑 <--> 마우스 위치랑 비교하면서 hover되면 버튼의 색을 바꾼다.
       //                                         click되면 화면에 텍스트를 출력한다.
        for(Button b: buttonArrayList) {
            //Hover
            if (b.getPosX() < mouseX &&
                    b.getPosX() + b.getWidth() > mouseX &&
                    b.getPosY() < mouseY &&
                    b.getPosY() + b.getHeight() > mouseY) {

                b.setAlpha(255);


            } else {
                b.setAlpha(120);
            }

            //Click
            if (mousePressed == true) {
                if (b.getPosX() < mouseX &&
                        b.getPosX() + b.getWidth() > mouseX &&
                        b.getPosY() < mouseY &&
                        b.getPosY() + b.getHeight() > mouseY) {

                    b.setAlpha(50);
                    text(b.getText(), b.getPosX(), b.getPosY());
                }
                else {
                    b.setAlpha(120);
                }
            }



        }

    }

    void render(){

        for(Button b: buttonArrayList){

            fill(b.getR(), b.getG(), b.getB(), b.getAlpha());
            rect(b.getPosX(), b.getPosY(), b.getWidth(), b.getHeight());



        }

    }

    @Override
    public void mouseMoved(MouseEvent event) {

        int mouseX = event.getX();
        int mouseY = event.getY();
        //마우스 위치가 버튼 안에 있는지를 체크하는 함수를
    }



    public void isInside(Button b, int mouseX , int mouseY){


        //충돌검사


    }

    public static void main(String[] args) {
        PApplet.main("io.thethelab.Window");
    }

}