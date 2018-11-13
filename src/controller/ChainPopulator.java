package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.MarkovChain;

public class ChainPopulator {

	public MarkovChain populateMarkovChains(String filePath) {
		MarkovChain buildChain = new MarkovChain();
		Scanner source;
		source = new Scanner(filePath);
		String prev;
		String current;
		while(source.hasNext()) {
			String[] newNodes = source.nextLine().split(" ");
			
		}

	}
}
