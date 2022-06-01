package com.number.guessinggame.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
public class Game {
    private String id;
    private Player player;
    private String gameStatus;
    private int totalAttemptsAllowed;
    private int attemptsMade;
    private boolean winner;
    private ArrayList<Guess> history = new ArrayList<>();
    private int[] answer = new int[4];
    private Set<Integer> answerSet = new HashSet<>();
    private int[] playerInput = new int[4];
    private int score;

}
