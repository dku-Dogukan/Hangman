package com.dku.hangman.v1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.dku.hangman.v1.domain.util.RandomWord;
import com.dku.hangman.v1.enums.GameStatus;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	private String player;
	private int guesses=0;
	private int guessesLeft = 6;
	private String guessedWord=new RandomWord().random();
	private String incorrectLetters="";
	private String gameStatus=GameStatus.DevamEdiyor.toString();
	private String maskedWord=guessedWord.replaceAll("(?s).", "*");

	public String getMaskedWord() {
		return maskedWord;
	}

	public void setMaskedWord(String maskedWord) {
		this.maskedWord = maskedWord;
	}

	public Game() {
		// TODO Auto-generated constructor stub
	}

	public Game(String player) {
		super();
		this.player = player;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public int getGuesses() {
		return guesses;
	}

	public void setGuesses(int guesses) {
		this.guesses = guesses;
	}

	public int getGuessesLeft() {
		return guessesLeft;
	}

	public void setGuessesLeft(int guessesLeft) {
		this.guessesLeft = guessesLeft;
	}

	public String getGuessedWord() {
		return guessedWord;
	}

	public void setGuessedWord(String guessedWord) {

		this.guessedWord = guessedWord;
	}

	public String getIncorrectLetters() {
		return incorrectLetters;
	}

	public void setIncorrectLetters(String incorrectLetters) {
		this.incorrectLetters = incorrectLetters;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Integer getId() {
		return id;
	}
}
