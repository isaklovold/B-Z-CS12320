
public class Bonks extends Mortal implements Being {
	
	private String sex;
	
	public Bonks(Position location, int lives, String s, String nm){
		super(location, lives, nm);
		sex = s;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public void act() throws CannotActException {
		super.act(); 
		
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
