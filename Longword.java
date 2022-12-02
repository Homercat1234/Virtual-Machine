class Longword { // A class 32 bits
	private Bit[] longword = new Bit[32]; // A longword that is composed of 32 bits

	public Longword() { // A constructor for Longword that creates a new Longword that is 32 falses
		for (int i = 0; i < 32; i++)
			longword[i] = new Bit(false);
	}

	public Longword(Longword value) { // A constructor that sets the Longword to a string
		for (int i = 0; i < 32; i++)
			longword[i] = new Bit(value.getLongword()[i].getValue());
	}

	public Longword(int value) { // A constructor that sets the longword to a value
		set(value);
	}

	public Longword(String value) throws Exception { // A constructor that sets the longword to a value from string
		value = value.replaceAll("\\s", ""); // Remove all spaces from input so that input can be in the form of xxxx
												// xxxx xxxx xxxx

		if (value.length() > 32)
			throw new Exception("Error, length of string is " + value.length() + ", which is out of bounds");

		for (int i = 0; i < value.length(); i++) {
			if ((value.charAt(i) != 't' && value.charAt(i) != 'f')
					&& (value.charAt(i) != '0' && value.charAt(i) != '1')) // Through an execption if the input is not
																			// all 0s, 1s, fs, or ts
				throw new Exception("Error, invalid character at index " + i);

			longword[i] = Character.isDigit(value.charAt(i)) ? (value.charAt(i) == '1' ? new Bit(true) : new Bit(false))
					: (value.charAt(i) == 't' ? new Bit(true) : new Bit(false)); // Convert the input into bits
		}
		for (int i = value.length(); i < 32; i++) // Pad the end with 0s if needed
			longword[i] = new Bit(false);

	}

	public Bit getBit(int i) { // Returns this bit at a i
		return new Bit(longword[i].getValue());
	}

	public void setBit(int i, Bit value) { // Sets a bit at i to value
		longword[i] = new Bit(value.getValue());
	}

	public Longword longwordAnd(Longword other) { // Preforms and on two longwords
		Longword result = new Longword();
		for (int i = 0; i < 32; i++)
			result.setBit(i, longword[i].bitAnd(other.getBit(i)));
		return result;
	}

	public Longword longwordOr(Longword other) { // Preforms or on two longwords
		Longword result = new Longword();
		for (int i = 0; i < 32; i++)
			result.setBit(i, longword[i].bitOr(other.getBit(i)));
		return result;
	}

	public Longword longwordXor(Longword other) { // Preforms xor on two longwords
		Longword result = new Longword();
		for (int i = 0; i < 32; i++)
			result.setBit(i, longword[i].bitXor(other.getBit(i)));
		return result;
	}

	public Longword longwordNot() { // Negates this longword and returns a new longword
		Longword result = new Longword();
		for (int i = 0; i < 32; i++) {
			result.setBit(i, longword[i].bitNot());
		}
		return result;
	}

	public Longword rightShift(int amount) throws Exception { // Shifts this longword to the right, amount times,
																// returning a new longword.
		// Throws an error if amount is greater than 32.
		if (amount > 32 || amount < 0)
			throw new Exception("Error, amount equals " + amount + ", which is out of bounds");
		Longword result = new Longword();
		for (int i = 31; amount - 1 < i; i--) // Shifts the bits to the right amount times
			result.setBit(i, longword[i - amount]);
		for (int i = 0; i < amount; i++) // Sets the amount bits from the end to false
			result.setBit(i, new Bit(false));
		return result;
	}

	public Longword leftShift(int amount) throws Exception { // Shifts this longword to the left, amount times,
																// returning a new longword.
		// Throws an error if amount is greater than 32.
		if (amount > 32 || amount < 0)
			throw new Exception("Error, amount equals " + amount + ", which is out of bounds");
		Longword result = new Longword();
		for (int i = 0; i < 32 - amount; i++) // Shifts the bits to the left amount times
			result.setBit(i, longword[i + amount]);
		for (int i = 32 - amount; i < 32; i++) // Sets the amount bits from the end to false
			result.setBit(i, new Bit(false));
		return result;
	}

	public String toString() { // Returns this longword as a string
		String result = new String();
		for (int i = 0; i < 32; i++) {
			result += longword[i] + ", ";
		}
		return result.substring(0, result.length() - 2); // Removes the last comma
	}

	public long getUnsigned() { // Gets the unsigned value of this longword
		long result = 0;
		for (long i = 31, x = 1; i >= 0; i--, x *= 2) // Saving the current factor as well as the curret iteration
			result += (longword[(int) i].getValue() ? 1 : 0) * x;
		return result;
	}

	public int getSigned() { // Get the signed value of this longword
		int result = 0; // Using an int because it will cause the regular unsigned to act like signed
		for (int i = 31, x = 1; i >= 0; i--, x *= 2) // Saving the current factor as well as the curret iteration
			result += (longword[i].getValue() ? 1 : 0) * x;
		return result;
	}

	public void set(int value) { // Set the value of this longword to be equal to value
		int temp = value;
		if (value < 0)
			value *= -1;
		for (int i = 31; i >= 0; i--) {
			longword[i] = value % 2 == 1 ? new Bit(true) : new Bit(false);
			value /= 2;
		}
		if (temp < 0) { // Applies twos complment if value was negative
			longword = RippleAdder.add(longwordNot(), new Longword(1)).getLongword();
			longword[0] = new Bit(true);
		}
	}

	public Bit[] getLongword() { // A method that get's the longword
		Bit[] results = new Bit[32];
		for (int i = 0; i < 32; i++)
			results[i] = new Bit(getBit(i).getValue() ? true : false);
		return results;
	}

	public void copy(Longword other) { // Copies the values of other to this longword
		for (int i = 0; i < 32; i++)
			longword[i] = new Bit(other.getLongword()[i].getValue() ? true : false);
	}

	public Longword duplicate() { // Duplicates this longwords;
		Longword result = new Longword();
		for (int i = 0; i < 32; i++)
			result.setBit(i, getBit(i));
		return result;
	}

	public Longword getBits(int bits, int n) throws Exception { // Gets the nth grouping of bits in longword
		if (bits > 32 || bits < 1)
			throw new Exception("Error, bits equals " + bits + ", which is out of bounds");
		if (n > (32 / bits) - 1 || n < 0)
			throw new Exception("Error, n equals " + n + ", which is out of bounds");
		Longword result = new Longword();
		for (int i = 0; i < bits; i++) {
			result.setBit(i, new Bit(true));
		}
		try {
			return longwordAnd(result.rightShift(n * bits)).rightShift(32 - ((n + 1) * bits)); // Grab the correct bits
																								// and
																								// then shift them to
																								// the
																								// end
		} catch (Exception e) {
			e.printStackTrace();
			return new Longword();
		}
	}

}