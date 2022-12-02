class Bit {
	private boolean bit;

	public Bit(boolean value) { // A constructor that createss a new bit based on value.
		bit = value;
	}

	public void set(boolean value) { // A method that updates bit to value.
		bit = value;
	}

	public void toggle() { // A method that negates and updates bit
		bit = this.bitNot().getValue();
	}

	public void set() { // A method that updates bit to true.
		bit = true;
	}

	public void clear() { // A method that updates bit to false.
		bit = false;
	}

	public boolean getValue() { // A method returns bit.
		return bit;
	}

	public Bit bitAnd(Bit other) { // A method that returns a new bit with the value of true if and only if both
									// values are true. Else returns it with false.
		return bit ? other.getValue() ? new Bit(true) : new Bit(false) : new Bit(false);
	}

	public Bit bitOr(Bit other) { // A method that returns true a new bit with the value of true if one value is
									// true else returns it with false.
		return bit ? new Bit(true) : other.getValue() ? new Bit(true) : new Bit(false);
	}

	public Bit bitXor(Bit other) { // A method that returns a new bit with the value of true if only one value is
									// true else returns a new bit with the value of false.
		return this.bitAnd(other).getValue() ? new Bit(false) : this.bitOr(other);
	}

	public Bit bitNot() { // A method that returns a new bit with the bit negated.
		return new Bit(bit ? false : true);
	}

	public String toString() { // A method that converts bit to a string.
		return bit ? "t" : "f";
	}

	public boolean equals(Bit other) { // A method to compare another bit.
		return bit ? other.getValue() ? true : false : other.getValue() ? false : true;
	}
}