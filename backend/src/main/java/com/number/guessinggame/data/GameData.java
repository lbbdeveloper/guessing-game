package com.number.guessinggame.data;

import com.number.guessinggame.entity.Game;
import java.util.HashMap;
import java.util.Map;

public class GameData {
    private static Map<String, Game> games = new HashMap<>() ;

    public static Map<String, Game> getGames() {
        return games;
    }

    public static void setGames(Game game) {
        games.put(game.getId(), game);
    }
}
