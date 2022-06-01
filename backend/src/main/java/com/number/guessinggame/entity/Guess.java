package com.number.guessinggame.entity;

import lombok.Data;

@Data
public class Guess {
    private int[]  playerAnswer = new int[4];
    private Feedback feedback = new Feedback();

}
