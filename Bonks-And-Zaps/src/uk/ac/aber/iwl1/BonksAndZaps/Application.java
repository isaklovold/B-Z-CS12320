package uk.ac.aber.iwl1.BonksAndZaps;
import java.io.IOException;
import java.util.Scanner;

import uk.ac.aber.iwl1.BonksAndZaps.mechanics.CannotActException;
import uk.ac.aber.iwl1.BonksAndZaps.mechanics.Game;

/**
 * Main class that runs the menu and game for Bonks And Zaps
 * 
 * @author Isak Wisth Løvold
 * @version 1.0 (6th May 2016)
 *
 */

public class Application {
	
	private Scanner in;
	private int rows, cols;
	private int numBonks, numZaps;
	private int cycles;
	private Game game;
	
	/**
	 * Constructor of the Application Class
	 * Initialising all the instance variables
	 */
	public Application(){
		in = new Scanner(System.in);
		rows = 5;
		cols = 5;
		numBonks = 5;
		numZaps = 1;
		cycles = 5;
	}
	
	/**
	 * Method that runs the menu and allow user to interact with the game
	 * @throws IOException throws exception if input is illegal
	 * @throws CannotActException call this class if being can't act
	 */
	public void runApp() throws IOException, CannotActException {
		String choice;
		System.out.println("@@@@@ HELLO - WELCOME BONKS AND ZAPS @@@@@");
		do {
			printMenu();
			choice = in.next().toUpperCase();
			switch (choice) {
			case "1":
				System.out.println("Game is running");
				game = new Game(rows, cols, numBonks, numZaps, cycles);
				break;
			case "2":
				System.out.println("Change settings:");
				settings();
				break;
			case "Q":
				System.out.println("@@@@@ Goodbye, hope you had fun playing 'BONKS AND ZAPS'. Welcome again! @@@@@");
				break;
			default:
				System.out.println("not a valid choice");
			}
		} while (!choice.equals("Q"));
	}
	
	/**
	 * Changes settings for number of bonks, zaps, rows and columns
	 */
	public void settings() {
		System.out.print("How many rows and columns do you want to play with? ");
		rows = in.nextInt();
		cols = rows;
		System.out.println("The world is now changed to a (" + rows + "x" + cols + ") grid");
		
		System.out.print("How many Bonks do you wish to start with? ");
		numBonks = in.nextInt();
		System.out.println("The world now starts with " + numBonks + " Bonks");
		
		System.out.print("How many Zaps do you wish to start with? ");
		numZaps = in.nextInt();
		System.out.println("The world now starts with " + numZaps + " Zaps");
		
		System.out.print("How many cycles do you want the game to run over? ");
		cycles = in.nextInt();
		System.out.println("The world will now play through " + cycles + " Cycles");
		
	}
	
	/**
	 * Printing a menu to the console of what the user is allowed to do
	 */
	public void printMenu() {
		System.out.println("\nThe game consist of a (" + rows + "x" + cols + ") Grid "
						+ "with a start of \n" + numBonks + " Bonks, " + numZaps + " Zaps "
						+ "and " + cycles + " Cycels! \n"
						+ "BM = Bonk Male \n"
						+ "BF = Bonk Female \n"
						+ "bb(x,n) = Baby Bonk with x and n as parents \n"
						+ "Press: \n"
						+ "1 - To Play the game \n"
						+ "2 - To Change the game setting \n"
						+ "Q = To Quit");
	}
	
	/**
	 * Creating a copy of the Application that runs the game
	 * @param args
	 * @throws IOException throws exception if input is illegal
	 * @throws CannotActException call this class if being can't act
	 */
	public static void main(String[] args) throws IOException, CannotActException {
		Application app = new Application();
		app.runApp();
	}

}