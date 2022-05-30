package com.number.guessinggame.data;

import com.number.guessinggame.entity.Game;

import java.util.ArrayList;

public class GameData {
    private  static ArrayList<Game> games;
    private static GameData instance;

    private GameData() {
        games = new ArrayList<>();
    }

    public static synchronized GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGame (Game game) {
        games.add(game);
    }
}
