import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class UI extends View implements Constants {

    Life life;
    Score score;
    private List<View> views = new ArrayList<>();

    UI(PApplet pApplet) {
        super(pApplet, NONE);
        life = new Life(pApplet);
        score = new Score(pApplet);
        SpriteManager.putImage(pApplet, UIIMAGE,
                "./images/UIimage.png");
        position.x = 12;
        position.y = 12;
        size.x = WIDTH - 24;
        size.y = HEIGHT - 24;

        views.add(life);
        views.add(score);

    }


    @Override
    public void render() {
        BackgroundRender();

        for (View view: views)
            view.render();

    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void update() {

        for (View view: views)
            view.update();

        //만약 Map을 static으로 한다면, 여기에서 score와 life를 수정할 수 있을듯.
    }

    private void BackgroundRender() {
        //백그라운드 이미지 출력.

        pApplet.image(SpriteManager.getImage(UIIMAGE),
                position.x, position.y, size.x, size.y);

    }

    public void setLife(Life life) {
        this.life = life;
    }

    public void lifeRender() {
        //score에 따라 숫자 이미지 출력.
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void scoreRender() {
        //score에 따라 숫자 이미지 출력.
    }

}