package com.number.guessinggame.entity;

import lombok.Data;

@Data
public class Guess {
    private int[]  playerInput = new int[4];
    private Feedback feedback;
}
