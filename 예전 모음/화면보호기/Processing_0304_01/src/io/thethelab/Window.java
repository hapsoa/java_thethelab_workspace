package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;

public class Window extends PApplet {
    public void settings() {
        size(960, 640);
    }

    class Point {
        public float x;
        public float y;
        public float radius;

        public float r;
        public float g;
        public float b;

        public float accelx = 0;
        public float accely = 0;

        public float deltaAccelx = 0;
        public float deltaAccely = 0;

        public ArrayList<Float> lx = new ArrayList<>();
        public ArrayList<Float> ly = new ArrayList<>();

        public float opacity;
    }

    private ArrayList<Point> points = new ArrayList<>();

    public void setup() {
        background(0);
        noCursor();
        smooth();
    }

    int tick = 0;

    public void draw() {
        background(0);
        blendMode(ADD);
        colorMode(HSB, 255);
        tick++;

        for (int i = 0; i < 1; i++) {
            if (tick % 5 != 0) break;

            Point point = new Point();
            point.x = (int) (mouseX + Math.random() * 20 - 10);
            point.y = (int) (mouseY + Math.random() * 20 - 10);
            point.radius = (float) (Math.random() * 10 + 5);

            point.r = (tick + (int)(Math.random() * 30)) % 360;
            point.g = 255;
            point.b = 255;
            point.opacity = (float)(Math.random() * 0.5 + 0.3);

            point.deltaAccelx = (float)(Math.random() * 0.1f) + -0.05f;
            point.deltaAccely = (float)(Math.random() * 0.1f) + 0.1f;

            points.add(point);
        }

        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);

            fill(point.r, point.g, point.b, point.opacity*255);
            stroke(point.r, point.g, point.b, point.opacity * 255);
            point.x += point.accelx;
            point.y += point.accely;

            point.deltaAccelx += Math.random() * 0.1 - 0.05;
            point.deltaAccely += Math.random() * 0.1 - 0.05;

            point.deltaAccelx *= 0.98f;
            point.deltaAccely *= 0.98f;
            point.radius -= 0.125;
            point.opacity -= 0.005;

            point.lx.add(point.x);
            point.ly.add(point.y);

            if (point.lx.size() > 80) {
                point.lx.remove(0);
                point.ly.remove(0);
            }


            for (int j = 1; j < point.lx.size(); j++) {
                float opa = point.opacity * 255 * j / point.lx.size();
                if (opa < 0) break;

                strokeWeight(opa * 10 / 255);
                stroke(point.r, point.g, point.b, opa);
                line(point.lx.get(j-1),
                        point.ly.get(j-1),
                        point.lx.get(j),
                        point.ly.get(j));
            }

            if (point.opacity < 0) {
                System.out.println("Destroy" + i +"," + points.size());
                points.remove(i);
            }
        }


    }

}
