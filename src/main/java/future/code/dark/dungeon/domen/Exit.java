package future.code.dark.dungeon.domen;

import javax.swing.*;
import java.awt.*;

import static future.code.dark.dungeon.config.Configuration.*;

public class Exit extends GameObject{
    private boolean open = false;
    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


    Image imageClose;

    public Exit(int xPosition, int yPosition) {
        super(xPosition, yPosition, EXIT_SPRITE);
        this.imageClose = new ImageIcon(WALL_SPRITE).getImage().getScaledInstance(SPRITE_SIZE,SPRITE_SIZE,0);
    }

       @Override
    public void render(Graphics graphics) {
           if(this.open) super.render(graphics);
           else graphics.drawImage(imageClose, xPosition * SPRITE_SIZE, yPosition  * SPRITE_SIZE, null);
        }


}
