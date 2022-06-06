package com.number.guessinggame.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.number.guessinggame.entity.*;
import com.number.guessinggame.services.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<String> start(@RequestBody Player player) throws IOException {
        return ResponseEntity.ok(gameService.startGame(player));
    }

    @PostMapping("/play/{id}")
    public ResponseEntity<Guess> playGame(@RequestBody Guess req, @PathVariable String id) {
        Guess playerGuess = gameService.playGame(id, req);
        return ResponseEntity.ok(playerGuess);
    }

    @GetMapping("/game-status/{id}")
    public ResponseEntity<Game> checkGameStatus(@PathVariable String id) {
        Game game = gameService.checkGameStatus(id);
        return ResponseEntity.ok(game);
    }

    //debug function
    @GetMapping("/game-answer")
    public ResponseEntity<Map<String, Result>> checkGameAnswer() {
        Map<String, Result> results = gameService.checkGameResult();
        return ResponseEntity.ok(results);
    }
}
