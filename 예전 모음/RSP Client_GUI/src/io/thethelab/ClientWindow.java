package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class ClientWindow extends PApplet {

    public void settings() {
        size(800, 600);
    }

    private List<View> views = new ArrayList<>();
    private RockButton rockButton = new RockButton(this);
    private ScissorsButton scissorsButton = new ScissorsButton(this);
    private PaperButton paperButton = new PaperButton(this);
    private PlayerRSP playerRSP = new PlayerRSP(this);
    private ServerRSP serverRSP = new ServerRSP(this);
    private Outcome outcome = new Outcome(this);

    public void setup() {
        views.add(rockButton);
        views.add(scissorsButton);
        views.add(paperButton);
        views.add(playerRSP);
        views.add(serverRSP);
        views.add(outcome);

        SpriteManager.putImages(this, Constants.RSP_IMAGE,
                "./images/rsp_image.png", 0, 47,
                0, 0, 106, 106, 3);
        SpriteManager.putImages(this, Constants.RSP_IMAGES,
                "./images/rsp_images.jpg", 0, 0,
                0, 0, 500, 333, 6);
        SpriteManager.putImages(this, Constants.OUTCOME_IMAGES,
                "./images/winLoseDraw.png", 0, 0,
                0, 0, 900, 200, 3);

    }

    public void draw() {
        background(255);

        for (View view : views) {
            view.update();
            view.render();

        }


    }

}
