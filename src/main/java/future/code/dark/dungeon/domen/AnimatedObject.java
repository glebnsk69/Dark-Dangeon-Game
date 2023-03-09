package future.code.dark.dungeon.domen;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static future.code.dark.dungeon.config.Configuration.SPRITE_SIZE;

public abstract class AnimatedObject extends GameObject implements ActionListener {

    ArrayList<Image> images = new ArrayList<>();
    int spriteNum=(int)(Math.random()*3f);
    public int frameCounter=0;
    public boolean isRight = true;

    public AnimatedObject(int xPosition, int yPosition, String imagePath) {
        super(xPosition, yPosition, imagePath);
        images.add(new ImageIcon(imagePath+"tile000.png").getImage().getScaledInstance(SPRITE_SIZE,SPRITE_SIZE,0));
        images.add(new ImageIcon(imagePath+"tile001.png").getImage().getScaledInstance(SPRITE_SIZE,SPRITE_SIZE,0));
        images.add(new ImageIcon(imagePath+"tile002.png").getImage().getScaledInstance(SPRITE_SIZE,SPRITE_SIZE,0));
        images.add(new ImageIcon(imagePath+"tile003.png").getImage().getScaledInstance(SPRITE_SIZE,SPRITE_SIZE,0));
    }

    @Override
    public void render(Graphics graphics) {
//        super.render(graphics);
        if(spriteNum>=3)spriteNum=0;
        if(isRight) graphics.drawImage(images.get(spriteNum), xPosition * SPRITE_SIZE, yPosition  * SPRITE_SIZE, null);
        else graphics.drawImage(images.get(spriteNum),xPosition * SPRITE_SIZE, yPosition  * SPRITE_SIZE, xPosition * SPRITE_SIZE+SPRITE_SIZE, yPosition  * SPRITE_SIZE+SPRITE_SIZE,SPRITE_SIZE, 0, 0, SPRITE_SIZE, null);
        if(frameCounter%10==0) spriteNum++;
        if(frameCounter>Integer.MAX_VALUE-1) frameCounter = 0;
        frameCounter++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
