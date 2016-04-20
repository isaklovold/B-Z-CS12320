
public class Mortal implements Being{

	private Position location;
	private int lives;
	
	public Mortal(Position loc, int liv){
		location = loc;
		lives = liv;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void act() throws CannotActException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(Position location) {
		// TODO Auto-generated method stub
		
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	@Override
	public String toString() {
		return "Mortal [location=" + location + ", lives=" + lives + "]";
	}
	
}
