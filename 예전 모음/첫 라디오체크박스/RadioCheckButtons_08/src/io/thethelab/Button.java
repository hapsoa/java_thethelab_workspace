package io.thethelab;

import processing.core.PApplet;

abstract public class Button {
    abstract public void render(PApplet pApplet);
    abstract public boolean collisionTest(int mouseX, int mouseY);
    abstract public void renderTrue(PApplet pApplet);
    abstract public boolean getChecked();
    abstract public boolean check();

    abstract public void setX(int x);
    abstract public void setY(int y);
    abstract public void setText(String text);
    abstract public String getType();
    abstract public void setChecked(boolean check);
}
