import processing.core.PApplet;

public class Life extends View implements Constants {

    private int life;

    Life(PApplet pApplet) {
        super(pApplet);
        life = 2;
        position.x = 45;
        position.y = 70;
        size.x = LIFE_SIZE_X;
        size.y = LIFE_SIZE_Y;

        DataHelper.setLife(life);
    }

    @Override
    public void onUpdate() {
        life = DataHelper.getLife();
    }

    @Override
    public void render() {

        for(int i = 0; i < life; i++){
            pApplet.image(SpriteManager.getImages(VAUS).get(0),
                    position.x * (i + 1) - size.x / 2, position.y - size.y / 2,
                    size.x, size.y);
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
