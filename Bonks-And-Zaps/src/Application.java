import java.io.IOException;
import java.util.Scanner;


public class Application {
	
	private Scanner in;
	private int rows, cols;
	private int numBonks, numZaps;
	private Game game;
	
	public Application(){
		in = new Scanner(System.in);
		game = new Game(rows, cols, numBonks, numZaps);
	}
	
	public void runApp() throws IOException {
		String choice;
		System.out.println("*** HELLO - WELCOME BONKS AND ZAPS ");
		do {
			printMenu();
			choice = in.next().toUpperCase();
			switch (choice) {
			case "1":
				System.out.println("Game is running");
				
				break;
				
			case "2":
				System.out.println("Change settings:");
				
				break;
			case "Q":
				System.out.println("Hade");
				break;
			default:
				System.out.println("not a valid choice");
			}
		} while (!choice.equals("Q"));
	}
	
	public void printMenu() {
		System.out.println("The game consist of a 20x20 grid \n"
						+ "1 - Play the game \n"
						+ "2 - Change the setting \n"
						+ "q = quit");
	}
	
	public static void main(String[] args) throws IOException {
		Application app = new Application();
		app.runApp();
	}

}