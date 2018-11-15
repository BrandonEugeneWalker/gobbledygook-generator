package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import model.MarkovChain;

/**
 * Creates a new ChainPopulator.
 * @author brandon walker
 *
 */
public class ChainPopulator {

	/**
	 * Populates a Markov Chain from a file.
	 * 
	 * @param filePath the path for the file to build from
	 * @return a populated Markov Chain
	 * @throws FileNotFoundException
	 */
	public MarkovChain populateMarkovChains(String filePath) throws FileNotFoundException {
		MarkovChain markovChain = new MarkovChain();
		Scanner source;
		source = new Scanner(new File(filePath));
		String prev = null;
		while (source.hasNext()) {
			String nextLine = source.nextLine().toLowerCase().replaceAll("\\p{P}+$", "");
			String[] newNodes = nextLine.split(" ");
			ArrayList<String> arrayNodes = new ArrayList<String>(Arrays.asList(newNodes));
			for (String currentWord : arrayNodes) {
				String trimmedWord = currentWord.trim();
				String strippedWord = trimmedWord.toLowerCase();
				if (strippedWord.equals("")) {
					continue;
				} else {
					if (prev != null) {
						markovChain.addInnerNode(prev, strippedWord);
					} else {
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
