package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

import java.awt.*;

import static future.code.dark.dungeon.config.Configuration.SPRITE_SIZE;

public class Coin extends GameObject{
    private boolean collected;

    public Coin(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.COIN_SPRITE);
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    @Override
    public void render(Graphics graphics) {
        if(!collected)         graphics.drawImage(getImage(), xPosition * SPRITE_SIZE, yPosition  * SPRITE_SIZE, null);
    }
}
