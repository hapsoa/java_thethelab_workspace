import processing.core.PApplet;

public class Processing extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Processing");
    }

    private final int RectCount = 10;
    Rect[] rects = new Rect[RectCount];

    public void settings() {
        size(800, 600);
        for (int i = 0; i < RectCount; i++) {
            rects[i] = new Rect(this,
                    (int) (Math.random() * 800),
                    (int) (Math.random() * 600),
                    (int) (Math.random() * 50) + 25,
                    (int) (Math.random() * 50) + 25
            );
        }
    }

    public void setup() {

    }

    public void mousePressed() {
        // 5,6
    }

    private Rect draggingObject = null;

    public void draw() {
        // 30 ~ 60

        // raw
        background(200);


        if (mousePressed) {
            if (draggingObject == null) {
                for (int i = 0; i < RectCount; i++) {
                    if (rects[i].collision(this.mouseX, this.mouseY)) {
                        draggingObject = rects[i];
                    }
                }
            }
        } else {
            draggingObject = null;
        }

        if (draggingObject != null) {
            draggingObject.deltaMovement(mouseX - pmouseX
                    , mouseY - pmouseY);
        }


        for (int i = 0; i < RectCount; i++) {
            rects[i].draw();
        }
    }
}










