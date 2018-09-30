package io.thethelab;

import java.time.Duration;
import java.time.Instant;

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


    public ProcessingTime(Window pApplet) {
        super(pApplet);
        start = Instant.now();
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

        num_one = (int)(between.getSeconds() % 10);
        num_ten = (int)((between.getSeconds() % 100) / 10);
        num_hundred = (int)((between.getSeconds() / 100));

        pApplet.image(ResourceManagers.loadImage(num_hundred),
                Constants.X_COUNT*Constants.BLOCK_SIZE-57,10,16, 32);
        pApplet.image(ResourceManagers.loadImage(num_ten),
                Constants.X_COUNT*Constants.BLOCK_SIZE-41,10,16, 32);
        pApplet.image(ResourceManagers.loadImage(num_one),
                Constants.X_COUNT*Constants.BLOCK_SIZE-25,10,16, 32);
    }
}
