package com.number.guessinggame.services;

import com.number.guessinggame.entity.Feedback;
import com.number.guessinggame.entity.Game;
import com.number.guessinggame.entity.Guess;
import com.number.guessinggame.entity.Player;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public Game startGame(Player player) {
        Game game = new Game();

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
