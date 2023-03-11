package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

public class Enemy extends DynamicObject {


    public Enemy(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.GHOST_SPRITE, "Enemy");
    }


    @Override
    public void move(Direction direction, int distance) {
        if (!isDead())
            super.move(direction, distance);

    }

    @Override
    public String toString() {
        return "Enemy{" + getName() + "[" + xPosition + ":" + yPosition + "]}";
    }
}
