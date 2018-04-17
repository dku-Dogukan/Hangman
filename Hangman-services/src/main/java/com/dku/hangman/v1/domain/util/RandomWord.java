package com.dku.hangman.v1.domain.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class RandomWord {
	
	public String random() {
		String word="";
		
		try {
			
			Random random=new Random();
			int randomInt=random.nextInt(101);
			BufferedReader reader = new BufferedReader(new FileReader("wordList.txt"));
			word=reader.readLine();

			for(int x=0;x<randomInt;x++) {
				word=reader.readLine();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return word;
	}

}