package com.dku.hangman.v1.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dku.hangman.v1.domain.Player;
import com.dku.hangman.v1.exceptions.PlayerAlreadyExistException;
import com.dku.hangman.v1.exceptions.PlayerNotFoundException;
import com.dku.hangman.v1.repository.PlayerRepository;
import com.dku.hangman.v1.resources.ResponseResource;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	
	public ResponseResource addPlayer(Player player) throws PlayerAlreadyExistException {
		ResponseResource response = new ResponseResource();
		if (checkExistenceOfPlayer(player.getName()) == false && player.getAge()!=null) {
			playerRepository.save(player);
			response.setDescription("Oyuncu Basariyla Yaratildi");
			return response;
		} else {
			
			throw new PlayerAlreadyExistException();
			
		}

	}

	public ResponseResource allPlayers() throws Exception {

		ResponseResource response = new ResponseResource();
		List<Player> players = new ArrayList<>();
		playerRepository.findAll().forEach(t -> players.add(t));
		if (players.isEmpty()==false) {
			response.setDescription("Oyuncular Basari Ile Cekildi");
			response.setTransferData(players);
			return response;
		}

		else {
			throw new Exception();
		}
	}

	public ResponseResource getPlayer(int id) throws PlayerNotFoundException {
		ResponseResource response = new ResponseResource();
		Player player=playerRepository.findOne(id);
		if(playerRepository.exists(id)==true) {
			response.setDescription("Oyuncu Bilgisi Cekildi");
			response.setTransferData(player);
			return response;
		}
		else {
			throw new PlayerNotFoundException();
		}
		
	
	}

	public boolean checkExistenceOfPlayer(String name) {

		List<String> playerNames = new ArrayList<>();
		playerRepository.findAll().forEach(t -> playerNames.add(t.getName()));

		for (String names : playerNames) {
			if (names.equals(name)) {
				return true;
			}
		}

		return false;
	}

}
