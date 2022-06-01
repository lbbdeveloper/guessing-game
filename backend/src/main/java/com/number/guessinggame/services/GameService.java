package com.number.guessinggame.services;

import com.number.guessinggame.data.GameData;
import com.number.guessinggame.entity.Feedback;
import com.number.guessinggame.entity.Game;
import com.number.guessinggame.entity.Guess;
import com.number.guessinggame.entity.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    public String startGame(Player player) {
        Game game = new Game();
        game.setGameStatus("In Progress");
        game.setId(UUID.randomUUID().toString());
        game.setPlayer(player);
        game.setTotalAttemptsAllowed(10);
        game.setAttemptsMade(0);
        game.setScore(0);

        //set placeholder answer

        //call api
        // int num = ...api;
        //Set<Integer> set = new Set<>();
        //List<Integer> list = new ArrayList<>();
        //int[] answer = new int[4];
        //for (int i = 0 ; i < 4; i++) {
        //while (i < 4) {
        //if (set.contains(num) {
        // continue;
        // } else {
        // answer[i] = num;
        //set.add(num);
        //i++;
        //}

        //}
        int[] answer = new int[]{1,2,3,4};
        game.setAnswer(answer);
        Set<Integer> set = new HashSet<>(Arrays.asList(1,2,3,4));
        game.setAnswerSet(set);


        GameData.getInstance().setGame(game);
        return game.getId();
    }

    public Guess playGame(String gameId, Guess playerGuess) {
        Game game = GameData.getInstance().getGames().get(gameId);
//        for (int i = 0; i < game.getTotalAttemptsAllowed(); i++) {
//            checkAnswer(game, playerGuess);
//
//        }
        checkAnswer(game, playerGuess);

        return playerGuess;
    }

    public Guess checkAnswer (Game game, Guess playerGuess) {

        int[] answer = game.getAnswer();
        Set<Integer> answerSet = game.getAnswerSet();

        Feedback feedback = playerGuess.getFeedback();
        int[] playerAnswer = playerGuess.getPlayerAnswer();
        //check result for feedback #1:
        boolean correctNum = checkNumberContain(answerSet, playerAnswer);
        feedback.setCorrectNum(correctNum);
        
        //check result for feedback #2 & #3:
        checkWinner(answer, playerAnswer, feedback);

        return playerGuess;
    }
    
    public boolean checkNumberContain (Set<Integer> answerSet, int[] playerAnswer) {
        for (int ans : playerAnswer) {
            if (answerSet.contains(ans)) {
                return true;
            } 
        }
        return false;
    }

    public void checkWinner (int[] answer, int[] playerAnswer, Feedback feedback) {
        int re = 0;
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == playerAnswer[i]) {
                re++;
            }
        }
        if (re > 0) {
            feedback.setCorrectNumAndLocation(true);
        }
        if (re == 4) {
            feedback.setWinner(true);
        }
        
    }
}
