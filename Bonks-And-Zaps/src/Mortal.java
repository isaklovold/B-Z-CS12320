
public class Mortal implements Being{

	private Position location;
	private int lives;
	private String name;
	
	public Mortal(Position loc, int liv, String nm){
		location = loc;
		lives = liv;
		name = nm;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void act() throws CannotActException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	@Override
	public void setLocation(Position location) {
		// TODO Auto-generated method stub
		this.location = location;
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
