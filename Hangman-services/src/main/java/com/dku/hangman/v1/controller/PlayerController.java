package com.dku.hangman.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dku.hangman.v1.domain.Player;
import com.dku.hangman.v1.exceptions.PlayerAlreadyExistException;
import com.dku.hangman.v1.exceptions.PlayerNotFoundException;
import com.dku.hangman.v1.resources.ResponseResource;
import com.dku.hangman.v1.services.PlayerService;

@RestController
@RequestMapping("/hangman/v1")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@RequestMapping(method = RequestMethod.POST, value = "/player")
	public ResponseEntity<ResponseResource> addPlayer(@RequestBody Player player) throws PlayerAlreadyExistException{

		return ResponseEntity.ok().body(playerService.addPlayer(player));

	}

	@RequestMapping("/player")
	public ResponseEntity<ResponseResource> allPlayers() throws Exception {
		return ResponseEntity.ok().body(playerService.allPlayers());

	}

	@RequestMapping("/player/{id}")
	public ResponseEntity<ResponseResource> getPlayer(@PathVariable Integer id) throws PlayerNotFoundException {
		return ResponseEntity.ok().body(playerService.getPlayer(id));

	}
}
