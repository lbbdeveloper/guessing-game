package com.number.guessinggame.data;

import com.number.guessinggame.entity.Game;

import java.util.HashMap;
import java.util.Map;

public class GameData {
    private static Map<String, Game> games ;
    private static GameData instance;

    private GameData() {
        games = new HashMap<>();
    }

    public static synchronized GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public Map<String, Game> getGames() {return games;
    }

    public void setGame (Game game) {
        games.put(game.getId(), game);
    }
}
