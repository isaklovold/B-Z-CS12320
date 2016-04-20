
public class Zaps implements Being {

	private Position location;
	private String name;
	
	public Zaps(Position loc, String n){
		location = loc;
		name = n;
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
		this.location = location;
	}

	@Override
	public String toString() {
		return "Zaps [location=" + location + ", name=" + name + "]";
	}

}
