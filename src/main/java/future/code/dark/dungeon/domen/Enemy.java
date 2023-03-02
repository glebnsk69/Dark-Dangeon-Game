package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

import java.awt.*;

public class Enemy extends DynamicObject{

    public Enemy(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.GHOST_SPRITE);
    }
    /*
    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        if(frameCounter%32 ==0 ) {
           isRight = Math.random()<0.5;
        }
    }

     */
}
