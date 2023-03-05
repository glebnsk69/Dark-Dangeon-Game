package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

public abstract class DynamicObject extends AnimatedObject {

    public DynamicObject(int xPosition, int yPosition, String imagePath) {
        super(xPosition, yPosition, imagePath);
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    protected void move(Direction direction, int distance) {
        int tmpXPosition = getXPosition();
        int tmpYPosition = getYPosition();

        switch (direction) {
            case UP -> tmpYPosition -= distance;
            case DOWN -> tmpYPosition += distance;
            case LEFT -> tmpXPosition -= distance;
            case RIGHT -> tmpXPosition += distance;
        }

        if (isAllowedSurface(tmpXPosition, tmpYPosition)) {
            xPosition = tmpXPosition;
            yPosition = tmpYPosition;
        }

        //System.out.println("x="+tmpXPosition+" y="+ tmpYPosition+"==>"+GameMaster.getInstance().getMap().toString());
//        System.out.println(GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition]);


        if((GameMaster.getInstance().getMap()).getMap()[tmpYPosition][tmpXPosition]==Configuration.EXIT_CHARACTER) {
            if(this instanceof Player p) {
                if(p.isWon()) System.out.println("Exit!!!");
            }
        }



    }

    public Boolean isAllowedSurface(int x, int y) {
        return GameMaster.getInstance().getMap().getMap()[y][x] != Configuration.WALL_CHARACTER;
    }


}
