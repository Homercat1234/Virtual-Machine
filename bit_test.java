class bit_test {
	public static Bit test;

	public static void runTests() { // A method tha runs all the tests.
		spacer();
		testCreate();
		spacer();
		testSet();
		spacer();
		testToggle();
		spacer();
		testSetTrue();
		spacer();
		testClear();
		spacer();
		testGetValue();
		spacer();
		testBitAnd();
		spacer();
		testBitOr();
		spacer();
		testBitXor();
		spacer();
		testBitNot();
		spacer();
		testToString();
		spacer();
	}

	private static void testCreate() { // Tests the creation of a new bit in both cases of true and false.
		if (new Bit(true).getValue())
			System.out.println("Create (T) succeded");
		else
			System.out.println("Create (T) failed");
		if (!(new Bit(false).getValue()))
			System.out.println("Create (F) succeded");
		else
			System.out.println("Create (F) failed");
	}

	private static void testSet() { // Tests seting with a paramenter in tthe following cases T-T, T-F, F-T, and
									// F-F.
		test = new Bit(true);
		test.set(true);
		if (test.getValue())
			System.out.println("Set[T-T] succeded");
		else
			System.out.println("Set[T-T] failed");
		test = new Bit(true);
		test.set(false);
		if (!test.getValue())
			System.out.println("Set[T-F] succeded");
		else
			System.out.println("Set[T-F] failed");
		test = new Bit(false);
		test.set(true);
		if (test.getValue())
			System.out.println("Set[F-T] succeded");
		else
			System.out.println("Set[F-T] failed");
		test = new Bit(false);
		test.set(false);
		if (!test.getValue())
			System.out.println("Set[F-F] succeded");
		else
			System.out.println("Set[F-F] failed");
	}

	private static void testToggle() { // Tests toggle in the cases of starting with true and starting with false.
		test = new Bit(true);
		test.toggle();
		if (!(test.getValue()))
			System.out.println("Toggle (T) succeded");
		else
			System.out.println("Toggle (T) failed");
		test.set(false);
		test.toggle();
		if (test.getValue())
			System.out.println("Toggle (F) succeded");
		else
			System.out.println("Toggle (F) failed");
	}

	private static void testSetTrue() { // Testing set with no parameters in the cases of starting with true and
										// starting with false.
		test = new Bit(true);
		test.set();
		if (test.getValue())
			System.out.println("Set[Start w/T] succeded");
		else
			System.out.println("Set[Start w/T] failed");
		test.set(false);
		test.set();
		if (test.getValue())
			System.out.println("Set[Start w/F] succeded");
		else
			System.out.println("Set[Start w/F] failed");
	}

	private static void testClear() { // Testing clear in the cases of starting with true and starting with false.
		test = new Bit(true);
		test.clear();
		if (!test.getValue())
			System.out.println("Clear[Start w/T] succeded");
		else
			System.out.println("Clear[Start w/T] failed");
		test.set(false);
		test.clear();
		if (!test.getValue())
			System.out.println("Clear[Start w/F] succeded");
		else
			System.out.println("Clear[Start w/F] failed");
	}

	private static void testGetValue() { // Testing get value in the cases of true and false.
		test = new Bit(true);
		if (test.getValue())
			System.out.println("getValue (T) succeded");
		else
			System.out.println("getValue (T) succeded");
		test.toggle();
		if (!test.getValue())
			System.out.println("getValue (F) succeded");
		else
			System.out.println("getValue (F) succeded");
	}

	private static void testBitAnd() { // Testing bitAnd in the following cases: T-T, T-F, F-T, F-F.
		test = new Bit(true);
		if (test.bitAnd(new Bit(true)).getValue())
			System.out.println("bitAnd (T, T) succeded");
		else
			System.out.println("bitAnd (T, T) failed");
		if (!test.bitAnd(new Bit(false)).getValue())
			System.out.println("bitAnd (T, F) succeded");
		else
			System.out.println("bitAnd (T, F) failed");
		test.toggle();
		if (!test.bitAnd(new Bit(true)).getValue())
			System.out.println("bitAnd (F, T) succeded");
		else
			System.out.println("bitAnd (F, T) failed");
		if (!test.bitAnd(new Bit(false)).getValue())
			System.out.println("bitAnd (F, F) succeded");
		else
			System.out.println("bitAnd (F, F) failed");
	}

	private static void testBitOr() { // Testing bitOr in the following cases: T-T, T-F, F-T, F-F.
		test = new Bit(true);
		if (test.bitOr(new Bit(true)).getValue())
			System.out.println("bitOr (T, T) succeded");
		else
			System.out.println("bitOr (T, T) failed");
		if (test.bitOr(new Bit(false)).getValue())
			System.out.println("bitOr (T, F) succeded");
		else
			System.out.println("bitOr (T, F) failed");
		test.toggle();
		if (test.bitOr(new Bit(true)).getValue())
			System.out.println("bitOr (F, T) succeded");
		else
			System.out.println("bitOr (F, T) failed");
		if (!test.bitOr(new Bit(false)).getValue())
			System.out.println("bitOr (F, F) succeded");
		else
			System.out.println("bitOr (F, F) failed");
	}

	private static void testBitXor() { // Testing bitXor in the following cases: T-T, T-F, F-T, F-F.
		test = new Bit(true);
		if (!test.bitXor(new Bit(true)).getValue())
			System.out.println("bitXor (T, T) succeded");
		else
			System.out.println("bitXor (T, T) failed");
		if (test.bitXor(new Bit(false)).getValue())
			System.out.println("bitXor (T, F) succeded");
		else
			System.out.println("bitXor (T, F) failed");
		test.toggle();
		if (test.bitXor(new Bit(true)).getValue())
			System.out.println("bitXor (F, T) succeded");
		else
			System.out.println("bitXor (F, T) failed");
		if (!test.bitXor(new Bit(false)).getValue())
			System.out.println("bitXor (F, F) succeded");
		else
			System.out.println("bitXor (F, F) failed");
	}

	private static void testBitNot() { // Testing bitNot starting with true and then with false.
		test = new Bit(true);
		if (!(test.bitNot().getValue()))
			System.out.println("bitNot (T) succeded");
		else
			System.out.println("bitNot (T) failed");
		test.toggle();
		if (test.bitNot().getValue())
			System.out.println("bitNot (F) succeded");
		else
			System.out.println("bitNot (F) failed");
	}

	private static void testToString() { // Testing toString for false and true.
		test = new Bit(true);
		if (test.toString().equals("t"))
			System.out.println("toString (T) succeded");
		else
			System.out.println("toString (T) failed");
		test.toggle();
		if (test.toString().equals("f"))
			System.out.println("toString (F) succeded");
		else
			System.out.println("toString (F) failed");
	}

	private static void spacer() { // A method that prints a line.
		System.out.println("----------------------");
	}
}