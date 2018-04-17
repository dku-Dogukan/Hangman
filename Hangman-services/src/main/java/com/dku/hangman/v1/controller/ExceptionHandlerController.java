package com.dku.hangman.v1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.dku.hangman.v1.exceptions.GameNotFoundException;
import com.dku.hangman.v1.exceptions.GuessException;
import com.dku.hangman.v1.exceptions.PlayerAlreadyExistException;
import com.dku.hangman.v1.exceptions.PlayerNotFoundException;


	
	@ControllerAdvice
	public class ExceptionHandlerController {

		@ExceptionHandler(Exception.class)
		public ResponseEntity<String> handleException(Exception e) {
		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bilinmeyen Bir Hata Olustu");
		}

		@ExceptionHandler(GameNotFoundException.class)
		public ResponseEntity<String> handleException(GameNotFoundException e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hata Kodu:404 , Oyun Bulunamadi");
		}
		
		@ExceptionHandler(GuessException.class)
		public ResponseEntity<String> handleException(GuessException e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hata Kodu:400 , Tahmin Yapilamadi");
		}
		
		@ExceptionHandler(PlayerAlreadyExistException.class)
		public ResponseEntity<String> handleException(PlayerAlreadyExistException e) {
			System.out.println("already exist controller");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hata Kodu:400 , Oyuncu Adi Zaten Var");
		}
		
		@ExceptionHandler(PlayerNotFoundException.class)
		public ResponseEntity<String> handleException(PlayerNotFoundException e) {
			System.out.println("already exist controller");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hata Kodu:404 , Oyuncu Bulunamadi");
		}
		

	}
	
	


