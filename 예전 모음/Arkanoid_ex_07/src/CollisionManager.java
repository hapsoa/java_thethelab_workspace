import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CollisionManager implements Constants{

//    public static List<View> collisionViews= new ArrayList<>();

    public static HashMap<Integer, ArrayList<View>> collisionViews = new HashMap<>();

//    public static void allocate(List<View> views, int key){
//        collisionViews.get(key).addAll(views);
//    }


    public static void update(){
        // todo Collision Check

        Set<Integer> keySet = collisionViews.keySet();

        ArrayList<View> src;
        ArrayList<View> dst;

        for (Integer i : keySet) {
            for (Integer j : keySet) {
                src = collisionViews.get(i);
                dst = collisionViews.get(j);

                for (View a : src) {
                    for (View b : dst) {
                        a.onCollision(b);
                        b.onCollision(a);
                    }
                }


            }
        }


//        for(View v : src){
//            for(View v2 : dst){
//
//                // if 충돌 하면
//                v.onCollision(v2);
//                v2.onCollision(v);
//            }
//        }
//
//        // 누군가 충돌을 했으면,
//        View v;
//
//        v.onCollision();






        //
    }
    public static void allocate(View view, int key){
        if (collisionViews.get(key) == null)
            collisionViews.put(key, new ArrayList<>());
        collisionViews.get(key).add(view);
    }

    public static boolean collisionRectCircleOutside(Vector2 rectVector, Vector2 circleVector, float r, int sizeX, int sizeY){
        if(circleVector.x > rectVector.x - r && circleVector.x < rectVector.x + sizeX + r
                && circleVector.y > rectVector.y - r && circleVector.y < rectVector.y + sizeY + r){
            if((Math.abs((sizeX/2 + rectVector.x) - circleVector.x) > sizeX / 2)
                    && (Math.abs((rectVector.y + sizeY / 2) - circleVector.y) > sizeY / 2)){
                if((circleVector.x - rectVector.x) * (circleVector.x - rectVector.x) + (circleVector.y - rectVector.y) * (circleVector.y - rectVector.y) < (r * r)
                        || (circleVector.x - rectVector.x) * (circleVector.x - rectVector.x) + (circleVector.y - (rectVector.y + sizeY)) * (circleVector.y - (rectVector.y + sizeY)) < (r * r)
                        || (circleVector.x - (rectVector.x + sizeX)) * (circleVector.x - (rectVector.x + sizeX)) + (circleVector.y - rectVector.y) * (circleVector.y - rectVector.y) < (r * r)
                        || (circleVector.x - (rectVector.x + sizeX)) * (circleVector.x - (rectVector.x + sizeX))
                        + (circleVector.y - (rectVector.y + sizeY)) * (circleVector.y - (rectVector.y + sizeY)) < (r * r)){
                    return true;
                }else
                    return false;
            }
            else
                return true;
        }
        return false;
    }


//    public static boolean collisionRectCircleOut(ArrayList<View> a, ArrayList<View> b){
//        if(circleVector.x > rectVector.x - r && circleVector.x < rectVector.x + sizeX + r
//                && circleVector.y > rectVector.y - r && circleVector.y < rectVector.y + sizeY + r){
//            if((Math.abs((sizeX/2 + rectVector.x) - circleVector.x) > sizeX / 2)
//                    && (Math.abs((rectVector.y + sizeY / 2) - circleVector.y) > sizeY / 2)){
//                if((circleVector.x - rectVector.x) * (circleVector.x - rectVector.x) + (circleVector.y - rectVector.y) * (circleVector.y - rectVector.y) < (r * r)
//                        || (circleVector.x - rectVector.x) * (circleVector.x - rectVector.x) + (circleVector.y - (rectVector.y + sizeY)) * (circleVector.y - (rectVector.y + sizeY)) < (r * r)
//                        || (circleVector.x - (rectVector.x + sizeX)) * (circleVector.x - (rectVector.x + sizeX)) + (circleVector.y - rectVector.y) * (circleVector.y - rectVector.y) < (r * r)
//                        || (circleVector.x - (rectVector.x + sizeX)) * (circleVector.x - (rectVector.x + sizeX))
//                        + (circleVector.y - (rectVector.y + sizeY)) * (circleVector.y - (rectVector.y + sizeY)) < (r * r)){
//                    return true;
//                }else
//                    return false;
//            }
//            else
//                return true;
//        }
//        return false;
//    }



    public static boolean collisionRectCircleOutside1(Vector2 rectVector, Vector2 circleVector, int r){
        if(circleVector.x > rectVector.x - r && circleVector.x < rectVector.x + VAUS_SIZE_X + r
                && circleVector.y > rectVector.y - r && circleVector.y < rectVector.y + VAUS_SIZE_Y + r){
            if((Math.abs((VAUS_SIZE_X/2 + rectVector.x) - circleVector.x) > VAUS_SIZE_X / 2)
                    && (Math.abs((rectVector.y + VAUS_SIZE_Y / 2) - circleVector.y) > VAUS_SIZE_Y / 2)){
                if((circleVector.x - rectVector.x) * (circleVector.x - rectVector.x) + (circleVector.y - rectVector.y) * (circleVector.y - rectVector.y) < (r * r)
                        || (circleVector.x - rectVector.x) * (circleVector.x - rectVector.x) + (circleVector.y - (rectVector.y + VAUS_SIZE_Y)) * (circleVector.y - (rectVector.y + VAUS_SIZE_Y)) < (r * r)
                        || (circleVector.x - (rectVector.x + VAUS_SIZE_X)) * (circleVector.x - (rectVector.x + VAUS_SIZE_X)) + (circleVector.y - rectVector.y) * (circleVector.y - rectVector.y) < (r * r)
                        || (circleVector.x - (rectVector.x + VAUS_SIZE_X)) * (circleVector.x - (rectVector.x + VAUS_SIZE_X))
                        + (circleVector.y - (rectVector.y + VAUS_SIZE_Y)) * (circleVector.y - (rectVector.y + VAUS_SIZE_Y)) < (r * r)){
                    return true;
                }else
                    return false;
            }
            else
                return true;
        }
        return false;
    }

    public static boolean collisionRectCircleInside(Vector2 rectVector, Vector2 circleVector, float r){

        return rectVector.x + r < circleVector.x && circleVector.x < rectVector.x + WALL_RIGHT - r
                && rectVector.y + r < circleVector.y && circleVector.y < rectVector.y + HEIGHT - r;

    }

    public static boolean collisionRectRectOutSide(
            Vector2 rect1pos, Vector2 rect2pos,
             Vector2 rect1size, Vector2 rect2size) {
        return (rect1pos.x < rect2pos.x + rect2size.x
                && rect1pos.y < rect2pos.y + rect2size.y
                && rect1pos.x + rect1size.x > rect2pos.x
                && rect1pos.y + rect1size.y > rect2pos.y);
    }




}
