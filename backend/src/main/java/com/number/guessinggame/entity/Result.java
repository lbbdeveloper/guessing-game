package com.number.guessinggame.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Result {
    private String id;
    private int[] answer = new int[4];
    private Set<Integer> answerSet = new HashSet<>();

}
