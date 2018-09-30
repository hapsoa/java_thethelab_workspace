package io.thethelab;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadButton extends View implements ButtonUtil {

    int x, y;
    int width, height;
    Path fp = Paths.get("save.csv");

    public LoadButton(Window pApplet) {
        super(pApplet);
        x = 221;
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
            System.out.println("load");
            // 로드한다
            try (BufferedReader br = Files.newBufferedReader(fp)) {
                String mineMapString = br.readLine();
                String blocksString[] = mineMapString.split(",");

                //각 블록의 정보를 쪼갠 것을 분해해서 넣는다
                for (int i = 0; i < pApplet.mineMap.blocks.size(); i++) {
                    String[] str = blocksString[i].split("_");
                    pApplet.mineMap.blocks.get(i).index= Integer.parseInt(str[0]);
                    pApplet.mineMap.blocks.get(i).property= Integer.parseInt(str[1]);
                    pApplet.mineMap.blocks.get(i).isHidden= Boolean.parseBoolean(str[2]);
                    pApplet.mineMap.blocks.get(i).isFlag= Boolean.parseBoolean(str[3]);
                }
                pApplet.mineMap.numOfRightFlag =
                        Integer.parseInt(blocksString[blocksString.length-1]);

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
        pApplet.image(ResourceManagers.loadImage(ResourceManagers.LOAD_BUTTON),
                x-width/2,y-height/2,width, height);
    }
}
