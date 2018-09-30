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
                                collisionRectRectOutside(dst, src);
                        }
                    }
                }

            }
        }

    }


    public static boolean collisionRectCircleOutside(View rect, View circle) {

        Vector2 circlePos = circle.position;
//        Vector2 rectPos = rect.position;
        float rectPosX = rect.position.x - rect.size.x/2;
        float rectPosY = rect.position.y - rect.size.y/2;

        float r = circle.size.x / 2;
        float rectSizeX = rect.size.x;
        float rectSizeY = rect.size.y;

        if (circlePos.x > rectPosX - r && circlePos.x < rectPosX + rectSizeX + r
                && circlePos.y > rectPosY - r && circlePos.y < rectPosY + rectSizeY + r) {

            if ((Math.abs((rectSizeX / 2 + rectPosX) - circlePos.x) > rectSizeX / 2)
                    && (Math.abs((rectPosY + rectSizeY / 2) - circlePos.y) > rectSizeY / 2)) {

                if ((circlePos.x - rectPosX) * (circlePos.x - rectPosX) +
                        (circlePos.y - rectPosY) * (circlePos.y - rectPosY) < (r * r)
                        || (circlePos.x - rectPosX) * (circlePos.x - rectPosX) +
                        (circlePos.y - (rectPosY + rectSizeY)) * (circlePos.y -
                                (rectPosY + rectSizeY)) < (r * r)
                        || (circlePos.x - (rectPosX + rectSizeX)) * (circlePos.x -
                        (rectPosX+ rectSizeX)) + (circlePos.y - rectPosY) *
                        (circlePos.y - rectPosY) < (r * r)
                        || (circlePos.x - (rectPosX + rectSizeX)) * (circlePos.x -
                        (rectPosX + rectSizeX)) + (circlePos.y - (rectPosY + rectSizeY)) *
                        (circlePos.y - (rectPosY + rectSizeY)) < (r * r)) {

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
//        Vector2 rectVector = rect.position;
        float rectPosX = rect.position.x - rect.size.x/2;
        float rectPosY = rect.position.y - rect.size.y/2;
        Vector2 circleVector = circle.position;

        float r = circle.size.x / 2;

        if (!(rectPosX + r < circleVector.x &&
                circleVector.x < rectPosX + rect.size.x - r &&
                rectPosY + r < circleVector.y &&
                circleVector.y < rectPosY + rect.size.y - r)) {


            rect.onCollision(circle);
            circle.onCollision(rect);
            return true;
        } else
            return false;

    }

    public static boolean collisionRectRectOutside(View rect1, View rect2) {

//        Vector2 rect1pos = rect1.position;
        Vector2 rect1size = rect1.size;
        float rectPos1X = rect1.position.x - rect1.size.x/2;
        float rectPos1Y = rect1.position.y - rect1.size.y/2;
//        Vector2 rect2pos = rect2.position;
        Vector2 rect2size = rect2.size;
        float rectPos2X = rect1.position.x - rect1.size.x/2;
        float rectPos2Y = rect1.position.y - rect1.size.y/2;

        if (rectPos1X < rectPos2X + rect2size.x
                && rectPos1Y < rectPos2Y + rect2size.y
                && rectPos1X + rect1size.x > rectPos2X
                && rectPos1Y + rect1size.y > rectPos2Y) {

            rect1.onCollision(rect2);
            rect2.onCollision(rect1);
            return true;
        } else
            return false;
    }


}
