package future.code.dark.dungeon.service;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.domen.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static future.code.dark.dungeon.config.Configuration.*;
import static future.code.dark.dungeon.config.Configuration.SPRITE_SIZE;

public class GameMaster {

    private static GameMaster instance;

    private final Map map;
    private final List<GameObject> gameObjects;
    private final Digit digit;
    private final Image gameOwer;
    private final Image gameWin;

    public static synchronized GameMaster getInstance() {
        if (instance == null) {
            instance = new GameMaster();
        }
        return instance;
    }

    private GameMaster() {
        try {
            this.map = new Map(Configuration.MAP_FILE_PATH);
            this.gameObjects = initGameObjects(map.getMap());
            this.digit = new Digit(17,0,Configuration.DIGIT_PATH);
            this.digit.setDigit(0);
//            System.out.println(getMap().getWidth()*SPRITE_SIZE);
            this.gameOwer = new ImageIcon(GAME_OVER).getImage().getScaledInstance(getMap().getWidth()*SPRITE_SIZE,getMap().getHeight()*SPRITE_SIZE,0);
            this.gameWin = new ImageIcon(GAME_WIN  ).getImage().getScaledInstance(getMap().getWidth()*SPRITE_SIZE,getMap().getHeight()*SPRITE_SIZE,0);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<GameObject> initGameObjects(char[][] map) {
        List<GameObject> gameObjects = new ArrayList<>();
        Consumer<GameObject> addGameObject = gameObjects::add;
        Consumer<Enemy> addEnemy = enemy -> {if (ENEMIES_ACTIVE) gameObjects.add(enemy);};

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case EXIT_CHARACTER -> addGameObject.accept(new Exit(j, i));
                    case COIN_CHARACTER -> addGameObject.accept(new Coin(j, i));
                    case ENEMY_CHARACTER -> addEnemy.accept(new Enemy(j, i));
                    case PLAYER_CHARACTER -> addGameObject.accept(new Player(j, i));
                }
            }
        }

        return gameObjects;
    }


    public void renderFrame(Graphics graphics) {

        getMap().render(graphics);
        graphics.setColor(Color.WHITE);
        graphics.drawString(getPlayer().toString(), 10, 20); //Отображение позиции героя
        graphics.drawString("Coins:"+getPlayer().getCoins()+"/9",10,40);

        getStaticObjects().forEach(gameObject -> gameObject.render(graphics));  // отображение статических объектов

        getEnemies().forEach(gameObject -> {
            if(gameObject.equels(getPlayer())) getPlayer().dead();
            gameObject.isRight = gameObject.getXPosition()> getPlayer().getXPosition();
            gameObject.render(graphics);

            if(gameObject.frameCounter%ENEMY_SPEED==0) {  // движение врагов
                if (gameObject.getXPosition() > getPlayer().getXPosition())
                    gameObject.move(DynamicObject.Direction.LEFT, 1);
                else gameObject.move(DynamicObject.Direction.RIGHT, 1);
                if (gameObject.getYPosition() > getPlayer().getYPosition())
                    gameObject.move(DynamicObject.Direction.UP, 1);
                else gameObject.move(DynamicObject.Direction.DOWN, 1);
            }
        });

        digit.setDigit(getPlayer().getCoins());
        digit.render(graphics);

        getPlayer().render(graphics);
        if(getExit().equels(getPlayer()) && getExit().isOpen()) {
            getPlayer().setCanMove(false);
            getEnemies().forEach(enemy -> enemy.setDead(true));
            graphics.drawImage(gameWin,0,0,null);
        }

        this.getStaticObjects().forEach(gameObjects-> {
            if(gameObjects instanceof Coin c && !c.isCollected()) {
                if(c.getXPosition()==getPlayer().getXPosition() && c.getYPosition()==getPlayer().getYPosition()) {
                    getPlayer().addCoin(1);
                    c.setCollected(true);
                }
            }
        });

        if(getPlayer().isDead()) graphics.drawImage(gameOwer,0,0,null);


    }

    public Player getPlayer() {
        return (Player) gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Player)
                .findFirst()
                .orElseThrow();
    }

    public Exit getExit() {
        return (Exit) gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Exit)
                .findFirst()
                .orElseThrow();
    }

    private List<GameObject> getStaticObjects() {
        return gameObjects.stream()
                .filter(gameObject -> !(gameObject instanceof DynamicObject))
                .collect(Collectors.toList());
    }

    private List<Enemy> getEnemies() {
        return gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Enemy)
                .map(gameObject -> (Enemy) gameObject)
                .collect(Collectors.toList());
    }
    public Map getMap() {
        return map;
    }

}
