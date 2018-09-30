package io.thethelab;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveButton extends View implements ButtonUtil {

    int x, y;
    int width, height;
    Path fp = Paths.get("save.csv");

    public SaveButton(Window pApplet) {
        super(pApplet);
        x = 176;
        y = 26;
        width = 32;
        height = 32;
    }

    @Override
    public boolean isCollided(int mouseX, int mouseY) {
        if (Math.abs(mouseX-x) < width/2 &&
                Math.abs(mouseY-y) < height/2)
            return true;
        else
            return false;
    }

    @Override
    public void standBy() {

    }

    @Override
    public void update() {
        if (isCollided(pApplet.mouseX, pApplet.mouseY)) {
            System.out.println("save");
            // 저장한다
            try(BufferedWriter bw = Files.newBufferedWriter(fp)) {
                bw.write(pApplet.mineMap.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void render() {
        if (isCollided(pApplet.mouseX, pApplet.mouseY)) {
            // 갖다대기 효과
        }
        pApplet.image(ResourceManagers.loadImage(ResourceManagers.SAVE_BUTTON),
                x-width/2,y-height/2,width, height);
    }
}
