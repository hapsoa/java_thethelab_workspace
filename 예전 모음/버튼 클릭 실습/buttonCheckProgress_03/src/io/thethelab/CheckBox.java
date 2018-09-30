package io.thethelab;

import processing.core.PApplet;
import processing.data.JSONObject;

public class CheckBox extends View {

    private int x, y, r, g, b;
    private String text;
    private int buttonSize = 20;
    private boolean checked;
    private boolean pressed;


    private CheckBox(int x, int y, int r, int g, int b, String text) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
        this.text = text;
    }

    public static CheckBox fromJSON(JSONObject json) {
        JSONObject colorObject = json.getJSONObject("color");
        //TODO : color가 없으면 기본 흰색으로 주자.

        return new CheckBox(
                json.getInt("x"),
                json.getInt("y"),
                colorObject.getInt("r"),
                colorObject.getInt("g"),
                colorObject.getInt("b"),
                json.getString("text"));
    }



    public boolean collisionTest(int mouseX, int mouseY){
        return (mouseX > x && mouseX < x + buttonSize
                && mouseY > y && mouseY < y + buttonSize);

    }

    public void renderTrue(PApplet pApplet) {
        pApplet.fill(0, 255, 0);
        pApplet.rect(x + 5, y + 5, buttonSize/2, buttonSize/2);
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

        if (collisionTest(pApplet.mouseX, pApplet.mouseY)) {
            mouseClicked(pApplet);
        }

    }

    @Override
    public void render(PApplet pApplet) {
        pApplet.fill(this.r, this.g, this.b);
        pApplet.rect(this.x, this.y, buttonSize, buttonSize);
        pApplet.fill(255);
        pApplet.text(this.text, this.x + 30, this.y + 10);

        if(checked) {
            renderTrue(pApplet);
        }
    }

    @Override
    public void mouseClicked(PApplet pApplet) {
        if (pApplet.mousePressed) {
            pressed = true;
        }
        else if (pressed) {
            check();
            pressed = false;
        }
    }
}
