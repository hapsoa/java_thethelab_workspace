package io.thethelab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CollisionManager {



    public static HashMap<Integer, ArrayList<View>> collisionViews = new HashMap<>();
    public static Set<Integer> keySet;

    public static void allocate(int key, View view) {
        if (collisionViews.get(key) == null)
            collisionViews.put(key, new ArrayList<>());
        collisionViews.get(key).add(view);
    }


    public static void update() {

        keySet = collisionViews.keySet();

        ArrayList<View> srcs;
        ArrayList<View> dsts;

        for (Integer i : keySet) {
            for (Integer j : keySet) {
                srcs = collisionViews.get(i);
                dsts = collisionViews.get(j);

                if (!i.equals(j)) {
                    for (int k = 0; k < srcs.size(); k++) {
                        for (int m = 0; m < dsts.size(); m++) {

                            View src = srcs.get(k);
                            View dst = dsts.get(m);

                            if(src.shape == View.RECT_OUTSIDE && dst.shape == View.CIRCLE)
                                collisionRectCircleOutside(src, dst);
                            if(src.shape == View.CIRCLE && dst.shape == View.RECT_OUTSIDE)
                                collisionRectCircleOutside(dst, src);
                            if(src.shape == View.RECT_INSIDE && dst.shape == View.CIRCLE)
                                collisionRectCircleInside(src, dst);
                            if(src.shape == View.CIRCLE && dst.shape == View.RECT_INSIDE)
                                collisionRectCircleInside(dst, src);
                            if(src.shape == View.RECT_OUTSIDE && dst.shape == View.RECT_OUTSIDE)
                                collisionRectRectOutside(src, dst);
                            if(src.shape == View.RECT_OUTSIDE && dst.shape == View.RECT_OUTSIDE)
                                collisionRectCircleInside(src, dst);
                        }
                    }
                }

            }
        }

    }


    public static boolean collisionRectCircleOutside(View rect, View circle) {

        Vector2 circlePos = circle.position;
        Vector2 rectPos = rect.position;
        float r = circle.size.x / 2;
        float rectSizeX = rect.size.x;
        float rectSizeY = rect.size.y;

        if (circlePos.x > rectPos.x - r && circlePos.x < rectPos.x + rectSizeX + r
                && circlePos.y > rectPos.y - r && circlePos.y < rectPos.y + rectSizeY + r) {

            if ((Math.abs((rectSizeX / 2 + rectPos.x) - circlePos.x) > rectSizeX / 2)
                    && (Math.abs((rectPos.y + rectSizeY / 2) - circlePos.y) > rectSizeY / 2)) {

                if ((circlePos.x - rectPos.x) * (circlePos.x - rectPos.x) +
                        (circlePos.y - rectPos.y) * (circlePos.y - rectPos.y) < (r * r)
                        || (circlePos.x - rectPos.x) * (circlePos.x - rectPos.x) +
                        (circlePos.y - (rectPos.y + rectSizeY)) * (circlePos.y -
                                (rectPos.y + rectSizeY)) < (r * r)
                        || (circlePos.x - (rectPos.x + rectSizeX)) * (circlePos.x -
                        (rectPos.x + rectSizeX)) + (circlePos.y - rectPos.y) *
                        (circlePos.y - rectPos.y) < (r * r)
                        || (circlePos.x - (rectPos.x + rectSizeX)) * (circlePos.x -
                        (rectPos.x + rectSizeX)) + (circlePos.y - (rectPos.y + rectSizeY)) *
                        (circlePos.y - (rectPos.y + rectSizeY)) < (r * r)) {

                    // view1.onCollision(view2)

                    rect.onCollision(circle);
                    circle.onCollision(rect);
                    return true;
                } else
                    return false;
            } else {
                rect.onCollision(circle);
                circle.onCollision(rect);
                return true;
            }
        }
        return false;
    }


    public static boolean collisionRectCircleInside(
            View rect, View circle) {
        Vector2 rectVector = rect.position;
        Vector2 circleVector = circle.position;
        float r = circle.size.x / 2;

        if (!(rectVector.x + r < circleVector.x &&
                circleVector.x < rectVector.x + rect.size.x - r &&
                rectVector.y + r < circleVector.y &&
                circleVector.y < rectVector.y + rect.size.y - r)) {


            rect.onCollision(circle);
            circle.onCollision(rect);
            return true;
        } else
            return false;

    }

    public static boolean collisionRectRectOutside(View rect1, View rect2) {

        Vector2 rect1pos = rect1.position;
        Vector2 rect1size = rect1.size;
        Vector2 rect2pos = rect2.position;
        Vector2 rect2size = rect2.size;

        if (rect1pos.x < rect2pos.x + rect2size.x
                && rect1pos.y < rect2pos.y + rect2size.y
                && rect1pos.x + rect1size.x > rect2pos.x
                && rect1pos.y + rect1size.y > rect2pos.y) {

            rect1.onCollision(rect2);
            rect2.onCollision(rect1);
            return true;
        } else
            return false;
    }


}
