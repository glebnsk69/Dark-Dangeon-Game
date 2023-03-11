package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

public class Player extends DynamicObject {
    private static final int stepSize = 1;
    private int coins=0;
    private boolean won = false;
    private boolean canMove=true;

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }


    public Player(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PLAYER_SPRITE,"Player");
    }

    public void move(Direction direction) {
        if(!isDead() || isWon())
            super.move(direction, stepSize);
    }

    @Override
    public String toString() {
        return "Player{"+getName()+"[" + xPosition + ":" + yPosition + "]}";
    }

    public int getCoins(){
        return coins;
    }

    public void addCoin(int coin){
       this.coins += coin;
       if(this.coins == Configuration.MAX_COIN ) GameMaster.getInstance().getExit().setOpen(true); //setAllowExit(true);

    }

    public boolean isWon(){ return won;}

    public void setWon(boolean won) {
        this.won = won;
    }

    @Override
    public void setDead(boolean dead) {
        super.setDead(dead);
    }

    public void dead() {
        setCanMove(false);
        setDead(true);
    }


}
