
public class Zaps implements Being {

	private Position location;
	private String name;
	private Movement movement;
	
	public Zaps(Position loc, String n){
		location = loc;
		name = n;
		movement = new Movement();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@Override
	public void act() throws CannotActException {
		this.setLocation(movement.move(this.getLocation(), 5)); // @@@@@@ CHANGE THE EDGE (NUMBER) @@@@@ 
	}

	@Override
	public Position getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	@Override
	public void setLocation(Position location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Zaps [location=" + location + ", name=" + name + "]";
	}

}
