package com.number.guessinggame.services;

import com.number.guessinggame.data.GameData;
import com.number.guessinggame.data.ResultData;
import com.number.guessinggame.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
@AllArgsConstructor
public class GameService {

    private static String APIBaseURL = "https://www.random.org/integers/?num=1&min=0&max=7&col=1&base=10&format=plain&rnd=new";

    public String startGame(Player player) throws IOException {
        Game game = new Game();
        Result gameResult = new Result();
        game.setGameStatus("In Progress");
        game.setId(IDGenerator());
        game.setPlayer(player);
        game.setAttemptsRemaining(10);
        GameData.setGames(game);

        //when not using API
//        int[] answer = new int[] {1,2,3,4};
//        gameResult.setAnswer(answer);
//        Set<Integer> set1 = new HashSet<>(Arrays.asList(1,2,3,4));
//        gameResult.setAnswerSet(set1);


        int[] answer = new int[4];
        Set<Integer> set = new HashSet<>();
        int i = 0;
        int num;
        while (i < 4) {
            num = Integer.parseInt(getAPIres(APIBaseURL));
            if (set.contains(num)) {
                continue;
            } else {
                set.add(num);
                answer[i] = num;
            }
            i++;
        }
        gameResult.setAnswer(answer);
        gameResult.setAnswerSet(set);
        ResultData.setResult(game.getId(), gameResult);
        return game.getId();


    }

    public Guess playGame(String gameId, Guess playerGuess) {
        Game game = GameData.getGames().get(gameId);
        Result result = ResultData.getResults().get(gameId);

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
        Game game = GameData.getGames().get(gameId);

        return game;
    }

    public Map<String, Result> checkGameResult() {
        Map<String, Result> results = ResultData.getResults();

        return results;
    }

    //Helper Functions:
    public String IDGenerator () {

        Set<String> idSet = GameData.getGames().keySet();
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

    private static String getAPIres(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            System.out.println("GET request error");
        }
        return null;
    }

    private Boolean checkAnswer(Result result, Guess playerGuess) {
        int[] answer = result.getAnswer();
        Set<Integer> answerSet = result.getAnswerSet();

        Feedback feedback = playerGuess.getFeedback();
        int[] playerAnswer = playerGuess.getPlayerAnswer();
        //1st check
        boolean correctNum = checkNumberContains(answerSet, playerAnswer);
        feedback.setCorrectNum(correctNum);
        if (correctNum) {
            checkWinner(answer, playerAnswer, feedback);
            return feedback.isWinner();
        } else {
            return false;
        }
    }

    public boolean checkNumberContains (Set<Integer> answerSet, int[] playerAnswer) {
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
