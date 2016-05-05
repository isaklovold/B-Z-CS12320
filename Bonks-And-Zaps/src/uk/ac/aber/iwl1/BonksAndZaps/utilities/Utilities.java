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
		int n;	
			
		for(;;){
			n = randomNum(8);
			if(n == 0){ // MOVE RIGHT
				if(x < edge){
					x += 1;
					break;
				} 
			} else if(n == 3){ // MOVE LEFT
				if(x > 0){
					x -= 1;
					break;
				} 
			} else if(n == 2){ // MOVE UP
				if(y > 0){
					y -= 1;
					break;
				} 
			} else if(n == 4){ // MOVE DOWN
				if(y < edge){
					y += 1;
					break;
				} 
			} else if(n == 1){ // MOVE UP RIGHT
				if(x < edge && y > 0){
					x += 1;
					y -= 1;
					break;
				}
			} else if(n == 5){ // MOVE UP LEFT
				if(x > 0 && y > 0){
					x -= 1;
					y -= 1;
					break;
				} 
			} else if(n == 6){ // MOVE DOWN RIGHT
				if(x < edge && y < edge){
					x += 1;
					y += 1;
					break;
				} 
			} else { // MOVE DOWN LEFT
				if(x > 0 && y < edge){
					x -= 1;
					y += 1;
					break;
				} 
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
