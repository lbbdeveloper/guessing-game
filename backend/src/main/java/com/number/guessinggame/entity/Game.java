package com.number.guessinggame.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Game {
    private String id;
    private Player player;
    private String gameStatus;
    private int totalAttempts;
    private int attemptsMade;
    private boolean winner;
    private ArrayList<Guess> history = new ArrayList<>();
    private int[] answer = new int[4];
    private int[] playerInput = new int[4];
    private int score;

}
