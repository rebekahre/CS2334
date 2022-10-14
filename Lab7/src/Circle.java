
public class Circle extends Shape {
	private double radius;
	
	public Circle(double radius) {
		this.radius = radius;
		if(radius<=0) {
			throw new IllegalArgumentException("Nonpositive radius: " + radius);
		}
	}
	
	public double getRadius() {
		return this.radius;
		
	}
	
	@Override
	public double getPerimeter() {
		double perimeter = 2*Math.PI*radius;
		return perimeter;
	}

	@Override
	public double getArea() {
		double area = Math.PI * radius * radius;
		return area;
	}
	
	

}
