package com.dku.hangman.v1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dku.hangman.v1.domain.Game;
import com.dku.hangman.v1.enums.GameStatus;
import com.dku.hangman.v1.exceptions.GameNotFoundException;
import com.dku.hangman.v1.exceptions.GuessException;
import com.dku.hangman.v1.repository.GameRepository;
import com.dku.hangman.v1.resources.ResponseResource;


@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private PlayerService playerService;

	
	public ResponseResource addGame(Game game) {
		ResponseResource response = new ResponseResource();
		if (playerService.checkExistenceOfPlayer(game.getPlayer()) == true) {
			gameRepository.save(game);
			response.setDescription("Oyun Basariyla Baslatildi, Game ID:" + game.getId());
			return response;
		} else {
			response.setDescription("Oyun Baslatabilmek Icin Once Oyuncu Olusturmaniz Gerekmektedir");

			return response;
		}

	}

	
	public ResponseResource allGames() throws Exception {
		ResponseResource response = new ResponseResource();
		List<Game> allGames = new ArrayList<>();
		gameRepository.findAll().forEach(t -> allGames.add(t));
		if (allGames.isEmpty() == false) {

			response.setDescription("Oyunlar Basariyla Cekildi");
			response.setTransferData(allGames);
			return response;
		}

		else {
			throw new Exception();
		}

	}

	public Game getGame(Integer id) throws GameNotFoundException {
		Game game = gameRepository.findOne(id);

		if (game != null) {
			return game;
		} else {

			throw new GameNotFoundException();
		}
	}

	public ResponseResource deleteGame(Integer id) throws GameNotFoundException {
		ResponseResource response = new ResponseResource();
		if (gameRepository.exists(id) == true) {
			response.setDescription(
					"Oyun Sonlandirildi, Tahmin Edilen Kelime:" + gameRepository.findOne(id).getGuessedWord());
			gameRepository.delete(id);
			return response;
		} else {
			throw new GameNotFoundException();
		}
	}

	public ResponseResource guess(Integer id, String guess) throws GuessException {
		ResponseResource response = new ResponseResource();
		Game game = gameRepository.findOne(id);
		if (guess.length() != 1) {
			throw new GuessException();
		}
		if (game.getGuessesLeft() > 0 && game.getGameStatus().toString().equals("DevamEdiyor")) {
			System.out.println("guessleft>0");
			if (!game.getGuessedWord().contains(guess)) {

				game.setIncorrectLetters(guess + "," + game.getIncorrectLetters());
				game.setGuessesLeft(game.getGuessesLeft() - 1);
				game.setGuesses(game.getGuesses() + 1);
				response.setDescription("Tahmin Basari Ile Yapildi, Yanlis Tahmin Edilen Harfler: ["
						+ game.getIncorrectLetters() + "]  ,Kalan Tahmin Sayisi:" + game.getGuessesLeft() + "        "
						+ game.getMaskedWord());

				if (game.getGuessesLeft() == 0) {
					game.setGameStatus(GameStatus.Kaybedildi.toString());
					response.setDescription("Oyun Kaybedildi, Tahmin Edilen Kelime:" + game.getGuessedWord());
				}

			} else if (game.getGuessedWord().contains(guess)) {

				String correctLetters = game.getMaskedWord().replaceAll("[*]", "");// to get unmasked chars.
				game.setMaskedWord(game.getGuessedWord().replaceAll("[^" + guess + "" + correctLetters + "]", "*"));
				response.setDescription("Tahmin Basari Ile Yapildi,Yanlis Tahmin Edilen Harfler: ["
						+ game.getIncorrectLetters() + "]  ,Kalan Tahmin Sayisi:" + game.getGuessesLeft() + "        "
						+ game.getMaskedWord());

				if (game.getMaskedWord().equals(game.getGuessedWord())) {

					game.setGameStatus(GameStatus.Kazanildi.toString());
					response.setDescription("Kazanildi");
				}

			}
			gameRepository.save(game);
			return response;

		}

		else {
			if (game.getGameStatus().toString().equals("Kazanildi")) {
				response.setDescription("Kazanilmis Oyun , Baska Tahmin Hakki Bulunmamaktadir");
			}

			else {
				response.setDescription("Kaybedilmis Oyun , Baska Tahmin Hakki Bulunmamaktadir");
			}
		}
		return response;
	}

}