import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class TestVector2 extends PApplet {

    public static void main(String[] args) {
        TestVector2.main("TestVector2");
    }

    private List<View> views = new ArrayList<>();
    private Block block = new Block(this, View.RECT_OUTSIDE);

    public void settings(){
        size(500, 500);
    }

    public void setup(){

        block.setOnKeyClickListener(new MovableView.OnKeyLClickListener() {
            @Override
            public void onKeyClick() {
                if(key == ' '){
                    for(Rect rect : block.getRects()){
                        rect.position.rotateByPoint(90, new Vector2(130, 130));
                    }
                }
            }
        });

        views.add(block);
    }

    public void draw(){
        background(0);




        for (View view: views){

            view.update();
            view.render();
        }

    }
}
