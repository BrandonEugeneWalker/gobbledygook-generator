package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import model.MarkovChain;

/**
 * Creates a new ChainPopulator.
 * 
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
			String nextLine = source.nextLine().toLowerCase();
			String replacedPunct = nextLine.replaceAll("\\p{P}", "");
			String[] newNodes = replacedPunct.split(" ");
			ArrayList<String> arrayNodes = new ArrayList<String>(Arrays.asList(newNodes));
			for (String currentWord : arrayNodes) {
				if (currentWord.equals("")) {
					continue;
				} else {
					if (prev != null) {
						markovChain.addInnerNode(prev, currentWord);
					} else {
						markovChain.addOuterNode(currentWord);
					}
					prev = currentWord;
				}

			}

		}
		source.close();
		return markovChain;
	}
}
