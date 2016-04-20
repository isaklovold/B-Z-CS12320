import java.util.ArrayList;
import java.util.Arrays;


public class Game {

	private int rows;
	private int cols;
	private int numBonks, numZaps;
	private ArrayList<Being>[][] world;
	private ArrayList<Bonks> bonks;
	private ArrayList<Zaps> zaps;

	private int cycles;
	private final int MAX_CYCLES;
	
	@SuppressWarnings("unchecked")
	public Game(int rows, int cols, int numBonks, int numZaps){
		this.rows = rows;
		this.cols = cols;
		
		world = new ArrayList[rows][cols];
		bonks = new ArrayList<Bonks>();
		zaps = new ArrayList<Zaps>();
		
		
		
		cycles = 0;
		MAX_CYCLES = 20;
	}

	public void gameLoop(){
		do {
			
		} while(cycles <= MAX_CYCLES);
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
