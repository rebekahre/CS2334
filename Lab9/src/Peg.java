
public enum Peg {
	LEFT,
	MIDDLE,
	RIGHT;

	public static Peg other(Peg p1, Peg p2) {
		if(p1 == p2) {
			throw new IllegalArgumentException();
		}
		if(p1 == null || p2 == null) {
			throw new NullPointerException();
		}
		Peg peg = null;
		if (p1 == MIDDLE && p2 == RIGHT) {
			peg = LEFT;
		}
		if (p1 == RIGHT && p2 == MIDDLE) {
			peg = LEFT;
		}
		if (p1 == LEFT && p2 == RIGHT) {
			peg = MIDDLE;
		}
		if (p1 == RIGHT && p2 == LEFT) {
			peg = MIDDLE;
		}
		if (p1 == LEFT && p2 == MIDDLE) {
			peg = RIGHT;
		}
		if (p1 == MIDDLE && p2 == LEFT) {
			peg = RIGHT;
		}
		return peg;
	}
}

