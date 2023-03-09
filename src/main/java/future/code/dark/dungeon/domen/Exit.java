package future.code.dark.dungeon.domen;

import javax.swing.*;
import java.awt.*;

import static future.code.dark.dungeon.config.Configuration.*;

public class Exit extends GameObject{
    private boolean closed = true;
    Image imageClose;

    public Exit(int xPosition, int yPosition) {
        super(xPosition, yPosition, EXIT_SPRITE);
        this.imageClose = new ImageIcon(WALL_SPRITE).getImage().getScaledInstance(SPRITE_SIZE,SPRITE_SIZE,0);
        System.out.println("Exit");
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }


    @Override
    public void render(Graphics graphics) {
        super.render(graphics);

        if(this.closed){
            System.out.println("Closed!");
            graphics.drawImage(imageClose, xPosition * SPRITE_SIZE, yPosition  * SPRITE_SIZE, null);
        }

    }
}
