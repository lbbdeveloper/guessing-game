package com.number.guessinggame.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.number.guessinggame.entity.Game;
import com.number.guessinggame.entity.Player;
import com.number.guessinggame.services.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

//@CrossOrigin("http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

//    public GameController(GameService gameService) {this.gameService = gameService;}
    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody Player player) {

        return ResponseEntity.ok(gameService.startGame(player));
    }
}
