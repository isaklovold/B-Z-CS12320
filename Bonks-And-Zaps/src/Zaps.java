import java.util.ArrayList;


public class Zaps implements Being {

	private static int damage = 1;
	
	private Position location;
	private String name;
	private Movement movement;
	private ArrayList<Mortal> mortals;
	
	
	public Zaps(){
		
	}
	
	public Zaps(Position loc, String n){
		location = loc;
		name = n;
		movement = new Movement();
		mortals = new ArrayList<Mortal>();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@Override
	public void act() throws CannotActException {
		this.setLocation(movement.move(this.getLocation(), 5)); // @@@@@@ CHANGE THE EDGE (NUMBER) @@@@@
		zap();
	}

	public void zap(){
		if(mortals != null){
		for(int i = 0; i < mortals.size(); i++){
			if(this.getLocation().getPositionX() == mortals.get(i).getLocation().getPositionX()
					&& this.getLocation().getPositionY() == mortals.get(i).getLocation().getPositionY()){
				mortals.get(i).setLives(mortals.get(i).getLives() - damage);
				if(mortals.get(i).getLives() <= 0){
					mortals.get(i).setName(mortals.get(i).getName() + "-D");
				} 
			}
		}
		}
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

	public Movement getMovement() {
		return movement;
	}

	public ArrayList<Mortal> getMortals() {
		return mortals;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	public void setMortals(ArrayList<Mortal> mortals) {
		this.mortals = mortals;
	}
	

	public static int getDamage() {
		return damage;
	}

	public static void setDamage(int damage) {
		Zaps.damage = damage;
	}

	@Override
	public String toString() {
		return "Zaps [location=" + location + ", name=" + name + "]";
	}

}
