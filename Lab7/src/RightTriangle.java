
public class RightTriangle extends Triangle {

	public RightTriangle(double base, double height) {
		super(base, height, Math.sqrt((base*base)+(height*height)));
		double hypotenuse = Math.sqrt((base*base)+(height*height));
	}
	
	public double getBase() {
		return getSideA();
		
	}
	
	public double getHeight() {
		return getSideB();
		
	}
	
	public double getHypotenuse() {
		return getSideC();
		
	}
	

}
