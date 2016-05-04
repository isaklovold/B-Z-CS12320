package uk.ac.aber.iwl1.BonksAndZaps.beings;
import java.util.ArrayList;

import uk.ac.aber.iwl1.BonksAndZaps.mechanics.CannotActException;
import uk.ac.aber.iwl1.BonksAndZaps.mechanics.Position;
import uk.ac.aber.iwl1.BonksAndZaps.utilities.Utilities;

/**
 * Zaps class that implements the Being interface 
 * 
 * @author Isak Wisth Løvold
 * @version 1.0 (6th May 2016)
 *
 */

public class Zaps implements Being {

	private static int damage = 1;
	
	private Position location;
	private String name;
	private Utilities util;
	private ArrayList<Mortal> mortals;
	
	private int square;
	
	/**
	 * Class constructor
	 */
	public Zaps(){
		
	}
	
	/**
	 * Class constructor that initialise the instance variables
	 * @param loc position of the zap
	 * @param n name of the zap
	 */
	public Zaps(Position loc, String n){
		location = loc;
		name = n;
		mortals = new ArrayList<Mortal>();
		util = new Utilities();
	}
	
	/**
	 * This is a getter for the name
	 * Returns a string of the name to the zap
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * This is the act() where the zap acts each cycle
	 * Calls the zap() which zap other mortals
	 */
	@Override
	public void act() throws CannotActException {
		this.setLocation(util.movement(this.getLocation(), square)); // @@@@@@ CHANGE THE EDGE (NUMBER) @@@@@
		zap();
	}

	/**
	 * Kills/damage other mortals if it's in the same position as them 
	 */
	public void zap(){
		if(mortals != null){
		for(int i = 0; i < mortals.size(); i++){
			if(mortals.get(i).getLives() > 0){
				if(this.getLocation().getPositionX() == mortals.get(i).getLocation().getPositionX()
						&& this.getLocation().getPositionY() == mortals.get(i).getLocation().getPositionY()){
					mortals.get(i).setLives(mortals.get(i).getLives() - damage);
					if(mortals.get(i).getLives() <= 0){
						mortals.get(i).setName(mortals.get(i).getName() + "-D");
					} 
				}
			}
		}
		}
	}

	/**
	 * This is a getter for the position of the zap
	 * Returns the position of the zap
	 */
	@Override
	public Position getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	/**
	 * This is a setter for the position of the zap
	 * Sets the position of the zap to the input position
	 */
	@Override
	public void setLocation(Position location) {
		this.location = location;
	}

	/**
	 * This is a getter for the mortals ArrayList
	 * @return returns the mortals ArrayLisst
	 */
	public ArrayList<Mortal> getMortals() {
		return mortals;
	}

	/**
	 * This is a setter for the zaps name
	 * @param name sets the name of the zap to it's input
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This is a setter for the mortals ArrayList
	 * @param mortals sets the mortals ArrayList to the input
	 */
	public void setMortals(ArrayList<Mortal> mortals) {
		this.mortals = mortals;
	}
	
	/**
	 * This is a getter for the damage
	 * @return returns the damage the zap takes
	 */
	public static int getDamage() {
		return damage;
	}

	/**
	 * This is a setter for the damage
	 * @param damage sets the damage of the zap to the input
	 */
	public static void setDamage(int damage) {
		Zaps.damage = damage;
	}

	/**
	 * This is a setter for the square (height and width of the grid world)
	 * @param square sets the square (height and width of the grid world) to the input
	 */
	public void setSquare(int square) {
		this.square = square;
	}

	/**
	 * Prints out information about the instance variables
	 */
	@Override
	public String toString() {
		return "Zaps [location=" + location + ", name=" + name + ", util="
				+ util + ", mortals=" + mortals + ", square=" + square + "]";
	}
	
}
