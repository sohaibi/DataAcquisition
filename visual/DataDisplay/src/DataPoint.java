public class DataPoint {
	private double xVal, yVal;
	
	public DataPoint(double xVal, double yVal) {
		this.xVal = xVal;
		this.yVal = yVal;
	}
	
	public void setPoint(double xVal, double yVal) {
		this.xVal = xVal;
		this.yVal = yVal;
	}
	
	public void setX(double xVal) {
		this.xVal = xVal;
	}
	
	public void setY(double yVal) {
		this.yVal = yVal;
	}
	
	public double getX() {
		return xVal;
	}
	
	public double getY() {
		return yVal;
	}
	
	public String toString() {
		return String.format("(%.3f, %.3f)", xVal, yVal);
	}
}
