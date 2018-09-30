import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Wall extends View implements Constants {

    private List<Rect> rects = new ArrayList<>();

    Wall(PApplet pApplet) {
        super(pApplet, RECT);

        rects.add(
                new Rect(pApplet, RECT,
                        WIDTH / 2,130 / 2, WIDTH, 130)
        );
        rects.add(
                new Rect(pApplet, RECT,
                        45 / 2, HEIGHT / 2, 45, HEIGHT)
        );
        rects.add(
                new Rect(pApplet, RECT,
                        WIDTH - 45 / 2,HEIGHT / 2, 45, HEIGHT)
        );
        rects.add(
                new Rect(pApplet, RECT,
                        WIDTH / 2,HEIGHT - 65 / 2, WIDTH, 65)
        );
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {

        for (Rect rect : rects)
            rect.render();
    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }
}
