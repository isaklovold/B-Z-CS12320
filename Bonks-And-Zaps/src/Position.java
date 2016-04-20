
public class Position {

	private int positionX, positionY;
	
	public Position(int posX, int posY){
		positionX = posX;
		positionY = posY;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	@Override
	public String toString() {
		return "Position [positionX=" + positionX + ", positionY=" + positionY
				+ "]";
	}
}
