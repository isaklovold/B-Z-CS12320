import java.util.Random;


public class Utilities {

	Random rand;
	Position location;
	
	public Utilities(){
		
	}
	
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
		
//		if(n == 0 || n == 1){
//			if(loc.getPositionX() < edge && loc.getPositionY() < edge){
//				if(n == 0){
//					x += 1;
//				} else{
//					y += 1;
//				} 
//			} 
//		} else {
//			if(loc.getPositionX() > 0 && loc.getPositionY() > 0){
//				if(n == 2){
//					x -= 1;
//				} else{
//					y -= 1;
//				}
//			} 
//		}
		location = new Position(x, y);
	
		return location;
	}
	
	public int randomNum(int num){
		rand = new Random();
		int r = rand.nextInt(num);
		return r;
	}
	
}
