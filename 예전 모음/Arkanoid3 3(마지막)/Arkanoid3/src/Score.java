import processing.core.PApplet;

public class Score extends View implements Constants {

    int scoreNum;

    private int fiveZeroNum;
    private int fourZeroNum;
    private int threeZeroNum;
    private int twoZeroNum;
    private int oneZeroNum;
    private int oneNum;

    Score(PApplet pApplet) {
        super(pApplet);

        scoreNum = 0;
        DataHelper.setScore(this);

        SpriteManager.putImage(pApplet, DIGITAL_NUM_ZERO, "./images/DIGITAL_ZERO.png");
        SpriteManager.putImage(pApplet, DIGITAL_NUM_ONE, "./images/DIGITAL_ONE.png");
        SpriteManager.putImage(pApplet, DIGITAL_NUM_TWO, "./images/DIGITAL_TWO.png");
        SpriteManager.putImage(pApplet, DIGITAL_NUM_THREE, "./images/DIGITAL_THREE.png");
        SpriteManager.putImage(pApplet, DIGITAL_NUM_FOUR, "./images/DIGITAL_FOUR.png");
        SpriteManager.putImage(pApplet, DIGITAL_NUM_FIVE, "./images/DIGITAL_FIVE.png");
        SpriteManager.putImage(pApplet, DIGITAL_NUM_SIX, "./images/DIGITAL_SIX.png");
        SpriteManager.putImage(pApplet, DIGITAL_NUM_SEVEN, "./images/DIGITAL_SEVEN.png");
        SpriteManager.putImage(pApplet, DIGITAL_NUM_EIGHT, "./images/DIGITAL_EIGHT.png");
        SpriteManager.putImage(pApplet, DIGITAL_NUM_NINE, "./images/DIGITAL_NINE.png");
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {


        for (int i = 1; i <= 6; i++) {
            pApplet.image(SpriteManager.getImage(scoreNum / (1000000/(int)(Math.pow(10,i))) % 10
                            + DIGITAL_NUM_ZERO),
                    WIDTH-170 + i*15, 60, 10, 20);
        }

    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }
}
