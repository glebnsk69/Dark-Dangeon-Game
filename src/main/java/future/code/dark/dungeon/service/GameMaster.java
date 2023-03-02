package future.code.dark.dungeon.service;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.domen.*;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static future.code.dark.dungeon.config.Configuration.COIN_CHARACTER;
import static future.code.dark.dungeon.config.Configuration.ENEMIES_ACTIVE;
import static future.code.dark.dungeon.config.Configuration.ENEMY_CHARACTER;
import static future.code.dark.dungeon.config.Configuration.EXIT_CHARACTER;
import static future.code.dark.dungeon.config.Configuration.PLAYER_CHARACTER;

public class GameMaster {

    private static GameMaster instance;

    private final Map map;
    private final List<GameObject> gameObjects;
    private Digit digit;


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
            this.digit = new Digit(2,0,Configuration.DIGIT_PATH);
            this.digit.setDigit(0);

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


        getStaticObjects().forEach(gameObject -> gameObject.render(graphics));
        getEnemies().forEach(gameObject -> {
            gameObject.isRight = gameObject.getXPosition()> getPlayer().getXPosition();
            gameObject.render(graphics);
        });
        getPlayer().render(graphics);
        if(getPlayer().isWon()){

            return;
        }
        if(getPlayer().isDead()) {

          return;
        }
        this.getStaticObjects().forEach(gameObjects-> {
            if(gameObjects instanceof Coin c && !c.isCollected()) {
                if(c.getXPosition()==getPlayer().getXPosition() && c.getYPosition()==getPlayer().getYPosition()) {
                    getPlayer().addCoin(1);
                    c.setCollected(true);
                }
            }
        });
        digit.setDigit(getPlayer().getCoins());
        digit.render(graphics);

    }

    public Player getPlayer() {
        return (Player) gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Player)
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
