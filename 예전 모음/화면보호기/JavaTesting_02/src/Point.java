import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Point {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 accel;
    private List<Vector2> history;

    private float radius = 10;
    private PApplet mApplet;
    private Color color;

    public Point(PApplet applet, float x, float y) {
        this.mApplet = applet;

        history = new ArrayList<>();

        this.position = new Vector2(x, y);

        this.velocity = new Vector2((float) (Math.random() * 1 - 0.5f),
                (float) (Math.random() * 1 - 0.5f));

        this.accel = new Vector2((float) (Math.random() * 1 - 0.5f),
                (float) (Math.random() * 1 - 0.5f));

        this.color = new Color();

    }

//    private float r = 255, g = 255, b = 255;

    public void update() {
        this.accel.add((float) (Math.random() * 0.1 - 0.05f),
                (float) (Math.random() * 0.1 - 0.05f));
        this.velocity.add(this.accel);
        this.position.add(this.velocity);

        this.accel.mul(0.95f, 0.95f);
        this.velocity.mul(0.95f, 0.95f);

        history.add(new Vector2(this.position.x, this.position.y));

    }

    public void render() {
//        this.mApplet.fill(r, g, b);
        color.fillPointColor(this.mApplet);
        this.mApplet.ellipse(this.position.x, this.position.y, radius, radius);

        for (int i = 1; i < history.size(); i++) {
            Vector2 current = history.get(i);
            Vector2 before = history.get(i - 1);
//            this.mApplet.stroke(5 * i);
            color.setLineOpacity(this.mApplet, 5 * i);
            this.mApplet.strokeWeight(3);
            this.mApplet.line(current.x, current.y, before.x, before.y);

            if (i > 51) {
                history.remove(0);
            }
        }
    }

}