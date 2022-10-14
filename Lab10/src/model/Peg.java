package model;
public enum Peg {
	LEFT,
	MIDDLE,
	RIGHT;

	public static Peg other(Peg p1, Peg p2) {
		if (p1 == null || p2 == null) {
			throw new NullPointerException();
		}
		if (p1 == p2) {
			throw new IllegalArgumentException();
		}
		if (p1 != LEFT && p2 != LEFT) {
			return LEFT;
		}
		if (p1 != MIDDLE && p2 != MIDDLE) {
			return MIDDLE;
		}
		if (p1 != RIGHT && p2 != RIGHT) {
			return RIGHT;
		}
		throw new IllegalArgumentException();
	}
}
