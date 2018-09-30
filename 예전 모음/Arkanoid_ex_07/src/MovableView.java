public abstract class MovableView extends View {


    float speed;
    Vector2 direction;

    MovableView(Arkanoid pApplet, float posX, float posY,
                float sizeX, float sizeY) {
        super(pApplet, posX, posY, sizeX, sizeY);
        this.direction = new Vector2(0,0);
        this.speed = 0;
    }

    MovableView(Arkanoid pApplet) {
        super(pApplet);
    }


}
