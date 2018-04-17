package com.dku.hangman.v1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dku.hangman.v1.domain.Game;
import com.dku.hangman.v1.exceptions.GameNotFoundException;
import com.dku.hangman.v1.resources.ResponseResource;
import com.dku.hangman.v1.services.GameService;

@RestController
@RequestMapping("/hangman/v1")
public class GameController {

	@Autowired
	private GameService gameService;

	@RequestMapping
	public String intro() {
		return "!Welcome to Hangman Game!";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/startGame")
	public ResponseEntity<ResponseResource> addGame(@RequestBody Game game) {
		return ResponseEntity.ok().body(gameService.addGame(game));

	}

	@RequestMapping("/listGames")
	public ResponseEntity<ResponseResource> allGames() throws Exception {

		return ResponseEntity.ok().body(gameService.allGames());

	}

	@RequestMapping("/game/{id}")
	public Game getGame(@PathVariable Integer id) throws GameNotFoundException {
		return gameService.getGame(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/game/{id}")
	public ResponseEntity<ResponseResource> deleteGame(@PathVariable Integer id) throws GameNotFoundException{
		return ResponseEntity.ok().body(gameService.deleteGame(id));
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/{guess}")
	public ResponseEntity<ResponseResource> guess(@PathVariable Integer id, @PathVariable String guess) {
		return ResponseEntity.ok().body(gameService.guess(id, guess));
	}

}
