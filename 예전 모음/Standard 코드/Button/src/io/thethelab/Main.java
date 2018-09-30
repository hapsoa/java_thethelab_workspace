package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet{

    public static void main(String[] args) {
	// write your code here
        Main.main("io.thethelab.Main");
    }

    public void settings() {
        size(Constant.WIDTH, Constant.HEIGHT);
    }

    ArrayList<View> ui = new ArrayList<>();

    public void setup() {
        UIBar bar = new UIBar(this);

        bar.addButton(new Button.OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("큰거");
            }
        });

        bar.addButton(new Button.OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("작은거");
            }
        });

        bar.addButton(new Button.OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("w");
            }
        });

        bar.addButton(new Button.OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("t");
            }
        });

        bar.addButton(new Button.OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("f");
            }
        });

        ui.add(bar);

    }

    public void draw() {
        background(0);

        for (View view : ui) {
            view.render();
        }

    }

    public void mouseClicked() {
        for (View view : ui) {
            view.mouseClicked(mouseX, mouseY);
        }
    }
}
