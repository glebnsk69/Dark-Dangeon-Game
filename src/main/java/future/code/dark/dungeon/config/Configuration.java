package future.code.dark.dungeon.config;

public interface Configuration {

    String GAME_NAME="Dark Dungeon";
    String MAP_FILE_PATH = "src/main/resources/maps/map2.ber";
    Boolean ENEMIES_ACTIVE = true;
    int GAME_FRAMES_PER_SECOND = 25;
    char WALL_CHARACTER = '1';
    char EXIT_CHARACTER = 'E';
    char LAND_CHARACTER = '0';
    char PLAYER_CHARACTER = 'P';
    char ENEMY_CHARACTER = 'G';
    char COIN_CHARACTER = 'C';
    Integer SPRITE_SIZE = 32;//64;
//    String PLAYER_SPRITE = "src/main/resources/assets/hero/tile000.png";
String PLAYER_SPRITE = "src/main/resources/assets/hero/";
//    String GHOST_SPRITE = "src/main/resources/assets/ghost/tile000.png";
    String GHOST_SPRITE = "src/main/resources/assets/ghost/";
    String DIGIT_PATH = "src/main/resources/assets/tdigit/";
    String WALL_SPRITE = "src/main/resources/assets/land/wall.png";
    String LAND_SPRITE = "src/main/resources/assets/land/ground.png";
    String EXIT_SPRITE = "src/main/resources/assets/land/out.png";
    String COIN_SPRITE = "src/main/resources/assets/land/collectible.png";
    int MAX_COIN = 9;

}
