package view;

import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.ChainPopulator;
import model.MarkovChain;

public class ChainConsole {
	private static MarkovChain sherlockChain = new MarkovChain();
	private static MarkovChain littleHouseChain = new MarkovChain();
	private static Scanner scan = new Scanner(System.in);
	private static ChainPopulator populator = new ChainPopulator();
	

	

	public static void main(String[] args) {
		
		printGreeting();
		
		buildBookChains();
		
		promptUserForTask();

	}
	
	private static void printGreeting() {
		System.out.println("Welcome to the Gobbledygook Generator!");
		System.out.println("Building generators....");
	}
	
	private static void buildBookChains() {
		try {
			sherlockChain = populator.populateMarkovChains("books/The_Adventures_of_Sherlock_Holmes.txt");
			littleHouseChain = populator.populateMarkovChains("books/Little_House_In_The_Big_Woods.txt");
		} catch (FileNotFoundException fnfe) {
			System.out.println("The book files were not found.");
			System.exit(1);
		}
		
		System.out.println("Build complete.");
	}
	
	private static String promptUserForChoice() {
		System.out.println("Which book would you like to generate gobbledygook from? [exit] to exit.");
		System.out.println("The Adventures of Sherlock Holmes? [sherlock]");
		System.out.println("Little House In TheBig Woods? [woods]");
		return scan.nextLine();
	}
	
	private static void printGobbledygook(MarkovChain chain) {
		String first = chain.getFirst();
		String current = first;
		System.out.print(first + " ");
		int count = 1;
		while(count <= 200) {
			current = chain.getNext(current);
			System.out.print(current + " ");
			if(count % 20 == 0) {
				System.out.println();
			}
			count++;
		}
		System.out.println();
		System.out.println("Printing complete.");
	}
	
	private static void promptUserForTask() {
		String answer = promptUserForChoice();
		
		if(answer.equals("sherlock")) {
			printGobbledygook(sherlockChain);
		}
		if(answer.equals("woods")) {
			printGobbledygook(littleHouseChain);
		}
		if(answer.equals("exit")) {
			System.out.println("Now exiting...");
			System.exit(0);
		}
		else {
			System.out.println("Please input valid book.");
			promptUserForTask();
		}
	}

}
