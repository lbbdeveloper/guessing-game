package com.number.guessinggame.services;

import com.number.guessinggame.data.GameData;
import com.number.guessinggame.entity.Feedback;
import com.number.guessinggame.entity.Game;
import com.number.guessinggame.entity.Guess;
import com.number.guessinggame.entity.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    public Game startGame(Player player) {
        Game game = new Game();
        game.setGameStatus("In Progress");
        game.setId(UUID.randomUUID().toString());
        game.setPlayer(player);
        GameData.getInstance().setGame(game);
        return game;
    }

    public Game playGame(Game game, Guess playerInput) {

        return game;
    }

    public Feedback checkAnswer (Game game, Guess playerInput) {
        Feedback result = new Feedback();

        return result;
    }

    public boolean checkWinner (Game game, Guess playerInput) {
        return false;
    }
}
