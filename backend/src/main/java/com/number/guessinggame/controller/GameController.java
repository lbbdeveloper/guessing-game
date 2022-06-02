package com.number.guessinggame.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.number.guessinggame.entity.Feedback;
import com.number.guessinggame.entity.Game;
import com.number.guessinggame.entity.Guess;
import com.number.guessinggame.entity.Player;
import com.number.guessinggame.services.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin("http://localhost:3000")
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    @GetMapping("/start")
    public ResponseEntity<Feedback> test() {
        log.info("test");
        Feedback feedback = new Feedback();
        return ResponseEntity.ok(feedback);
    }

    @PostMapping("/start")
    public ResponseEntity<String> start(@RequestBody Player player) {
        log.info("Game Started by: {}", player.getUsername());
        return ResponseEntity.ok(gameService.startGame(player));
    }

    @PostMapping("/play/{id}")
    public ResponseEntity<Guess> playGame(@RequestBody Guess req, @PathVariable String id) {
        log.info("Player Guess: {}", req.getPlayerAnswer());
        Guess playerGuess = gameService.playGame(id, req);
        return ResponseEntity.ok(playerGuess);
    }

    @GetMapping("/game-status/{id}")
    public ResponseEntity<Game> checkGameStatus(@PathVariable String id) {
        Game game = gameService.checkGameStatus(id);
        log.info("Game Status: {}");
        return ResponseEntity.ok(game);
    }

    @GetMapping("/game-history/{id}")
    public ResponseEntity<ArrayList<Guess>> checkGameHistory(@PathVariable String id) {
        ArrayList<Guess> history = gameService.checkGameHistory(id);
        log.info("Game history: {}", history);
        return ResponseEntity.ok(history);
    }
}
