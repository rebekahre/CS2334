
public class Triangle extends Polygon{
	private double sideA;
	private double sideB;
	private double sideC;
	private double area;
	
	public Triangle(double sideA, double sideB, double sideC) {
		
		super(sideA, sideB, sideC); 
		this.sideA = sideA;
		this.sideB = sideB;
		this.sideC = sideC;
		double semiPerim = (sideA+sideB+sideC)/2.0d;
		area = Math.sqrt(semiPerim*(semiPerim-sideA)*(semiPerim-sideB)*(semiPerim-sideC));
		
	}
	
	public double getSideA() {
		return this.sideA;
		
	}
	
	public double getSideB() {
		return this.sideB;
	}
	
	public double getSideC() {
		return this.sideC;
	}
	
	
	@Override
	public double getArea() {
		
		return area;
	}

}
