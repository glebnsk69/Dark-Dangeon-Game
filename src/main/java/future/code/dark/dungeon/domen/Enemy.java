package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

import java.awt.*;

public class Enemy extends DynamicObject{

    public Enemy(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.GHOST_SPRITE);
    }
    public double getDistance(int x,int y){
        return Math.sqrt(((x-xPosition)*(x-xPosition)+(y-yPosition)*(y-yPosition)));
    }

    @Override
    public void move(Direction direction, int distance) {
        super.move(direction, distance);
    }
}
