package com.number.guessinggame.data;

import com.number.guessinggame.entity.Game;
import com.number.guessinggame.entity.Result;

import java.util.HashMap;
import java.util.Map;

public class ResultData {
    private static Map<String, Result> results = new HashMap<>();

    public static Map<String, Result> getResults() {
        return results;
    }

    public static void setResult(String id, Result result) {
        results.put(id, result);
    }

}
