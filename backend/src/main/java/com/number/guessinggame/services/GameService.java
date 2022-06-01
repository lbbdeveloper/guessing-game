package com.number.guessinggame.services;

import com.number.guessinggame.data.GameData;
import com.number.guessinggame.entity.Feedback;
import com.number.guessinggame.entity.Game;
import com.number.guessinggame.entity.Guess;
import com.number.guessinggame.entity.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    public String startGame(Player player) {
        Game game = new Game();
        game.setGameStatus("In Progress");
        game.setId(UUID.randomUUID().toString());
        game.setPlayer(player);
        game.setTotalAttempts(10);
        game.setAttemptsMade(0);
        game.setScore(0);

        //set placeholder answer
        int[] answer = new int[]{1,2,3,4};
        game.setAnswer(answer);

        GameData.getInstance().setGame(game);
        return game.getId();
    }

    public Game playGame(String gameId, Guess playerInput) {
        Game game = new Game();
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
