import processing.core.PApplet;

public class Block extends View implements Constants {

    private int color;
    private int item;
    private int life;
    private boolean visible;
    private int index;

    Block(PApplet pApplet) {
        super(pApplet, RECT);
        size.x = BLOCK_SIZE_X;
        size.y = BLOCK_SIZE_Y;

    }

    public void setIndex(int index, int lengthX) {
        this.index = index;
        position.x = Util.getPosXByIndex(index, lengthX, BLOCK_SIZE_X, WALL_LEFT);
        position.y = Util.getPosYByIndex(index, lengthX, BLOCK_SIZE_Y) + BLOCK_SIZE_Y * 8;

        if(color != 0)
            CollisionManager.allocate(RECT, this);
    }

//    private int a;
//
//    public void setA(int a){
//        this.a = a;
//    }


    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {

        if (visible) {
            if (color == BLOCK_HARD) {
                pApplet.image(SpriteManager.getImage(BLOCKS_IMAGE, 8),
                        position.x - size.x / 2,
                        position.y - size.y / 2, size.x, size.y);

            } else if (color == BLOCK_IMMORTAL) {
                pApplet.image(SpriteManager.getImage(BLOCKS_IMAGE, 9),
                        position.x - size.x / 2,
                        position.y - size.y / 2, size.x, size.y);

            } else if (color == 0) {

            } else {
                pApplet.image(SpriteManager.getImage(BLOCKS_IMAGE, color - 5),
                        position.x - size.x / 2,
                        position.y - size.y / 2, size.x, size.y);
            }

        }
    }

    @Override
    public void onCollision(View view) {

        if (view instanceof Ball || view instanceof Laser) {

            if(color != BLOCK_IMMORTAL)
                visible = INVISIBLE;
            DataHelper.getItems().get(index).setSpeed(5f);
            DataHelper.getItems().get(index).setVisible(VISIBLE);

            CollisionManager.bin.add(this);
        }

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisible() {
        return visible;
    }

    public int getColor() {
        return color;
    }

    public Vector2 getPos() {
        return position;
    }

}
