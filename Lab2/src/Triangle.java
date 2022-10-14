
public class Triangle {
	private double sideA;
	private double sideB;
	private double sideC;
	public static final double DEFAULT_SIDE = 1;
	
	
	public Triangle() {
		this.sideA = DEFAULT_SIDE;
		this.sideB = DEFAULT_SIDE;
		this.sideC = DEFAULT_SIDE;
		
	}
	
	
	public Triangle(double sideA, double sideB, double sideC) {
			this.sideA = sideA;
			this.sideB = sideB;
			this.sideC = sideC;
			
		if(!isTriangle(sideA, sideB, sideC)) {
			sideA = DEFAULT_SIDE;
			sideB = DEFAULT_SIDE;
			sideC = DEFAULT_SIDE;
		}	
	}
	
	
	public Triangle(double[] sides) {
		sideA = 0;
		sideB = 1;
		sideC = 2;
		
		if(!(isTriangle(sides))) {
			sideA = DEFAULT_SIDE;
			sideB = DEFAULT_SIDE;
			sideC = DEFAULT_SIDE;
		}
	}
	
	
	public Triangle(Triangle triangle) {
		if (triangle == null) {
			sideA = DEFAULT_SIDE;
			sideB = DEFAULT_SIDE;
			sideC = DEFAULT_SIDE;
		}
		else {
			this.sideA = triangle.sideA;
			this.sideB = triangle.sideB;
			this.sideC = triangle.sideC;
		}
	}
	
	
	public double getSideA() {
		return sideA;
	}
	
	
	public double getSideB() {
		return sideB;
	}
	
	
	public double getSideC() {
		return sideC;
	}
	
	
	public double[] getSides() {
		double[] allSides = new double[3];
			allSides[0] = sideA;
			allSides[1] = sideB;
			allSides[2] = sideC;	
		
		return allSides;
	}
	
	
	public double getAngleA() {
		double angleA = 180-getAngleB()-getAngleC();
		return Math.toDegrees(angleA);
	}
	
	
	public double getAngleB() {
		double angleB= 180-getAngleA()-getAngleC();
		return Math.toDegrees(angleB);
	}
	
	
	public double getAngleC() {
		double angleC = 180-getAngleA()-getAngleB();
		return Math.toDegrees(angleC);
	}
	
	
	public double[] getAngles() {
		double[] allAngles = new double[3];
		allAngles[0] = getAngleA();
		allAngles[1] = getAngleB();
		allAngles[2] = getAngleC();
		return allAngles;
	}
	
	
	public boolean setSideA(double sideA) {
		return false;
		
	}
	
	
	public boolean setSideB(double sideB) {
		return false;
		
	}
	
	
	public boolean setSideC(double sideC) {
		return false;
		
	}
	
	
	public static boolean isTriangle(double a, double b, double c) {
		if((a+b>c && b+c>a && c+a>b) && (a>0.0 && b>0.0 && c>0.0)) {
			return true;
		}
			else {
				return false;
			}
		}
	
	
	public static boolean isTriangle(double[] sides) {
		if(sides != null && sides.length == 3){
			double a = sides[0];
			double b = sides[1];
			double c = sides[2];
			if((a+b>c && b+c>a && c+a>b) && (a>0.0 && b>0.0 && c>0.0)) {
				return true;
			}
		}
		return false;
	
	}
	
	
	public static double lawOfCosines(double a, double b, double c) {
		
//		double angleA = Math.acos(Math.pow(b, 2) + Math.pow(c, 2) / (2.0 *b*c));
//		double angleB = Math.acos(Math.pow(a, 2) + Math.pow(c, 2) / (2.0*a*c));
		double angleC = Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2.0*a*b));
		
		return Math.toDegrees(angleC);
		
	}
	
	
	
}

	


