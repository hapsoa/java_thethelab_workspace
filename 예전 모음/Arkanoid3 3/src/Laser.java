import processing.core.PApplet;

public class Laser extends MovableView implements Constants {

    private boolean visible;


    Laser(PApplet pApplet, float posX, float posY) {
        super(pApplet, RECT);


        this.position.x = posX;
        this.position.y = posY;
        size.x = 5;
        size.y = 15;
        direction.x = 0;
        direction.y = -1f;

        direction = direction.nomalize();
        speed = 7f;

        visible = true;

        CollisionManager.allocate(LASER, this);

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {
        if (visible)
            pApplet.image(SpriteManager.getImage(LASER), position.x - size.x / 2,
                    position.y - size.y / 2, size.x, size.y);
    }

    @Override
    public void onCollision(View view) {

        if (view instanceof Block) {
            visible = INVISIBLE;
            CollisionManager.bin.add(this);
            DataHelper.getLasers().remove(this);
        }

        if (view instanceof Rect) {
            visible = INVISIBLE;
            CollisionManager.bin.add(this);
            DataHelper.getLasers().remove(this);
        }

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }
}
