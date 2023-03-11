package future.code.dark.dungeon.domen;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

import static future.code.dark.dungeon.config.Configuration.SPRITE_SIZE;

public abstract class GameObject {
    private final String name;
    private final Image image;
    protected int xPosition;
    protected int yPosition;

    public String getName() {
        return name;
    }

    public GameObject(int xPosition, int yPosition, String imagePath) {
        this(xPosition,yPosition,imagePath,"Unknown animated object");
    }
    public GameObject(int xPosition, int yPosition, String imagePath,String name) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.image = new ImageIcon(imagePath).getImage().getScaledInstance(SPRITE_SIZE,SPRITE_SIZE,0);
        this.name = name;
    }
    public Image getImage(){
        return image;
    }
    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }
    public void render(Graphics graphics) {
        graphics.drawImage(image, xPosition * SPRITE_SIZE, yPosition  * SPRITE_SIZE, null);
    }
    public boolean equels(GameObject obj){
        if(getXPosition()==obj.getXPosition() && getYPosition()==obj.getYPosition()) return true;
        return false;
    }

}
