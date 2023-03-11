package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

public abstract class DynamicObject extends AnimatedObject {

    public DynamicObject(int xPosition, int yPosition, String imagePath) {
        this(xPosition, yPosition, imagePath, "Unknown Dinamic Object");
    }

    public DynamicObject(int xPosition, int yPosition, String imagePath, String name) {

        super(xPosition, yPosition, imagePath, name);
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
        if (this instanceof Enemy enemy) {
            if (enemy.equals(GameMaster.getInstance().getPlayer())) System.out.println("ПОПАЛСЯ!");
        }


    }

    public Boolean isAllowedSurface(int x, int y) {
        if (GameMaster.getInstance().getMap().getMap()[y][x] == Configuration.EXIT_CHARACTER) {
            System.out.println("check exit " + GameMaster.getInstance().getPlayer());
            if (!GameMaster.getInstance().getExit().isOpen()) {
                System.out.println("ВЫХОДА НЕТ!!! "+GameMaster.getInstance().getExit()+":"+GameMaster.getInstance().getPlayer());
                return false;
            }
        }
        return GameMaster.getInstance().getMap().getMap()[y][x] != Configuration.WALL_CHARACTER;
    }

}
