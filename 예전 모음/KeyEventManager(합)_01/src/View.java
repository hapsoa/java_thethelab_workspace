import processing.core.PApplet;

public abstract class View {

    protected PApplet pApplet;       // 프로세싱 변수와 메소드를 참조하기위하여 만듦.
    Vector2 position;      // View의 위치 좌표
    Vector2 size;          // View의 너비와 높이
    private boolean isMousePressed;

    View(PApplet pApplet, float posX, float posY,
         float sizeX, float sizeY) {
        this.pApplet = pApplet;
        this.position = new Vector2(posX, posY);
        this.size = new Vector2(sizeX, sizeY);
    }

    View(PApplet pApplet) {
        this.pApplet = pApplet;
        this.position = new Vector2(0, 0);
        this.size = new Vector2(0, 0);
    }

    public interface OnClickListener {
        void onClick();
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener clickListener) {
        this.onClickListener = clickListener;
    }

    abstract public void onUpdate();

    public void update() {

        if (isCollision(this.pApplet.mouseX, this.pApplet.mouseY)) {
            if (pApplet.mousePressed) {

                isMousePressed = true;
            }
            else if (isMousePressed) {
                if (onClickListener != null)
                    onClickListener.onClick();

                isMousePressed = false;
            }
        }

        onUpdate();
    }                     // View가 프레임마다 변화하게 하는 메소드

    abstract public void render();                      // View를 그리는 메소드

    abstract public void onCollision(View view);        // 다른 View와 충돌되었을 때 호출하는 메소드

    abstract public boolean isCollision(float mouseX, float mouseY);

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }


}
