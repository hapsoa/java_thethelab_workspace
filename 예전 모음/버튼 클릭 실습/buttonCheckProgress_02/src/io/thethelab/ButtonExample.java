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
    private ArrayList<View> views = new ArrayList<>();

    public void settings() {
        size(800, 600);
        JSONArray array = loadJSONArray("./button.json");
        int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject obj = array.getJSONObject(i);

            String type = obj.getString("Type");
            switch (type) {
                case "Button":
                    Button b = Button.fromJSON(obj);
                    views.add(b);
                    break;

                case "CheckBox":
                    CheckBox c = CheckBox.fromJSON(obj);
                    views.add(c);
                    break;

                case "Progress":
                    Progress p = Progress.fromJSON(obj);
                    views.add(p);
                    break;

                default:
                    break;

            }

        }
    }

    public void setup() {
        background(0);

    }

    public void draw() {
        background(0);
        // advanced for loop
        for (View view : views) {
            view.render(this);
        }

    }
}
