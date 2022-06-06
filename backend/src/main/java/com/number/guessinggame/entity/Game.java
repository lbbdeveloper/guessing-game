package com.number.guessinggame.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Game {
    private String id;
    private Player player;
    private String gameStatus;
    private int attemptsRemaining;
    private boolean winner;
    private ArrayList<Guess> history = new ArrayList<>();

}
