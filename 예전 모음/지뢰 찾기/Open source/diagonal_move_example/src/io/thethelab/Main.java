package io.thethelab;

import processing.core.PApplet;

public class Main extends PApplet{

    public static void main(String[] args) {
	// write your code here
        Main.main("io.thethelab.Main");
    }

    /* Move Object Example (v2.0) * by GoToLoop (2012/Dec) * * http://forum.processing.org/topic/keypressed-problem * * http://forum.processing.org/topic/ * how-to-move-an-object-diagonally-using-keycode * * http://studio.processingtogether.com/sp/pad/export/ro.9bY07S95k2C2a/latest */

    // Object's variables:
    static int x, y;     // positions
    int w, h;            // dimensions
    int ww, hh;          // radii
    boolean ballOrBox;   // current shape

    // Object's constants:
    final static byte  SPX  = 30, SPY = 20; // speed movement
    final static byte  BOLD = 4;            // border thickness
//    final static color COLOUR = #0000FF;    // #blue

    // General constants:
    final static byte  FPS = 50;
//    final static color BG  = #FFFF00;       // #yellow

    // General boolean variables:
    static boolean NORTH, SOUTH, WEST, EAST;

    void initVars() {
        x = width>>1;    // center of screen
        y = height>>1;

        w = width/10;    // 1/10th size of screen
        h = height/10;

        ww = w>>1;       // distances from center (radii)
        hh = h>>1;
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {

        smooth();            // turn drawing smoothness on
        noLoop();            // turn draw() callback off
        frameRate(FPS);      // set frames / second

        rectMode(CENTER);    // coordinates are relative from center
        ellipseMode(CENTER);

        fill(255);        // object's paint color
        stroke(0);           // #black
        strokeWeight(BOLD);  // object's border thickness

        initVars();
    }

    public void draw() {
        background(0);

        moveObj();
        checkBorder();

        if (ballOrBox)    ellipse(x, y, w, h);
        else              rect(x, y, w, h);
    }

    public void keyPressed() {
        redraw();   //  queue draw()

        final int k = keyCode;

        if (k == ' ' | k == ENTER | k == RETURN)
            ballOrBox = !ballOrBox;

        else if (k == UP    | k == 'W')   NORTH = true;
        else if (k == DOWN  | k == 'S')   SOUTH = true;
        else if (k == LEFT  | k == 'A')   WEST  = true;
        else if (k == RIGHT | k == 'D')   EAST  = true;
    }

    public void keyReleased() {
        redraw();   //  queue draw()

        final int k = keyCode;

        if      (k == UP    | k == 'W')   NORTH = false;
        else if (k == DOWN  | k == 'S')   SOUTH = false;
        else if (k == LEFT  | k == 'A')   WEST  = false;
        else if (k == RIGHT | k == 'D')   EAST  = false;
    }

    static void moveObj() {
        if (NORTH)  y -= SPY;
        if (SOUTH)  y += SPY;
        if (WEST)   x -= SPX;
        if (EAST)   x += SPX;
    }

    void checkBorder() {
        if (x > width-ww)    x = width-ww;
        else if (x < ww)     x = ww;

        if (y > height-hh)   y = height-hh;
        else if (y < hh)     y = hh;
    }
}
