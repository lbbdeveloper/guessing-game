package com.number.guessinggame.entity;

import lombok.Data;

@Data
public class Game {
    private String id;
    private Player player;
    private String gameStatus;
//    private int totalAttempts;
//    private int attemptsMade;
//    private boolean winner;
//    private Guess[] history = new Guess[4];
//    private int[] answer = new int[4];
//    private int[] playerInput = new int[4];
//    private int score;

}
