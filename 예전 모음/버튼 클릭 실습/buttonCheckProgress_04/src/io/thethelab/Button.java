package io.thethelab;

import processing.core.PApplet;
import processing.data.JSONObject;

public class Button extends View {

    private int x, y, r, g, b;
    private String text;
    private int width = 120;
    private int height = 40;
    private boolean checked;
    private boolean pressed;

    private Button(int x, int y, int r, int g, int b, String text) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
        this.text = text;
        checked = false;
    }

    public static Button fromJSON(JSONObject json) {
        JSONObject colorObject = json.getJSONObject("color");
        //TODO : color가 없으면 기본 흰색으로 주자.

        return new Button(
                json.getInt("x"),
                json.getInt("y"),
                colorObject.getInt("r"),
                colorObject.getInt("g"),
                colorObject.getInt("b"),
                json.getString("text"));
    }



    public boolean collisionTest(int mouseX, int mouseY){
        return (mouseX > x && mouseX < x + width
                && mouseY > y && mouseY < y + height);
    }

    public boolean check() {
        if (checked)
            checked = false;
        else
            checked = true;

        return checked;
    }

    @Override
    public void update(PApplet pApplet) {
        mouseClicked(pApplet);
        if (collisionTest(pApplet.mouseX, pApplet.mouseY) && pressed) {
            r = 0;
            g = 255;
            b = 0;
        }
        else {
            r = 100;
            g = 100;
            b = 100;
        }
    }

    @Override
    public void render(PApplet pApplet) {
        pApplet.fill(this.r, this.g, this.b);
        pApplet.rect(this.x, this.y, width, height);
        pApplet.fill(255);
        pApplet.text(text, x, y+25);
    }

    @Override
    public void mouseClicked(PApplet pApplet) {
        if (pApplet.mousePressed) {
            pressed = true;
        }
        else if (pressed) {
            pressed = false;
        }
    }



}
