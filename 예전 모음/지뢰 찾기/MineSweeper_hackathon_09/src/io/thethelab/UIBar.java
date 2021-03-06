package io.thethelab;

import java.util.ArrayList;
import java.util.List;

public class UIBar extends View {

    private int width;
    int height;
    Smile smile = new Smile(pApplet);
    ProcessingTime processingTime = new ProcessingTime(pApplet);
    NumOfLeftMine numOfLeftMine = new NumOfLeftMine(pApplet);
    NumOfLeftBlock numOfLeftBlock = new NumOfLeftBlock(pApplet);
    SaveButton saveButton = new SaveButton(pApplet);
    LoadButton loadButton = new LoadButton(pApplet);
    List<View> views = new ArrayList<>();


    public UIBar(Window pApplet) {
        super(pApplet);
        width = Constants.X_COUNT*Constants.BLOCK_SIZE;
        height = Constants.UI_BAR_HEIGHT;
        views.add(smile);
        views.add(processingTime);
        views.add(numOfLeftMine);
        views.add(numOfLeftBlock);
        views.add(saveButton);
        views.add(loadButton);
    }

    @Override
    public void standBy() {

    }

    @Override
    public void update() {
        for (View view : views) {
            view.update();
        }

    }

    @Override
    public void render() {
        pApplet.fill(255);
        pApplet.rect(0, 0, width, height);

        for (View view : views) {
            view.render();
        }


    }
}
