import processing.core.PApplet;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyEventManager {
    // 먼저 관찰자를 설정 할 수 있습니다

    private PApplet pApplet;
    private HashMap<Integer, KeyStruct> keyStructs;

    private class KeyStruct {
        public List<Listener> listenerList = new ArrayList<>();
        public long duration = 0;
        public boolean isFlag = false;
        public boolean isPress = false;
        boolean isFirst = true;
    }

    public KeyEventManager(PApplet pApplet) {
        this.pApplet = pApplet;
        keyStructs = new HashMap<>();
    }


    public interface Listener {
        void onPress(boolean isFirst, float duration);

        void onRelease(float duration);
    }


    public void update() {



        for (int key : keyStructs.keySet()) {

            KeyStruct struct = keyStructs.get(key);

            if (struct.isPress) {
                for (Listener l : struct.listenerList) {
                    l.onPress(true, System.currentTimeMillis()
                            - keyStructs.get(key).duration);
                }
                struct.isFlag = true;
            } else if(struct.isFlag) {

                for (Listener l : struct.listenerList) {
                    l.onRelease(System.currentTimeMillis()
                            - keyStructs.get(key).duration);
                }

                struct.isFlag = false;
            }

        }


    }

    public void setKeyPress(int key) {
        if (!keyStructs.get(key).isFirst) return;

        keyStructs.get(key).isFirst = false;
        keyStructs.get(key).isPress = true;
        keyStructs.get(key).duration = System.currentTimeMillis();



    }

    public void setKeyRelease(int key) {
        keyStructs.get(key).isPress = false;
        keyStructs.get(key).duration = System.currentTimeMillis()
                - keyStructs.get(key).duration;
        keyStructs.get(key).isFirst = true;
    }

    public void removeListener(int key, Listener listener) {

    }

    public void removeListener(char key, Listener listener) {

    }

    public void setListener(int key, Listener listener) {
//        if (!pApplet.keyPressed) return;
        if (!keyStructs.containsKey(key)) {
            keyStructs.put(key, new KeyStruct());
        }

        keyStructs.get(key).listenerList.add(listener);
    }

    public void setListener(char key, Listener listener) {
        if (keyStructs.containsKey((int) key))
            keyStructs.get((int) key).listenerList.add(listener);
        else
            keyStructs.put((int) key, new KeyStruct());
    }
}

