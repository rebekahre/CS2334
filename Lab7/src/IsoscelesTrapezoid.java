
public class IsoscelesTrapezoid extends Polygon {
	private double top;
	private double base;
	private double leg;
	private double area;
	
	public IsoscelesTrapezoid(double top, double base, double leg) {
		super(top, base, leg, leg);
		this.top = top;
		this.base = base;
		this.leg=leg;
		
		this.area = 0.5*(top + base)*height();
	}
	
	protected double height() {
		double shortBase = 0.5*(top-base);
		double height = Math.sqrt((leg*leg)-(shortBase*shortBase));
		return height;
	}
	
	public double getTop() {
		return this.top;
	}
	
	public double getBase() {
		return this.base;
	}
	
	public double getLeg() {
		return this.leg;
	}
	
	public double getArea() {
		return this.area;
	}
	
	protected double smallBase() {
		double smallBase = 0;
		if(top-base<0) {
			return smallBase = top;
		}
		else {
			smallBase=base;
		}
		return smallBase;
	}
	
	protected double bigBase() {
		double bigBase = 0;
		if(top-base>0) {
			return bigBase = top;
		}
		else {
			bigBase = base;
		}
		return bigBase;
	}
	
	public Rectangle getCenterRectangle() {
		
		Rectangle rectangle = new Rectangle(smallBase(), height());
//		rectangle.getWidth();
//		rectangle.getHeight();
		return rectangle;
	}
	
	public RightTriangle getSideTriangle() {
		double shortBase = 0.5*(bigBase()-smallBase());
		RightTriangle triangle = new RightTriangle(shortBase, height());
		return triangle;
	}
	

	
	
}
