public class Camera {
    public Vector2 position;

    public Camera() {
        this.position = new Vector2();
    }

    public Vector2 getScreenFromWorld(Vector2 worldPosition) {
        return new Vector2(worldPosition.x - position.x,
                worldPosition.y - position.y);
    }

    public Vector2 getScreenFromWorld(float worldX, float worldY) {
        float x = worldX - position.x;
        float y = worldY - position.y;

        x %= Window.MAPSIZE;
        y %= Window.MAPSIZE;

        if (x > Window.SCREENWIDTH)
            x -= Window.MAPSIZE;
        if (y > Window.SCREENHEIGHT)
            y -= Window.MAPSIZE;

        if (x < -50) // 50은 타일 사이즈
            x += Window.MAPSIZE;
        if (y < -50)
            y += Window.MAPSIZE;

        return new Vector2(x, y);
    }

    public Vector2 getScreenToWorld(Vector2 screenPosition) {
        return new Vector2(screenPosition.x + position.x,
                screenPosition.y + position.y);
    }


}
