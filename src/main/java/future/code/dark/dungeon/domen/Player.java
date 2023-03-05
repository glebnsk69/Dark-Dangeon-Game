package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

public class Player extends DynamicObject {
    private static final int stepSize = 1;
    private int coins=0;
    private boolean won=false;
    private boolean dead=false;

    public Player(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PLAYER_SPRITE);
    }

    public void move(Direction direction) {
        super.move(direction, stepSize);
    }

    @Override
    public String toString() {
        return "Player{[" + xPosition + ":" + yPosition + "]}";
    }

    public int getCoins(){
        return coins;
    }
    public void addCoin(int coin){
        this.coins += coin;
        if(this.coins == Configuration.MAX_COIN ) won();
    }

    public boolean isWon(){ return won;}
    public void won(){
        System.out.println("Все собрал");
        won=true;
    }
    public boolean isDead(){return dead;}
    public void dead() {dead = true;}
}
