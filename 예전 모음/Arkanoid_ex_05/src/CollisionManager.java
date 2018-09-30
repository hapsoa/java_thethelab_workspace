public class CollisionManager implements Constants{

    public static boolean collisionRectCircleOutside(Vector2 rectVector, Vector2 circleVector, int r, int sizeX, int sizeY){
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

    public static boolean collisionRectCircleInside(Vector2 rectVector, Vector2 circleVector, int r){

        return rectVector.x + r < circleVector.x && circleVector.x < rectVector.x + WALL_RIGHT - r
                && rectVector.y + r < circleVector.y && circleVector.y < rectVector.y + HEIGHT - r;


    }




}
