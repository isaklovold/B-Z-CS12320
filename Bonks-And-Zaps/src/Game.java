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
				for(int i = 0; i < bonks.size(); i++){
					bonks.get(i).act();
				}
				for(int i = 0; i < zaps.size(); i++){
					zaps.get(i).act();
				}
				printGame();
				cycles++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while(cycles <= MAX_CYCLES);
		
		System.out.println("Game-world is over, stats:");
		System.out.println("Bonks alive: " + "@@ add code for bonks alive after game @@");
		System.out.println("Baby Bonks alive: " + "@@ add code for baby bonks alive after game @@");
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
			Bonks b = new Bonks(location, 1, sex, name);
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
			for(int j = 0; j <= cols; j++){
				for(int k = 0; k < bonks.size(); k++){
					if(bonks.get(k).getLocation().getPositionX() == i && bonks.get(k).getLocation().getPositionY() == j){
						countBeings.add(bonks.get(k));
					} 
				}
				for(int h = 0; h < zaps.size(); h++){
					if(zaps.get(h).getLocation().getPositionX() == i && zaps.get(h).getLocation().getPositionY() == j){
						countBeings.add(zaps.get(h));
					}
				}
				System.out.print("[" + i + ", " + j + "] = " + "[");
				for(int b = 0; b < countBeings.size(); b++){
					System.out.print(countBeings.get(b).getName());
					countBeings.remove(b);
				}
				System.out.println("]");
				
//				Position location = new Position(i,j);
//				for(int k = 0; k < bonks.size(); k++){
//					for(int h = 0; h < zaps.size(); h++){
//						if(bonks.get(k).getLocation() == location || zaps.get(h).getLocation() == location) {
//							System.out.println("[" + i + ", " + j + "] = " + "[" + bonks.get(k).getName() + ", " + zaps.get(h).getName() + "]");
//						} else {
//							
//						}
//					}
//				}
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
		return "Game [rows=" + rows + ", cols=" + cols + ", world="
				+ Arrays.toString(world) + ", bonks=" + bonks + ", zaps="
				+ zaps + "]";
	}
		
}
