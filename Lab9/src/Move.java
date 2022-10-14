
public enum Move {
	LEFT_TO_MIDDLE(Peg.LEFT, Peg.MIDDLE),
	LEFT_TO_RIGHT(Peg.LEFT, Peg.RIGHT),
	MIDDLE_TO_LEFT(Peg.MIDDLE, Peg.LEFT),
	MIDDLE_TO_RIGHT(Peg.MIDDLE, Peg.RIGHT),
	RIGHT_TO_LEFT(Peg.RIGHT, Peg.LEFT),
	RIGHT_TO_MIDDLE(Peg.RIGHT, Peg.MIDDLE);

	public final Peg from;
	public final Peg to;

	private Move(Peg from, Peg to) {
		this.from = from;
		this.to = to;
	}

	public static Move move(Peg from, Peg to) {
		Move transfer = null;
		if(from == to) {
			throw new IllegalArgumentException();
		}
		if(from == null || to == null) {
			throw new NullPointerException();
		}
		if(from == Peg.LEFT && to == Peg.MIDDLE) {
			transfer = LEFT_TO_MIDDLE;
		}
		if(from == Peg.LEFT && to == Peg.RIGHT) {
			transfer = LEFT_TO_RIGHT;
		}
		if(from == Peg.MIDDLE && to == Peg.LEFT) {
			transfer = MIDDLE_TO_LEFT;
		}
		if(from == Peg.MIDDLE && to == Peg.RIGHT) {
			transfer = MIDDLE_TO_RIGHT;
		}
		if(from == Peg.RIGHT && to == Peg.LEFT) {
			transfer = RIGHT_TO_LEFT;
		}
		if(from == Peg.RIGHT && to == Peg.MIDDLE) {
			transfer = RIGHT_TO_MIDDLE;
		}
		return transfer;
	}
}
