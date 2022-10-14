
public abstract class Shape implements Comparable<Shape> {

	private int id;
	private static int nextID = 0;
	
	protected Shape() {
		id = nextID;
		++nextID;
	}
	
	public int getID() {
		return id;
		
	}
	
	public abstract double getPerimeter();
	
	public abstract double getArea();
	
	@Override
	public int compareTo(Shape other) {
		String perimeter = String.format("%.1f", getPerimeter());
		String perimeter2 = String.format("%.1f", other.getPerimeter());
		String area = String.format("%.1f", getArea());
		String area2 = String.format("%.1f", other.getArea());
		String type = getClass().getName();
		String type2 = other.getClass().getName();
		if(type.compareTo(type2) < 0) {
			return -1;
		}
		else if(type.compareTo(type2) > 0) {
			return 1;
		}
		else {
			if(perimeter.compareTo(perimeter2) < 0) {
				return -1;
			}
			else if(perimeter.compareTo(perimeter2) > 0) {
				return 1;
			}
			else {
				if(area.compareTo(area2) < 0) {
					return -1;
				}
				else if(area.compareTo(area2) > 0) {
					return 1;
				}
				else {
					return 0;
					}		
					}
				}	
	}

	@Override
	public String toString() {
		return "<"
				+ getClass().getName()
				+ ", ID: " + id
				+ ", PERIMETER: " + String.format("%.1f", getPerimeter())
				+ ", AREA: " + String.format("%.1f", getArea())
				+ ">";
	}
}
