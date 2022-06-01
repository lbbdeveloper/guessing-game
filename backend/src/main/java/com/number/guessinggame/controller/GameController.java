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

//@CrossOrigin("http://localhost:4200")
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<String> start(@RequestBody Player player) {
        log.info("Game Started by: {}", player.getUsername());
        return ResponseEntity.ok(gameService.startGame(player));
    }

    @PostMapping("/play/{id}")
    public ResponseEntity<Guess> playGame(@RequestBody Guess req, @PathVariable String id) {
        log.info("Player Guess: {}", req);
        Guess playerGuess = gameService.playGame(id, req);
        return ResponseEntity.ok(playerGuess);
    }
}
