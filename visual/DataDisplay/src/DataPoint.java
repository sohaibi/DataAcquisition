public class DataPoint {
	private float xVal, yVal;
	
	public DataPoint(float xVal, float yVal) {
		this.xVal = xVal;
		this.yVal = yVal;
	}
	
	public void setPoint(float xVal, float yVal) {
		this.xVal = xVal;
		this.yVal = yVal;
	}
	
	public void setX(float xVal) {
		this.xVal = xVal;
	}
	
	public void setY(float yVal) {
		this.yVal = yVal;
	}
	
	public float getX() {
		return xVal;
	}
	
	public float getY() {
		return yVal;
	}
	
	public String toString() {
		return String.format("(%.3f, %.3f)", xVal, yVal);
	}
}
