package uk.ac.aber.iwl1.BonksAndZaps.utilities;
import java.util.Random;

import uk.ac.aber.iwl1.BonksAndZaps.mechanics.Position;

/**
 * Utilities class where logic such as random and movement is gathered
 * 
 * @author Isak Wisth Løvold
 * @version 1.0 (6th May 2016)
 *
 */

public class Utilities {

	private Random rand;
	private Position location;
	
	/**
	 * Class constructor
	 */
	public Utilities(){
		
	}
	
	/**
	 * Movement method that takes in two parameters and returns a new position
	 * @param loc position that holds two integer values
	 * @param edge the edge of the grid world
	 * @return returns a new position
	 */
	public Position movement(Position loc, int edge){
		int x = loc.getPositionX();  
		int y = loc.getPositionY();
		int n = randomNum(4);
		
		if(loc.getPositionX() <= 0 || loc.getPositionY() <= 0){
			if(n == 0 || n == 1){
				x += 1;
			} else {
				y += 1;
			}
		} else if(loc.getPositionX() >= edge || loc.getPositionY() >= edge){
			if(n == 0 || n == 1){
				x -= 1;
			} else {
				y -= 1;
			}
		} else {
			if(n == 0){
				x += 1;
			} else if(n == 1){
				x -= 1;
			} else if(n == 2){
				y += 1;
			} else {
				y -= 1;
			}
		}

		location = new Position(x, y);
	
		return location;
	}
	
	/**
	 * Random number method
	 * @param num takes in a integer value
	 * @return returns a random integer value
	 */
	public int randomNum(int num){
		rand = new Random();
		int r = rand.nextInt(num);
		return r;
	}
	
}
