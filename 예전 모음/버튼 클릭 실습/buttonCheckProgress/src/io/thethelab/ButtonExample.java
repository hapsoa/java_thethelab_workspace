package io.thethelab;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class ButtonExample extends PApplet {
    public static void main(String[] args) {
        PApplet.main("io.thethelab.ButtonExample");
    }

    private ArrayList<Button> buttons = new ArrayList<>();


    public void settings() {
        size(800, 600);
        JSONArray array = loadJSONArray("./button.json");
        int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject obj = array.getJSONObject(i);



            Button b = Button.fromJSON(obj);
            buttons.add(b);
        }
    }

    public void setup() {
        background(0);

    }

    public void draw() {
        // advanced for loop
        for (Button button : buttons) {
            button.render(this);
        }

    }
}
