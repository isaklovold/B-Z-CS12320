import java.util.ArrayList;


public class Bonks extends Mortal implements Being {
	
	private ArrayList<Bonks> bonks;
	
	private String sex;
	private boolean hasReproduced;
	private boolean isAdult;
	
	private Utilities util;
	
	public Bonks(Position location, int lives, String s, String nm, boolean ia){
		super(location, lives, nm);
		sex = s;
		isAdult = ia;
		
		hasReproduced = false;
		util = new Utilities();
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void act() throws CannotActException {	
		super.act(); 
		reproduce();
	}

	public void reproduce(){
		Bonks b;
		Position loc;
		String gender;
		
		
		if(bonks != null){
			for(int i = 0; i < bonks.size(); i++){
				if(this.isAdult && bonks.get(i).isAdult && !this.hasReproduced && !bonks.get(i).hasReproduced 
						&& !this.getSex().equalsIgnoreCase(bonks.get(i).getSex()) 
						&& this.getLives() > 0 && bonks.get(i).getLives() > 0 
						&& this.getLocation().getPositionX() == bonks.get(i).getLocation().getPositionX() 
						&& this.getLocation().getPositionY() == bonks.get(i).getLocation().getPositionY()){
					
					int n = util.randomNum(2);
					if(n == 1){
						gender = "Boy";
					} else {
						gender = "Girl";
					}
					loc = this.getLocation();
					b = new Bonks(loc, 1, gender, "bb(" + this.getName() + "," + bonks.get(i).getName() + ")", false);
					bonks.add(b);					
					this.hasReproduced = true;
					bonks.get(i).hasReproduced = true;
				}
			}
		}
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

	public ArrayList<Bonks> getBonks() {
		return bonks;
	}

	public boolean isHasReproduced() {
		return hasReproduced;
	}

	public boolean isAdult() {
		return isAdult;
	}

	public void setBonks(ArrayList<Bonks> bonks) {
		this.bonks = bonks;
	}

	public void setHasReproduced(boolean hasReproduced) {
		this.hasReproduced = hasReproduced;
	}

	@Override
	public String toString() {
		return "Bonks [bonks=" + bonks + ", sex=" + sex + ", hasReproduced="
				+ hasReproduced + ", isAdult=" + isAdult + ", util=" + util
				+ "]";
	}

}
