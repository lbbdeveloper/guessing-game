package com.number.guessinggame.services;

import com.number.guessinggame.data.GameData;
import com.number.guessinggame.data.ResultData;
import com.number.guessinggame.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
public class GameService {

    public String startGame(Player player) {
        Game game = new Game();
        Result gameResult = new Result();
        game.setGameStatus("In Progress");
        game.setId(IDGenerator());
        game.setPlayer(player);
        game.setTotalAttemptsAllowed(10);
        game.setAttemptsRemaining(10);
        game.setScore(0);
        GameData.getInstance().setGame(game);

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
        gameResult.setAnswer(answer);
        Set<Integer> set = new HashSet<>(Arrays.asList(1,2,3,4));
        gameResult.setAnswerSet(set);

        ResultData.getInstance().setResult(game.getId(), gameResult);
        return game.getId();
    }

    public Guess playGame(String gameId, Guess playerGuess) {
        Game game = GameData.getInstance().getGames().get(gameId);
        Result result = ResultData.getInstance().getResults().get(gameId);

        ArrayList<Guess> history = game.getHistory();
        int i = game.getAttemptsRemaining();

        if (i > 0 && !"Finished".equals(game.getGameStatus()) ) {
            game.setAttemptsRemaining(i - 1);
            boolean isWinner = checkAnswer(result, playerGuess);
            history.add(playerGuess);

            if (isWinner) {
                game.setGameStatus("Finished");
                game.setWinner(true);
            }
        } else {
            game.setGameStatus("Finished");
        }

        return playerGuess;
    }

    public Game checkGameStatus (String gameId) {
        Game game = GameData.getInstance().getGames().get(gameId);

        return game;
    }

    public ArrayList<Guess> checkGameHistory (String gameId) {
        Game game = GameData.getInstance().getGames().get(gameId);
        ArrayList<Guess> history = game.getHistory();

        return history;
    }

    //Helper Functions:
    public String IDGenerator () {

        Set<String> idSet = GameData.getInstance().getGames().keySet();
        String id = "";
        if (!idSet.isEmpty()){
            do {
                int idNum = (int)(Math.random() * 100000);
                id = Integer.toString(idNum);
            } while (idSet.contains(id));
        } else {
            int idNum = (int)(Math.random() * 100000);
            id = Integer.toString(idNum);
        }
        return id;
    }

    public Boolean checkAnswer (Result result, Guess playerGuess) {

        int[] answer = result.getAnswer();
        Set<Integer> answerSet = result.getAnswerSet();

        Feedback feedback = playerGuess.getFeedback();
        int[] playerAnswer = playerGuess.getPlayerAnswer();
        //check result for feedback #1:
        boolean correctNum = checkNumberContain(answerSet, playerAnswer);
        feedback.setCorrectNum(correctNum);

        //check result for feedback #2 & #3:
        checkWinner(answer, playerAnswer, feedback);

        return feedback.isWinner();
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
