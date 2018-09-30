package io.thethelab;

import processing.core.PImage;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class ProcessingTime extends View {

    //백의 자리 수
    int num_hundred;
    //십의 자리 수
    int num_ten;
    //일의 자리 수
    int num_one;
    // 각각 화면에 띄운다
    Instant start;
    Instant now;
    Duration between;

    ArrayList<PImage> digitalNumberImages;

    public ProcessingTime(Window pApplet) {
        super(pApplet);
        start = Instant.now();


        SpriteManager.loadImage(pApplet, 0, "./images/digital_numbers_2.png",
                0, 0, 61, 85, 10, false);

        digitalNumberImages = SpriteManager.getImages(0);
    }

    @Override
    public void standBy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        between = Duration.between(start, Instant.now());

        if (between.getSeconds()<1000) {
            num_one = (int) (between.getSeconds() % 10);
            num_ten = (int) ((between.getSeconds() % 100) / 10);
            num_hundred = (int) ((between.getSeconds() / 100));
        }

//        pApplet.image(ResourceManagers.loadImage(num_hundred),
//                Constants.X_COUNT*Constants.BLOCK_SIZE-57,10,16, 32);
//        pApplet.image(ResourceManagers.loadImage(num_ten),
//                Constants.X_COUNT*Constants.BLOCK_SIZE-41,10,16, 32);
//        pApplet.image(ResourceManagers.loadImage(num_one),
//                Constants.X_COUNT*Constants.BLOCK_SIZE-25,10,16, 32);

        pApplet.image(digitalNumberImages.get(num_hundred),
                Constants.X_COUNT*Constants.BLOCK_SIZE-57,10,16, 32);
        pApplet.image(digitalNumberImages.get(num_ten),
                Constants.X_COUNT*Constants.BLOCK_SIZE-41,10,16, 32);
        pApplet.image(digitalNumberImages.get(num_one),
                Constants.X_COUNT*Constants.BLOCK_SIZE-41,10,16, 32);
    }
}
