package uk.ac.aber.iwl1.BonksAndZaps.beings;
import java.util.ArrayList;

import uk.ac.aber.iwl1.BonksAndZaps.mechanics.CannotActException;
import uk.ac.aber.iwl1.BonksAndZaps.mechanics.Position;
import uk.ac.aber.iwl1.BonksAndZaps.utilities.Utilities;

/**
 * Bonks class that extends the Mortal class and implements the Being interface
 * 
 * @author Isak Wisth Løvold
 * @version 1.0 (6th May 2016)
 *
 */

public class Bonks extends Mortal implements Being {
	
	private ArrayList<Bonks> bonks;
	
	private String sex;
	private boolean hasReproduced;
	private boolean isAdult;
	
	private Utilities util;
	
	/**
	 * Class constructor that initialise values and instance variables
	 * @param location position of the bonk
	 * @param lives lives of the bonk
	 * @param s sex of the bonk
	 * @param nm name of the bonk
	 * @param ia boolean that set's the bonk as adult or not
	 */
	public Bonks(Position location, int lives, String s, String nm, boolean ia){
		super(location, lives, nm);
		sex = s;
		isAdult = ia;
		
		hasReproduced = false;
		util = new Utilities();
	}

	/**
	 * Returns the name of the bonk
	 */
	@Override
	public String getName() {
		return super.getName();
	}

	/**
	 * Make the bonk act
	 * Calls the super.act() from the super class
	 * Calls the reproduce() that reproduces bonks
	 */
	@Override
	public void act() throws CannotActException {	
		super.act(); 
		reproduce();
	}

	/**
	 * Reproduce bonks if the bonk are both adults,
	 * they got the oposet sex, they are alive,
	 * they have not already produced bonks and
	 * if they got the same location
	 */
	public void reproduce(){
		Bonks b;
		Position loc;
		String gender;
		
		
		if(bonks != null){
			for(int i = 0; i < bonks.size(); i++){
				if(this.isAdult && bonks.get(i).isAdult && !this.hasReproduced && !bonks.get(i).hasReproduced 
						&& !this.getSex().equalsIgnoreCase(bonks.get(i).getSex()) 
						&& this.getLives() > 0 && bonks.get(i).getLives() > 0 
						&& this.getLocation().getPositionX() == bonks.get(i).getLocation().getPositionX() 
						&& this.getLocation().getPositionY() == bonks.get(i).getLocation().getPositionY()){
					
					int n = util.randomNum(2);
					if(n == 1){
						gender = "Boy";
					} else {
						gender = "Girl";
					}
					loc = this.getLocation();
					b = new Bonks(loc, 1, gender, "bb(" + this.getName() + "," + bonks.get(i).getName() + ")", false);
					bonks.add(b);					
					this.hasReproduced = true;
					bonks.get(i).hasReproduced = true;
				}
			}
		}
	}
	
	/**
	 * This is a getter for the position of the bonk
	 */
	@Override
	public Position getLocation() {
		return super.getLocation();
	}

	/**
	 * This is a setter for the position of the bonk
	 */
	@Override
	public void setLocation(Position location) {
		super.setLocation(location);
	}

	/**
	 * This is a getter for the bonks sex
	 * @return returns a string of the bonks sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * This is a getter for the ArrayList of bonks in this specific instance
	 * @return returns the ArrayList bonks for this specific bonk
	 */
	public ArrayList<Bonks> getBonks() {
		return bonks;
	}

	/**
	 * This is a getter for hasReproduced
	 * @return returns a boolean value if the bonk has reproduced or not
	 */
	public boolean isHasReproduced() {
		return hasReproduced;
	}

	/**
	 * This is a getter for the isAdult boolean
	 * @return returns a boolean value if the bonk is adult or not
	 */
	public boolean isAdult() {
		return isAdult;
	}

	/**
	 * This is a setter for the bonks ArrayList
	 * @param bonks sets the bonks ArrayList to the input of a bonks ArrayList
	 */
	public void setBonks(ArrayList<Bonks> bonks) {
		this.bonks = bonks;
	}

	/**
	 * This is a setter for hasReproduced
	 * @param hasReproduced sets hasReproduced to the boolean value it's given
	 */
	public void setHasReproduced(boolean hasReproduced) {
		this.hasReproduced = hasReproduced;
	}

	/**
	 * Prints out information about the instance variables
	 */
	@Override
	public String toString() {
		return "Bonks [bonks=" + bonks + ", sex=" + sex + ", hasReproduced="
				+ hasReproduced + ", isAdult=" + isAdult + "]";
	}

}
