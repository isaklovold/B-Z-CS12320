
public class Bonks extends Mortal implements Being {
	
	private String sex;
	
	public Bonks(Position location, int lives, String s){
		super(location, lives);
		sex = s;
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
