import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Game {

	private int rows;
	private int cols;
	private int numBonks, numZaps;
	private ArrayList<Being>[][] world;
	private ArrayList<Bonks> bonks;
	private ArrayList<Zaps> zaps;
	private ArrayList<Mortal> mortals;
	
	private int bonksAlive;
	private int babyBonksAlive;
	private int matureBonks;
	private int babyBonks;
	
	
	private int cycles;
	private final int MAX_CYCLES;
	private int numBeings;
	
	private Random rand;
	
	public Game(){
		MAX_CYCLES = 0;
	}
	
	public Game(int rows, int cols, int numBonks, int numZaps, int MAX_C) throws CannotActException{
		this.rows = rows;
		this.cols = cols;
		this.numBonks = numBonks;
		this.numZaps = numZaps;
		
		world = new ArrayList[rows][cols];
		bonks = new ArrayList<Bonks>();
		zaps = new ArrayList<Zaps>();
		mortals = new ArrayList<Mortal>();
		
		numBeings = numBonks + numZaps;
		cycles = 0;
		MAX_CYCLES = MAX_C;	

		createBeings();
		startGame();
	}

	// GAME LOOP
	public void startGame() throws CannotActException{
		do {
			try {
				addMortals();
				actZaps();
				actMortals();
				actBonks();
				System.out.println("\nDay. " + cycles);
				for(int i = 0; i < bonks.size(); i++){
					System.out.println("(" + bonks.get(i).getLocation().getPositionX() + "," + bonks.get(i).getLocation().getPositionY() + ") " + bonks.get(i).getName());
				}
				
				//printGame();
				cycles++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while(cycles <= MAX_CYCLES);
		
		for(int i = 0; i < bonks.size(); i++){
			if(bonks.get(i).isAdult()){
				matureBonks += 1;
				if(bonks.get(i).getLives() > 0 && bonks.get(i).isAdult()){
					bonksAlive += 1;
				} 
			} else {
				babyBonks += 1;
				if(bonks.get(i).getLives() > 0 && !bonks.get(i).isAdult()){
					babyBonksAlive += 1;
				}
			}
		}
		
		System.out.println("\nGame-world is over, stats:");
		System.out.println(bonksAlive + "/" + matureBonks + " Mature Bonks is still alive");
		System.out.println(babyBonksAlive + "/" + babyBonks + " Baby Bonks is still alive");
	}
	
	public void addMortals(){
		for(int i = 0; i < bonks.size(); i++){
			mortals.add(bonks.get(i));
		}
	}
	
	public void actZaps() throws CannotActException{
		for(int i = 0; i < zaps.size(); i++){
			zaps.get(i).setSquare(rows);
			zaps.get(i).setMortals(mortals);
			zaps.get(i).act();
			mortals = zaps.get(i).getMortals();
		}
	}
	
	public void actBonks() throws CannotActException{
		for(int i = 0; i < bonks.size(); i++){
			bonks.get(i).setHasReproduced(false);
		}
		for(int i = 0; i < bonks.size(); i++){
			bonks.get(i).setBonks(bonks);
			bonks.get(i).act();
			bonks = bonks.get(i).getBonks();
		}
	}
	
	public void actMortals() throws CannotActException{
		for(int i = 0; i < mortals.size(); i++){	
			mortals.get(i).setSquare(rows);
//			mortals.get(i).act();
		}
	}
	
	// Creating Bonks and Zaps
	public void createBeings() {
		int x;
		int y;
		int s;
		Position location;
		String name = "";
		String sex = "";
		
		for(int i = 0; i < numBonks; i++){
			x = randomNum(rows);
			y = randomNum(cols);
			location = new Position(x,y);
			s = randomNum(2);
			if(s == 0){
				sex = "Male";
				name = "BM" + i;
			}else {
				sex = "Female";
				name = "BF" + i;
			}
			Bonks b = new Bonks(location, 1, sex, name, true);
			bonks.add(b);
			//System.out.println(b.getName() + "(" + b.getLocation().toString() + ")");
		}
		for(int i = 0; i < numZaps; i++){
			x = randomNum(rows);
			y = randomNum(cols);
			location = new Position(x,y);
			name = "Z" + i;
			Zaps z = new Zaps(location, name);
			zaps.add(z);
			//System.out.println(z.getName() + "(" + z.getLocation().toString() + ")");
		}
		
		// @@@@@@ TEST FOR HOW TO STORE BEINGS @@@@@@@@@
//		ArrayList<Being> k = new ArrayList<Being>();
//		for(int i = 0; i < rows; i++){
//			for(int j = 0; j < cols; j++){
//				for(int b = 0; b < beings.size(); b++){
//					if(beings.get(b).getLocation().getPositionX() == i && beings.get(b).getLocation().getPositionY() == j){
//						k.add(beings.get(b));
//					}
//				}
//				if(k != null){
//					g[i][j].add(k);
//				}
//				for(int h = 0; h < k.size(); h++){
//					System.out.println("hei");
//					k.remove(h);
//				}
//			}
//		}
	}
	
	// Creates a integer number of the range it get's as input
	public int randomNum(int num){
		rand = new Random();
		int r = rand.nextInt(num);
		return r;
	}
	
	// Prints the Grid out
	public void printGame() {
		ArrayList<Being> countBeings = new ArrayList<Being>();

		for(int i = 0; i <= rows; i++){
			System.out.println("\nDay. " + cycles);
			
			for(int j = 0; j <= cols; j++){
//				for(int k = 0; k < bonks.size(); k++){
//					if(bonks.get(k).getLocation().getPositionX() == i && bonks.get(k).getLocation().getPositionY() == j){
//						countBeings.add(bonks.get(k));
//					} 
//				}
				for(int h = 0; h < zaps.size(); h++){
					if(zaps.get(h).getLocation().getPositionX() == i && zaps.get(h).getLocation().getPositionY() == j){
						countBeings.add(zaps.get(h));
					}
				}
				for(int m = 0; m < mortals.size(); m++){
					if(mortals.get(m).getLocation().getPositionX() == i && mortals.get(m).getLocation().getPositionY() == j){
						countBeings.add(mortals.get(m));
					}
				}
				
				System.out.print("[" + i + ", " + j + "] = " + "[");
				//System.out.print("[");
				for(int b = 0; b < countBeings.size(); b++){
					System.out.print(countBeings.get(b).getName());
//					if(countBeings.size() > 1){
//						
//					}
					countBeings.remove(b);
				}
				System.out.println("]");
			}
		}
	}
	
	public int getNumBonks() {
		return numBonks;
	}

	public int getNumZaps() {
		return numZaps;
	}

	public int getCycles() {
		return cycles;
	}

	public int getMAX_CYCLES() {
		return MAX_CYCLES;
	}

	public void setNumBonks(int numBonks) {
		this.numBonks = numBonks;
	}

	public void setNumZaps(int numZaps) {
		this.numZaps = numZaps;
	}

	public void setCycles(int cycles) {
		this.cycles = cycles;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public ArrayList<Being>[][] getWorld() {
		return world;
	}

	public ArrayList<Bonks> getBonks() {
		return bonks;
	}

	public ArrayList<Zaps> getZaps() {
		return zaps;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public void setWorld(ArrayList<Being>[][] world) {
		this.world = world;
	}

	public void setBonks(ArrayList<Bonks> bonks) {
		this.bonks = bonks;
	}

	public void setZaps(ArrayList<Zaps> zaps) {
		this.zaps = zaps;
	}

	@Override
	public String toString() {
		return "Game [rows=" + rows + ", cols=" + cols + ", numBonks="
				+ numBonks + ", numZaps=" + numZaps + ", world="
				+ Arrays.toString(world) + ", bonks=" + bonks + ", zaps="
				+ zaps + ", mortals=" + mortals + ", bonksAlive=" + bonksAlive
				+ ", babyBonksAlive=" + babyBonksAlive + ", matureBonks="
				+ matureBonks + ", babyBonks=" + babyBonks + ", cycles="
				+ cycles + ", MAX_CYCLES=" + MAX_CYCLES + ", numBeings="
				+ numBeings + ", rand=" + rand + "]";
	}
		
}
