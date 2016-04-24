import java.util.ArrayList;


public class Zaps implements Being {

	private Position location;
	private String name;
	private Movement movement;
	private ArrayList<Being> beings;
	
	public Zaps(){
		
	}
	
	public Zaps(Position loc, String n){
		location = loc;
		name = n;
		movement = new Movement();
		beings = new ArrayList<Being>();
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
	
	public void passBeings(Being b){
		beings.add(b);
	}
	
	public void zap(){
		for(int i = 0; i < beings.size(); i++){
			if(this.getLocation().equals(beings.get(i).getLocation())){
				
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

	@Override
	public String toString() {
		return "Zaps [location=" + location + ", name=" + name + "]";
	}

}
