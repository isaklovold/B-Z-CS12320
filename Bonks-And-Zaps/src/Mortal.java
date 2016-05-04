
public class Mortal implements Being{

	private Position location;
	private int lives;
	private String name;
	private Utilities util;
	
	public Mortal(Position loc, int liv, String nm){
		location = loc;
		lives = liv;
		name = nm;
		
		util = new Utilities();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void act() throws CannotActException {
		if(this.getLives() > 0){
			this.setLocation(util.movement(this.getLocation(), 20)); // @@@@@@ CHANGE THE EDGE (NUMBER) @@@@@
		}
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

	public void setName(String name) {
		this.name = name;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	@Override
	public String toString() {
		return "Mortal [location=" + location + ", lives=" + lives + "]";
	}
	
}
