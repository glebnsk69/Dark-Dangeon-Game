package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static future.code.dark.dungeon.config.Configuration.SPRITE_SIZE;

public class Digit extends GameObject{
    ArrayList<Image> images = new ArrayList<>();
    private int currentDigit=0;

    public Digit(int xPosition, int yPosition, String imagePath) {
        super(xPosition, yPosition, imagePath);
        for (int i = 0; i < 11; i++) {
            images.add(new ImageIcon(imagePath+i+".png").getImage().getScaledInstance(SPRITE_SIZE,SPRITE_SIZE,0));
        }

    }
public void setDigit(int digit){
        if(digit<11) this.currentDigit = digit;
        else {this.currentDigit = 10;
            System.out.println("===>"+currentDigit);
        }

    }//Configuration.MAX_COIN;}
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(images.get(currentDigit), xPosition * SPRITE_SIZE, yPosition  * SPRITE_SIZE, null);
    }
}
