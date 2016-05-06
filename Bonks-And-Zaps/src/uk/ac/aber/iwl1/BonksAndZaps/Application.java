package uk.ac.aber.iwl1.BonksAndZaps;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uk.ac.aber.iwl1.BonksAndZaps.mechanics.CannotActException;
import uk.ac.aber.iwl1.BonksAndZaps.mechanics.Game;

/**
 * Main class that runs the menu and game for Bonks And Zaps
 * 
 * @author Isak Wisth Løvold
 * @version 1.0 (6th May 2016)
 *
 */

public class Application extends javafx.application.Application {
	
	private Scanner in;
	private int rows, cols;
	private int numBonks, numZaps;
	private int cycles;
	private Game game;
	
	// JAVAFX
	private Label[] gameInfoLabels = new Label[4];
	private Label[] resultInfo = new Label[4];
	
	/**
	 * Constructor of the Application Class
	 * Initialising all the instance variables
	 */
	public Application(){
		in = new Scanner(System.in);
		
		rows = 20;
		cols = 20;
		numBonks = 20;
		numZaps = 5;
		cycles = 20;
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {				
		printMenu();
		stage.setTitle("Bonks And Zaps");

		Label header = new Label("Bonks And Zaps");
		header.setFont(new Font(100));
		header.setId("header");
		header.setPadding(new Insets(0,0,0,200));
		
		BorderPane borderPane = new BorderPane();
		
		VBox buttons = new VBox();
		Button settingsBtn = new Button("Settings");
		settingsBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				settings();
				updateGUI();
			}
		});
		settingsBtn.setPrefSize(120, 50);
		
		Button startBtn = new Button("Start");
		startBtn.setPrefSize(120, 50);
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					game = new Game(rows, cols, numBonks, numZaps, cycles);
					updateResults();
				} catch (CannotActException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		final Button pauseBtn = new Button("Pause");
		pauseBtn.setPrefSize(120, 50);
		pauseBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(pauseBtn.getText().equalsIgnoreCase("Pause")){
					pauseBtn.setText("Play");
				} else {
					pauseBtn.setText("Pause");
				}
			}
		});
		
		Button restartBtn = new Button("Restart");
		restartBtn.setPrefSize(120, 50);
		restartBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				gameInfoLabels[0].setText(String.valueOf(20));
				gameInfoLabels[1].setText(String.valueOf(20));
				gameInfoLabels[2].setText(String.valueOf(5));
				gameInfoLabels[3].setText(String.valueOf(20));
			}
		});
		
		Button stopBtn = new Button("Stop");
		stopBtn.setPrefSize(120, 50);
		
		buttons.setId("buttons");
		buttons.setSpacing(20);
		buttons.setPadding(new Insets(100, 20, 20, 20)); 

		Button quitBtn = new Button("Quit");
		quitBtn.setPrefSize(120, 50);
		quitBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.exit(1);
			}
		});
		
		VBox gameLabels = new VBox();
		Label grid = new Label("Grid size: ");
		Label bonks = new Label("Number of bonks: ");
		Label zaps = new Label("Number of zaps: ");
		Label cycles = new Label("Cycles: ");
		
		gameLabels.setId("gameLabels");
		gameLabels.setSpacing(50);
		gameLabels.setPadding(new Insets(150, 20, 20, 20)); 
		
		
		VBox gameInfo = new VBox();
		Border gameInfo_border = new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));
		
		Label show_grid = new Label(String.valueOf(rows) + "x" + String.valueOf(rows));
		show_grid.setBorder(gameInfo_border);
		show_grid.setPrefSize(130, 50);
		show_grid.setAlignment(Pos.CENTER);
		gameInfoLabels[0] = show_grid;
		
		Label show_bonks = new Label(String.valueOf(numBonks));
		show_bonks.setBorder(gameInfo_border);
		show_bonks.setPrefSize(130, 50);
		show_bonks.setAlignment(Pos.CENTER);
		gameInfoLabels[1] = show_bonks;
		
		Label show_zaps = new Label(String.valueOf(numZaps));
		show_zaps.setBorder(gameInfo_border);
		show_zaps.setPrefSize(130, 50);
		show_zaps.setAlignment(Pos.CENTER);
		gameInfoLabels[2] = show_zaps;
		
		Label show_cycles = new Label(String.valueOf(this.cycles));
		show_cycles.setBorder(gameInfo_border);
		show_cycles.setPrefSize(130, 50);
		show_cycles.setAlignment(Pos.CENTER);
		gameInfoLabels[3] = show_cycles;
		
		
		gameInfo.setId("gameInfo");
		gameInfo.setSpacing(20);
		gameInfo.setPadding(new Insets(135, 500, 0, 0));
		
		VBox results = new VBox();
		results.setId("resultLabels");
		Label result_label = new Label("Results: ");
		resultInfo[0] = result_label;
		result_label.setId("result");
		Label result_bonks = new Label("Bonks alive: ");
		resultInfo[1] = result_bonks;
		Label result_babybonks = new Label("Baby Bonks alive: ");
		resultInfo[2] = result_babybonks;
		Label result_zapskilled = new Label("Zaps has killed: ");
		resultInfo[3] = result_zapskilled;
		
		results.setPadding(new Insets(0,0,50,450));
		
		borderPane.setTop(header);
		borderPane.setLeft(buttons);
		borderPane.setCenter(gameLabels);
		borderPane.setRight(gameInfo);
		borderPane.setBottom(results);

		buttons.getChildren().add(settingsBtn);
		buttons.getChildren().add(startBtn);
		buttons.getChildren().add(pauseBtn);
		buttons.getChildren().add(restartBtn);
		buttons.getChildren().add(stopBtn);
		buttons.getChildren().add(quitBtn);
		
		gameLabels.getChildren().add(grid);
		gameLabels.getChildren().add(bonks);
		gameLabels.getChildren().add(zaps);
		gameLabels.getChildren().add(cycles);
		
		gameInfo.getChildren().add(show_grid);
		gameInfo.getChildren().add(show_bonks);
		gameInfo.getChildren().add(show_zaps);
		gameInfo.getChildren().add(show_cycles);
		
		results.getChildren().add(result_label);
		results.getChildren().add(result_bonks);
		results.getChildren().add(result_babybonks);
		results.getChildren().add(result_zapskilled);
		
		
		Scene scene = new Scene(borderPane, 1080, 900);
		scene.getStylesheets().add(Application.class.getResource("styling.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void updateGUI(){
		gameInfoLabels[0].setText(String.valueOf(rows));
		gameInfoLabels[1].setText(String.valueOf(numBonks));
		gameInfoLabels[2].setText(String.valueOf(numZaps));
		gameInfoLabels[3].setText(String.valueOf(this.cycles));
	}
	
	public void updateResults(){
		resultInfo[1].setText(resultInfo[1].getText() + String.valueOf(game.bonksAlive) + "/" + String.valueOf(game.matureBonks));
		resultInfo[2].setText(resultInfo[2].getText() + String.valueOf(game.babyBonksAlive) + "/" + String.valueOf(game.babyBonks));
		resultInfo[3].setText(resultInfo[3].getText() + String.valueOf((game.matureBonks - game.bonksAlive) 
				+ (game.babyBonks - game.babyBonksAlive)) + "/" + String.valueOf(game.matureBonks + game.babyBonks));
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
		for(String s: args){
			if(s.equals("-gui")){
				launch(args);
			} else {
				System.err.println("ERROR LOADING GUI!");
				System.exit(1);
			}
		}
		
		Application app = new Application();
		app.runApp();
		
	}

	

}