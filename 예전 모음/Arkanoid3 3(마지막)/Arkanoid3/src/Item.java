import processing.core.PApplet;

public class Item extends MovableView implements Constants {

    private int item;
    private int time;
    private boolean visible;

    Item(PApplet pApplet, int item, float posX, float posY) {
        super(pApplet, RECT, posX, posY, ITEM_SIZE_X, ITEM_SIZE_Y);

        CollisionManager.allocate(ITEM, this);

        direction.y = 1;
        direction = direction.nomalize();

        this.item = item;
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {

        if(item != 0 && visible) {

            if (DataHelper.getTick() % 10 == 0) {
                time = (time + 1) % 7;
            }

            pApplet.image(SpriteManager.getImages(item).get(time),
                    position.x - size.x / 2, position.y - size.y / 2,
                    size.x, size.y);
        }

    }

    @Override
    public void onCollision(View view) {

        if(view instanceof Vaus) {
            visible = false;
            CollisionManager.bin.add(this);
//            DataHelper.getItems().remove(this);
        }

        if (view instanceof Rect) {
            CollisionManager.bin.add(this);
//            System.out.println("hi"); // 이걸 통해 이상 작동 하는걸 알게 되는데,
//            DataHelper.getItems().remove(this);
        }

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public int getItem(){
        return item;
    }

    public boolean getVisible() {
        return visible;
    }
}
