
public abstract class Polygon extends Shape {
	private double perimeter;

	protected Polygon(double... sides) {

		if(sides == null) {
			throw new IllegalArgumentException("null sides");
		}
		else if(sides.length < 3){
			throw new IllegalArgumentException("Invalid number of sides: " + sides.length);
		}
		else {
			for(int i = 0; i < sides.length; ++i) {
				if(sides[i] <= 0.0) {
					throw new IllegalArgumentException("Nonpositive side: " + sides[i]);
				}
			}

			double sum = 0.0;
			for(int i=0; i<sides.length; ++i) {
				sum = 0.0;
				for(int j=0; j<sides.length; ++j) {
					sum= sum + sides[j];
				}
				sum = sum - sides[i];
				if(sides[i] >= sum) {
					throw new IllegalArgumentException("Polygon inequality violated: " + sides[i] + " >= " + sum);
				}
			}
		}

		for(int i = 0; i < sides.length; ++i) {
			perimeter = perimeter + sides[i];
		}

	}

	public double getPerimeter() {
		return perimeter;

	}
}	
