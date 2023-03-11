package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

public class Player extends DynamicObject {
    private static final int stepSize = 1;
    private int coins=0;
    private boolean won=false;
    private boolean dead=false;
    private boolean canMove=true;

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

 //   private boolean allowExit = false;

 //   public boolean isAllowExit() {
 //       return allowExit;
 //   }

//    public void setAllowExit(boolean allowExit) {
//        this.allowExit = allowExit;
//    }

    public Player(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PLAYER_SPRITE,"Player");
    }

    public void move(Direction direction) {
        if(!isDead() || isWon())
            super.move(direction, stepSize);
    }

    @Override
    public String toString() {
        return "Player{"+getName()+"[" + xPosition + ":" + yPosition + "]}"+" coins = "+coins+won+","+dead+","+canMove;
    }

    public int getCoins(){
        return coins;
    }

    public void addCoin(int coin){
       this.coins += coin;
       if(this.coins == Configuration.MAX_COIN ) GameMaster.getInstance().getExit().setOpen(true); //setAllowExit(true);

    }

    public boolean isWon(){ return won;}
    public void won(){
        won = true;
        setCanMove(false);
        System.out.println("ПОБЕДА!");
    }
    public boolean isDead(){
        return dead;}
    public void dead() {
        setCanMove(false);
        dead = true;
    }


}
