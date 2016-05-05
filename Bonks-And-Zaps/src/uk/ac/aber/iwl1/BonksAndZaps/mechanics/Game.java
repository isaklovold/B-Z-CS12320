package uk.ac.aber.iwl1.BonksAndZaps.mechanics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import uk.ac.aber.iwl1.BonksAndZaps.beings.Being;
import uk.ac.aber.iwl1.BonksAndZaps.beings.Bonks;
import uk.ac.aber.iwl1.BonksAndZaps.beings.Mortal;
import uk.ac.aber.iwl1.BonksAndZaps.beings.Zaps;
import uk.ac.aber.iwl1.BonksAndZaps.utilities.Utilities;

/**
 * Game class that runs the actual game
 * 
 * @author Isak Wisth Løvold
 * @version 1.0 (6th May 2016)
 *
 */

public class Game {

	private int rows;
	private int cols;
	private int numBonks, numZaps;
	private ArrayList<Being>[][] world;
	private ArrayList<Bonks> bonks;
	private ArrayList<Zaps> zaps;
	private ArrayList<Mortal> mortals;
	private Utilities util;
	
	private int bonksAlive;
	private int babyBonksAlive;
	private int matureBonks;
	private int babyBonks;
	
	
	private int cycles;
	private final int MAX_CYCLES;
	private int numBeings;
	
	private Random rand;
	
	/**
	 * Class constructor that sets the MAX_CYCLES to 0 
	 * before the game info is passed
	 */
	public Game(){
		MAX_CYCLES = 0;
	}
	
	/**
	 * Constructor for the class that initialise 
	 * the instance variables and starts the game
	 * @param rows number of rows in the grid
	 * @param cols number of columns in the grid
	 * @param numBonks number of bonks to start of with
	 * @param numZaps number of zaps to start of with
	 * @param MAX_C how many cycles the game is ran
	 * @throws CannotActException call this class if being can't act
	 */
	public Game(int rows, int cols, int numBonks, int numZaps, int MAX_C) throws CannotActException{
		this.rows = rows - 1;
		this.cols = cols - 1;
		this.numBonks = numBonks;
		this.numZaps = numZaps;
		
		world = new ArrayList[rows][cols];
		bonks = new ArrayList<Bonks>();
		zaps = new ArrayList<Zaps>();
		mortals = new ArrayList<Mortal>();
		
		numBeings = numBonks + numZaps;
		cycles = 0;
		MAX_CYCLES = MAX_C - 1;	
		
		util = new Utilities();

		createBeings();
		startGame();
	}

	/**
	 * GAME LOOP
	 * This is where the game starts and gets played
	 * over MAX_CYCLES cycles
	 * @throws CannotActException call this class if being can't act
	 */
	public void startGame() throws CannotActException{
		do {
			try {
				addMortals();
				actZaps();
				actMortals();
				actBonks();
				
				printGame();
				cycles++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while(cycles <= MAX_CYCLES);
		
		for(int i = 0; i < bonks.size(); i++){
			if(bonks.get(i).isAdult()){
				matureBonks += 1;
				if(bonks.get(i).getLives() > 0 && bonks.get(i).isAdult()){
					bonksAlive += 1;
				} 
			} else {
				babyBonks += 1;
				if(bonks.get(i).getLives() > 0 && !bonks.get(i).isAdult()){
					babyBonksAlive += 1;
				}
			}
		}
		
		System.out.println("\nGame-world is over, stats:");
		System.out.println(bonksAlive + "/" + matureBonks + " Mature Bonks is still alive");
		System.out.println(babyBonksAlive + "/" + babyBonks + " Baby Bonks is still alive");
		System.out.println("Zaps have killed " + ((matureBonks - bonksAlive) + (babyBonks - babyBonksAlive)) 
				+ "/" + (matureBonks + babyBonks) + " in total!");
	}
	
	/**
	 * Add bonks and to the mortals ArrayList
	 * Can add more mortals if needed later on
	 */
	public void addMortals(){
		for(int i = 0; i < bonks.size(); i++){
			mortals.add(bonks.get(i));
		}
	}
	
	/**
	 * Calls the act() in zaps class with passing
	 * and returning ArrayLists of mortals
	 * @throws CannotActException call this class if being can't act
	 */
	public void actZaps() throws CannotActException{
		for(int i = 0; i < zaps.size(); i++){
			zaps.get(i).setSquare(rows);
			zaps.get(i).setMortals(mortals);
			zaps.get(i).act();
			mortals = zaps.get(i).getMortals();
		}
	}
	
	/**
	 * Calls the act() in bonks class with passing
	 * and returning ArrayLists of bonks/baby bonks
	 * Sets all bonks to be able to reproduce before each cycle
	 * @throws CannotActException call this class if being can't act
	 */
	public void actBonks() throws CannotActException{
		for(int i = 0; i < bonks.size(); i++){
			bonks.get(i).setHasReproduced(false);
		}
		for(int i = 0; i < bonks.size(); i++){
			bonks.get(i).setBonks(bonks);
			bonks.get(i).act();
			bonks = bonks.get(i).getBonks();
		}
	}
	
	/**
	 * Let the mortals class know how big the grid world is
	 * Passes the size of the grid to every mortals(bonks) when moving in act()
	 * Uncomment the line inside if needed to use the act() in mortals class
	 * @throws CannotActException call this class if being can't act
	 */
	public void actMortals() throws CannotActException{
		for(int i = 0; i < mortals.size(); i++){	
			mortals.get(i).setSquare(rows);
//			mortals.get(i).act();
		}
	}
	
	/**
	 * Creating bonks and zaps before the game starts and
	 * add them to a separate ArrayList
	 */
	public void createBeings() {
		int x;
		int y;
		int s;
		Position location;
		String name = "";
		String sex = "";
		
		for(int i = 0; i < numBonks; i++){
			x = util.randomNum(rows);
			y = util.randomNum(cols);
			location = new Position(x,y);
			s = util.randomNum(2);
			if(s == 0){
				sex = "Male";
				name = "BM" + i;
			}else {
				sex = "Female";
				name = "BF" + i;
			}
			Bonks b = new Bonks(location, 1, sex, name, true);
			bonks.add(b);
		}
		for(int i = 0; i < numZaps; i++){
			x = util.randomNum(rows);
			y = util.randomNum(cols);
			location = new Position(x,y);
			name = "Z" + i;
			Zaps z = new Zaps(location, name);
			zaps.add(z);
		}

	}
		
	/**
	 * Prints out the game in the console where
	 * you can see all the bonks, zaps, if thei're alive 
	 * and the position they got on the grid
	 */
	public void printGame() {
		ArrayList<Being> countBeings = new ArrayList<Being>();
				
		System.out.println("\nDay. " + (cycles + 1));
		
		for(int i = 0; i <= rows; i++){
			for(int j = 0; j <= cols; j++){
				for(int k = 0; k < bonks.size(); k++){
					if(bonks.get(k).getLocation().getPositionX() == i && bonks.get(k).getLocation().getPositionY() == j){
						countBeings.add(bonks.get(k));
					} 
				}
				for(int h = 0; h < zaps.size(); h++){
					if(zaps.get(h).getLocation().getPositionX() == i && zaps.get(h).getLocation().getPositionY() == j){
						countBeings.add(zaps.get(h));
					}
				}
				
				/*
				 *  Uncomment the section below if you want to check for all mortals
				 * 
				 * for(int m = 0; m < mortals.size(); m++){
				 *	if(mortals.get(m).getLocation().getPositionX() == i && mortals.get(m).getLocation().getPositionY() == j){
				 *		countBeings.add(mortals.get(m));
				 *	}
				 *}
				 */
				
				/*
				 * Uncomment this section and comment out the section 
				 * 
				for(int b = 0; i < bonks.size(); i++){
					System.out.println("(" + bonks.get(i).getLocation().getPositionX() + "," + bonks.get(i).getLocation().getPositionY() + ") " + bonks.get(i).getName());
				}
				for(int z = 0; i < zaps.size(); i++){
					System.out.println("(" + zaps.get(i).getLocation().getPositionX() + "," + zaps.get(i).getLocation().getPositionY() + ") " + zaps.get(i).getName());
				}*/
				
				
				System.out.print("[" + (i + 1) + ", " + (j + 1) + "] = " + "[");
				for(int b = 0; b < countBeings.size(); b++){
					System.out.print(countBeings.get(b).getName());
					countBeings.remove(b);
				}
				System.out.println("]");

			}

			
		}
	}
	
	/**
	 * This is a getter for number of bonks in the game
	 * @return returns number of bonks in the game
	 */
	public int getNumBonks() {
		return numBonks;
	}

	/**
	 * This is a getter for number of zaps in the game
	 * @return returns number of zaps in the game
	 */
	public int getNumZaps() {
		return numZaps;
	}

	/**
	 * This is a getter for the cycle the game is on
	 * @return returns which number of cycle the game is on
	 */
	public int getCycles() {
		return cycles;
	}

	/**
	 * This is a getter for how many cycles one game is
	 * @return returns number of cycles in one game
	 */
	public int getMAX_CYCLES() {
		return MAX_CYCLES;
	}

	/**
	 * This is a setter for number of bonks
	 * @param numBonks sets number of bonks to the input
	 */
	public void setNumBonks(int numBonks) {
		this.numBonks = numBonks;
	}

	/**
	 * This is a setter for number of zaps
	 * @param numZaps sets number of zaps to the input
	 */
	public void setNumZaps(int numZaps) {
		this.numZaps = numZaps;
	}

	/**
	 * This is a setter for number of cycles
	 * @param cycles sets number of cycles to the input
	 */
	public void setCycles(int cycles) {
		this.cycles = cycles;
	}

	/**
	 * This is a getter for number of rows
	 * @return returns number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * This is a getter for number of columns
	 * @return returns number of columns
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * This is a getter for the grid world that is a 2D ArrayList of beings
	 * @return returns a 2d ArrayList of beings
	 */
	public ArrayList<Being>[][] getWorld() {
		return world;
	}

	/**
	 * This is a getter for the ArrayList of bonks
	 * @return returns an ArrayList of bonks
	 */
	public ArrayList<Bonks> getBonks() {
		return bonks;
	}

	/**
	 * This is a getter for the ArrayList of zaps
	 * @return returns an ArrayList of Zaps
	 */
	public ArrayList<Zaps> getZaps() {
		return zaps;
	}

	/**
	 * This is a setter for number of rows
	 * @param rows sets number of rows in the grid world to the input
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * This is a setter for number of columns
	 * @param cols sets number of columns in the grid world to the input
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * This is a setter for the 2d ArrayList of beings that is the grid world
	 * @param world sets the grid world to the 2d ArrayList input
	 */
	public void setWorld(ArrayList<Being>[][] world) {
		this.world = world;
	}

	/**
	 * This is a setter for the bonks ArrayList
	 * @param bonks sets the bonks ArrayList to the input
	 */
	public void setBonks(ArrayList<Bonks> bonks) {
		this.bonks = bonks;
	}

	/**
	 * This is a setter for the zaps ArrayList
	 * @param zaps sets the zapz ArrayList to the input
	 */
	public void setZaps(ArrayList<Zaps> zaps) {
		this.zaps = zaps;
	}

	/**
	 * Prints out information about the instance variables
	 */
	@Override
	public String toString() {
		return "Game [rows=" + rows + ", cols=" + cols + ", numBonks="
				+ numBonks + ", numZaps=" + numZaps + ", world="
				+ Arrays.toString(world) + ", bonks=" + bonks + ", zaps="
				+ zaps + ", mortals=" + mortals + ", bonksAlive=" + bonksAlive
				+ ", babyBonksAlive=" + babyBonksAlive + ", matureBonks="
				+ matureBonks + ", babyBonks=" + babyBonks + ", cycles="
				+ cycles + ", MAX_CYCLES=" + MAX_CYCLES + ", numBeings="
				+ numBeings + ", rand=" + rand + "]";
	}
		
}
