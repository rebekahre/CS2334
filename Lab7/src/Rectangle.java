
public class Rectangle extends IsoscelesTrapezoid{

	public Rectangle(double width, double height) {
		super(width, width, height);
		
	}
	
	public double getWidth() {	
		return smallBase();
		
	}
	
	public double getHeight() {
		return height();
	}
	

	
}
