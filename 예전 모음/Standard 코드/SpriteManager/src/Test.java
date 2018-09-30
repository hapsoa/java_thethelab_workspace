import com.sun.javaws.jnl.ResourcesDesc;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Test extends PApplet{

    private ArrayList<PImage> images;
    private ArrayList<PImage> images2;
    private int tick = 0;
    private int i = 0;
    private int[] indices = {0, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1, 0};
    private boolean isLoop = true;
    private int spriteCount = 9;


    public static void main(String[] args) {
        Test.main("Test");
    }

    public void settings(){
        size(800, 600);
    }

    public void setup(){
        SpriteManager.putImage(this, 0, "./images/ITEM_PLAYER.png"
                , 0, 0, 80, 44, 8);

        images = SpriteManager.getImages(0);

        SpriteManager.putImage(this, 1, "./images/BACKGROUND.png");


        images.add(SpriteManager.getImage(1));
//
        SpriteManager.putImage(this, 2, "./images/ITEM_SLOW.png",
                80, 44, 8, indices);


        images.addAll(SpriteManager.getImages(2));


        SpriteManager.putImage(this, 3, "./images/ITEM_LASER.png",
                0,0, 80, 44, 8, true);

        images2 = SpriteManager.getImages(3);

    }

    private int j=0;

    public void draw(){
        background(0);

        tick ++;
        if(tick % 20 == 0){
            i = (i + 1) % (indices.length + 9);
            j = (j + 1) % 14;
            System.out.println(j);
        }

        image(images.get(i), 30, 30);
        image(images2.get(j), 200, 200);
    }

}
