package uk.ac.aber.iwl1.BonksAndZaps.beings;
import uk.ac.aber.iwl1.BonksAndZaps.mechanics.CannotActException;
import uk.ac.aber.iwl1.BonksAndZaps.mechanics.Position;
import uk.ac.aber.iwl1.BonksAndZaps.utilities.Utilities;


/**
 * Mortal class that is the super class of all mortals
 * 
 * @author Isak Wisth Løvold
 * @version 1.0 (6th May 2016)
 *
 */

public class Mortal implements Being{

	private Position location;
	private int lives;
	private String name;
	private Utilities util;
	
	private int square;
	
	/**
	 * Class constructor that sets the instance variables
	 * @param loc position of the mortal
	 * @param liv lives of the mortal
	 * @param nm name of the mortal
	 */
	public Mortal(Position loc, int liv, String nm){
		location = loc;
		lives = liv;
		name = nm;
		
		util = new Utilities();
	}

	/**
	 * This is a getter for the name 
	 * Return the name of the Mortal
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	/**
	 * This is the act() where the mortal acts each cycle
	 * as long as it's alive
	 * Calls CannotActException if the mortal can't act
	 */
	@Override
	public void act() throws CannotActException {
		if(this.getLives() > 0){
			this.setLocation(util.movement(this.getLocation(), square)); // @@@@@@ CHANGE THE EDGE (NUMBER) @@@@@
		}
	}

	/**
	 * This is a getter for the position
	 * Returns position of the mortal
	 */
	@Override
	public Position getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	/**
	 * This is a setter for the position
	 * Sets the position of the mortal to the input
	 */
	@Override
	public void setLocation(Position location) {
		// TODO Auto-generated method stub
		this.location = location;
	}

	/**
	 * This is a getter for number of lives
	 * @return returns number of lives of the mortal
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * This is a setter for the name
	 * @param name sets the name of the mortal to the input string
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This is a setter for the lives of the mortal
	 * @param lives sets the number of lives of the mortal to the input
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * This is a setter for the square integer (width and height of the game)
	 * @param square sets the square integer to the input
	 */
	public void setSquare(int square) {
		this.square = square;
	}

	/**
	 * Prints out the information about the instance variables
	 */
	@Override
	public String toString() {
		return "Mortal [location=" + location + ", lives=" + lives + ", name="
				+ name + ", util=" + util + ", square=" + square + "]";
	}
	
}
