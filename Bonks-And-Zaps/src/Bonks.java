
public class Bonks extends Mortal implements Being {
	
	private String sex;
	private Movement movement;
	
	public Bonks(Position location, int lives, String s, String nm){
		super(location, lives, nm);
		sex = s;
		movement = new Movement();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public void act() throws CannotActException {
		this.setLocation(movement.move(this.getLocation(), 5)); // @@@@@@ CHANGE THE EDGE (NUMBER) @@@@@ 
		
	}

	@Override
	public Position getLocation() {
		// TODO Auto-generated method stub
		return super.getLocation();
	}

	@Override
	public void setLocation(Position location) {
		super.setLocation(location);
	}

	public String getSex() {
		return sex;
	}

	
	
	@Override
	public String toString() {
		return "Bonks [sex=" + sex + "]";
	}

}
