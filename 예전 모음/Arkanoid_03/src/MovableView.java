public abstract class MovableView extends View {


    float speed;
    Vector2 direction;

    MovableView(Arkanoid pApplet, CollisionManager.Shape shape, float posX, float posY,
                float sizeX, float sizeY) {
        super(pApplet, shape, posX, posY, sizeX, sizeY);
        this.direction = new Vector2(0,0);
        this.speed = 0;
    }

    MovableView(Arkanoid pApplet,CollisionManager.Shape shape) {
        super(pApplet,shape);
    }


}
