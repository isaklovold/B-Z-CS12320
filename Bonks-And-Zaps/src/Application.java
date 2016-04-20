import java.io.IOException;
import java.util.Scanner;


public class Application {
	
	private Scanner in;

	public Application(){
		in = new Scanner(System.in);
	}
	
	public void runApp() throws IOException {
		String choice;
		System.out.println("*** HELLO - WELCOME TO MONSTER LAND ");
		do {
			printMenu();
			choice = in.next().toUpperCase();
			switch (choice) {
			case "1":
				System.out.println("Hei");
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
		System.out.println("lots of menu lines \n"
						+ "1 - To say hi in Norwegian \n"
						+ "q = quit");
	}
	
	public static void main(String[] args) throws IOException {
		Application app = new Application();
		app.runApp();
	}

}