import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Test extends PApplet {

    public static void main(String[] args) {
        Test.main("Test");
    }

    private Circle circle = new Circle(this);
    private Rect rect = new Rect(this);
    private List<View> views = new ArrayList<>();
    private boolean isMouse = true;
    private boolean isKey = true;

    public void settings(){
        size(600, 400);
    }

    public void setup(){

        Vector2 a = new Vector2(1, 0).rotation(90);


        System.out.println(new Vector2(-1, 4).reflection(new Vector2(2, 3)));



        circle.setOnClickListener(() -> {
            if (isMouse) {
                circle.setGreen(100);
                isMouse = false;
            }
            else {
                circle.setGreen(255);
                isMouse = true;
            }
        });

        rect.setOnKeyClickListener(new MovableView.OnKeyLClickListener() {
            @Override
            public void onKeyClick() {
                System.out.println("sdf");

                if(keyCode == LEFT){
                    rect.setDirection(-1,0);
                }

            }
        });

        views.add(circle);
        views.add(rect);
    }

    public void draw(){
        background(0);
        for (View e : views){
            e.update();
            e.render();
        }


    }


}
