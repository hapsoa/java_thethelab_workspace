import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class Map {

    private String type;
    Body body;

        class Body {
            List<Integer> map;
            List<Item> items;

            Body() {
                map = new ArrayList<>();
                items = new ArrayList<>();
                setMap((int) Constants.MAP_NUMBER.x, (int) Constants.MAP_NUMBER.y);
                setItems((int) Constants.MAP_NUMBER.x, (int) Constants.MAP_NUMBER.y);
            }

            void setMap(int x, int y) {
                for (int j = 0; j < y; j++)
                    for (int i = 0; i < x; i++) map.add(Utils.initRand(0, 1));
            }

            void setItems(int x, int y) {
                for (int j = 0; j < y; j++)
                    for (int i = 0; i < x; i++)
                        if (Utils.initRand(0, 10) == 6) items.add(new Item(new Vector2D(i, j)));
            }
    }

    Map() {
        type = Constants.MAP;
        body = new Body();
    }

    String getMapObjectAsString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
