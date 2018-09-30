import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyEventManager {

    private HashMap<Integer, KeyStruct> listeners;
    private class KeyStruct {
        boolean flag;
        boolean pressed;
        List<Listener> listenerList;

        KeyStruct(){
            flag = false;
            pressed = false;
            listenerList = new ArrayList<>();
        }
    }

    public KeyEventManager() {
        listeners = new HashMap<>();
    }

    public interface Listener {
        void onPress();
        void onRelease();
    }

    public void update() {

        for(Integer i : listeners.keySet()){
            KeyStruct keyStruct = listeners.get(i);
            for(Listener listener : keyStruct.listenerList){
                if(keyStruct.pressed) {
                    listener.onPress();
                    keyStruct.flag = true;
                }
                else if(keyStruct.flag){
                    listener.onRelease();
                    keyStruct.flag = false;
                }
            }
        }
    }
    public void setKeyPress(int key) {

        KeyStruct keyStruct = listeners.get(key);
        if(keyStruct != null && !keyStruct.pressed){
            keyStruct.pressed = true;
            keyStruct.flag = false;
        }
    }

    public void setKeyRelease(int key) {

        KeyStruct keyStruct = listeners.get(key);
        if (keyStruct != null) keyStruct.pressed = false;
    }

    public void removeListener(int key, Listener listener) {
        if (listeners.containsKey(key)) listeners.get(key).listenerList.remove(listener);
    }

    public void setListener(int key, Listener listener) {
        if(!listeners.containsKey(key)){ listeners.put(key, new KeyStruct()); }
        listeners.get(key).listenerList.add(listener);
    }

}