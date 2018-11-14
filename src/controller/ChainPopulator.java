package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import model.MarkovChain;

public class ChainPopulator {

	public MarkovChain populateMarkovChains(String filePath) throws FileNotFoundException {
		MarkovChain markovChain = new MarkovChain();
		Scanner source;
		source = new Scanner(new File(filePath));
		String prev = null;
		while(source.hasNext()) {
			String[] newNodes = source.nextLine().toLowerCase().split(" ");
			ArrayList<String> arrayNodes = new ArrayList<String>(Arrays.asList(newNodes));
			for(String currentWord : arrayNodes) {
				String trimmedWord = currentWord.trim();
				String strippedWord = trimmedWord.toLowerCase();
				if(strippedWord.equals("")) {
					continue;
				} else {
					if(prev != null) {
						markovChain.addInnerNode(strippedWord, prev);
					}
					else {
						markovChain.addOuterNode(strippedWord);
					}
					prev = currentWord;
				}
				
			}
			
			
			
		}
		source.close();
		return markovChain;
	}
}
