package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

import java.awt.*;

public class Enemy extends DynamicObject{
    private boolean dead = false;

    public Enemy(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.GHOST_SPRITE,"Enemy");
    }
    public double getDistance(int x,int y){
        return Math.sqrt(((x-xPosition)*(x-xPosition)+(y-yPosition)*(y-yPosition)));
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public void move(Direction direction, int distance) {
        if(!dead)
        super.move(direction, distance);

    }

    @Override
    public String toString() {
        return "Enemy{"+getName()+"[" + xPosition + ":" + yPosition + "]}";
    }
}
