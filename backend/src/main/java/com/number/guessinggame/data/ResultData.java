package com.number.guessinggame.data;

import com.number.guessinggame.entity.Game;
import com.number.guessinggame.entity.Result;

import java.util.HashMap;
import java.util.Map;

public class ResultData {
    private static Map<String, Result> results;
    private static ResultData instance;

    private ResultData() {
        results = new HashMap<>();
    }

    public static synchronized ResultData getInstance() {
        if (instance == null) {
            instance = new ResultData();
        }
        return instance;
    }

    public Map<String, Result> getResults() {
        return results;
    }

    public void setResult(String id, Result result) {
        result.setId(id);
        results.put(id, result);
    }
}