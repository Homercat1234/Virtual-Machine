public class ALU_tests { // A class that tests the ALU
    public static void runTests() { // A method that runs all the tests
        spacer();
        testAnd();
        spacer();
        testOr();
        spacer();
        testNot();
        spacer();
        testXor();
        spacer();
        testAdd();
        spacer();
        testSubtract();
        spacer();
        testMultiply();
        spacer();
        testLeftShift();
        spacer();
        testRightShift();
    }

    private static Bit[] halfbyte(String value) { // A method that converts a string to a byte
        Bit[] halfbyte = new Bit[4];
        for (int i = 3; i >= 0; i--)
            halfbyte[i] = Character.getNumericValue(value.charAt(i)) == 1 ? new Bit(true) : new Bit(false);
        return halfbyte;
    }

    private static void testAnd() { // Tests 1000 in 4 cases
        System.out.println((ALU.doOp(halfbyte("1000"), new Longword(31), new Longword(7000))
                .getSigned() == (new Longword(31).longwordAnd(new Longword(7000))).getSigned()) ? "And succeded"
                        : "And failed");

        System.out.println((ALU.doOp(halfbyte("1000"), new Longword(31), new Longword(-7000))
                .getSigned() == (new Longword(31).longwordAnd(new Longword(-7000))).getSigned()) ? "And succeded"
                        : "And failed");

        System.out.println((ALU.doOp(halfbyte("1000"), new Longword(231), new Longword(17000))
                .getSigned() == (new Longword(231).longwordAnd(new Longword(17000))).getSigned()) ? "And succeded"
                        : "And failed");

        System.out.println((ALU.doOp(halfbyte("1000"), new Longword(0), new Longword(0))
                .getSigned() == (new Longword(0).longwordAnd(new Longword(0))).getSigned()) ? "And succeded"
                        : "And failed");
    }

    private static void testOr() { // Tests 1001 in 4 cases
        System.out.println((ALU.doOp(halfbyte("1001"), new Longword(31), new Longword(7000))
                .getSigned() == (new Longword(31).longwordOr(new Longword(7000))).getSigned()) ? "Or succeded"
                        : "Or failed");

        System.out.println((ALU.doOp(halfbyte("1001"), new Longword(31), new Longword(-7000))
                .getSigned() == (new Longword(31).longwordOr(new Longword(-7000))).getSigned()) ? "Or succeded"
                        : "Or failed");

        System.out.println((ALU.doOp(halfbyte("1001"), new Longword(231), new Longword(17000))
                .getSigned() == (new Longword(231).longwordOr(new Longword(17000))).getSigned()) ? "Or succeded"
                        : "Or failed");

        System.out.println((ALU.doOp(halfbyte("1001"), new Longword(0), new Longword(0))
                .getSigned() == (new Longword(0).longwordOr(new Longword(0))).getSigned()) ? "Or succeded"
                        : "Or failed");
    }

    private static void testNot() { // Tests 1011 in 4 cases
        System.out.println((ALU.doOp(halfbyte("1011"), new Longword(31), new Longword(7000))
                .getSigned() == (new Longword(31).longwordNot()).getSigned()) ? "Not succeded" : "Not failed");

        System.out.println((ALU.doOp(halfbyte("1011"), new Longword(31), new Longword(-7000))
                .getSigned() == (new Longword(31).longwordNot()).getSigned()) ? "Not succeded" : "Not failed");

        System.out.println((ALU.doOp(halfbyte("1011"), new Longword(231), new Longword(17000))
                .getSigned() == (new Longword(231).longwordNot()).getSigned()) ? "Not succeded" : "Not failed");

        System.out.println((ALU.doOp(halfbyte("1011"), new Longword(0), new Longword(0))
                .getSigned() == (new Longword(0).longwordNot()).getSigned()) ? "Not succeded" : "Not failed");
    }

    private static void testXor() { // Tests 1010 in 4 cases
        System.out.println((ALU.doOp(halfbyte("1010"), new Longword(31), new Longword(7000))
                .getSigned() == (new Longword(31).longwordXor(new Longword(7000))).getSigned()) ? "Xor succeded"
                        : "Xor failed");

        System.out.println((ALU.doOp(halfbyte("1010"), new Longword(31), new Longword(-7000))
                .getSigned() == (new Longword(31).longwordXor(new Longword(-7000))).getSigned()) ? "Xor succeded"
                        : "Xor failed");

        System.out.println((ALU.doOp(halfbyte("1010"), new Longword(231), new Longword(17000))
                .getSigned() == (new Longword(231).longwordXor(new Longword(17000))).getSigned()) ? "Xor succeded"
                        : "Xor failed");

        System.out.println((ALU.doOp(halfbyte("1010"), new Longword(0), new Longword(0))
                .getSigned() == (new Longword(0).longwordXor(new Longword(0))).getSigned()) ? "Xor succeded"
                        : "Xor failed");
    }

    private static void testAdd() { // Tests 1110 in 4 cases
        System.out.println((ALU.doOp(halfbyte("1110"), new Longword(31), new Longword(7000)).getSigned() == RippleAdder
                .add(new Longword(31), (new Longword(7000))).getSigned()) ? "Add succeded" : "Add failed");

        System.out.println((ALU.doOp(halfbyte("1110"), new Longword(31), new Longword(-7000)).getSigned() == RippleAdder
                .add(new Longword(31), (new Longword(-7000))).getSigned()) ? "Add succeded" : "Add failed");

        System.out
                .println((ALU.doOp(halfbyte("1110"), new Longword(231), new Longword(17000)).getSigned() == RippleAdder
                        .add(new Longword(231), (new Longword(17000))).getSigned()) ? "Add succeded" : "Add failed");

        System.out.println((ALU.doOp(halfbyte("1110"), new Longword(0), new Longword(0)).getSigned() == RippleAdder
                .add(new Longword(0), (new Longword(0))).getSigned()) ? "Add succeded" : "Add failed");
    }

    private static void testSubtract() { // Tests 1111 in 4 cases
        System.out.println((ALU.doOp(halfbyte("1111"), new Longword(31), new Longword(7000)).getSigned() == RippleAdder
                .subtract(new Longword(31), (new Longword(7000))).getSigned()) ? "Subtract succeded"
                        : "Subtract failed");

        System.out.println((ALU.doOp(halfbyte("1111"), new Longword(31), new Longword(-7000)).getSigned() == RippleAdder
                .subtract(new Longword(31), (new Longword(-7000))).getSigned()) ? "Subtract succeded"
                        : "Subtract failed");

        System.out
                .println((ALU.doOp(halfbyte("1111"), new Longword(231), new Longword(17000)).getSigned() == RippleAdder
                        .subtract(new Longword(231), (new Longword(17000))).getSigned()) ? "Subtract succeded"
                                : "Subtract failed");

        System.out.println((ALU.doOp(halfbyte("1111"), new Longword(0), new Longword(0)).getSigned() == RippleAdder
                .subtract(new Longword(0), (new Longword(0))).getSigned()) ? "Subtract succeded" : "Subtract failed");
    }

    private static void testMultiply() { // Tests 0111 in 4 cases
        System.out.println((ALU.doOp(halfbyte("0111"), new Longword(31), new Longword(7000)).getSigned() == Multiplier
                .multiply(new Longword(31), (new Longword(7000))).getSigned()) ? "Multiply succeded"
                        : "Multiply failed");

        System.out.println((ALU.doOp(halfbyte("0111"), new Longword(31), new Longword(-7000)).getSigned() == Multiplier
                .multiply(new Longword(31), (new Longword(-7000))).getSigned()) ? "Multiply succeded"
                        : "Multiply failed");

        System.out.println((ALU.doOp(halfbyte("0111"), new Longword(231), new Longword(17000)).getSigned() == Multiplier
                .multiply(new Longword(231), (new Longword(17000))).getSigned()) ? "Multiply succeded"
                        : "Multiply failed");

        System.out.println((ALU.doOp(halfbyte("0111"), new Longword(0), new Longword(0)).getSigned() == Multiplier
                .multiply(new Longword(0), (new Longword(0))).getSigned()) ? "Multiply succeded" : "Multiply failed");
    }

    private static void testLeftShift() { // Tests 1100 in 4 cases
        try {
            System.out.println((ALU.doOp(halfbyte("1100"), new Longword(31), new Longword(7000))
                    .getSigned() == (new Longword(31).leftShift(new Longword(24).getSigned())).getSigned())
                            ? "LeftShift succeded"
                            : "LeftShift failed");

            System.out.println((ALU.doOp(halfbyte("1100"), new Longword(31), new Longword(-7000))
                    .getSigned() == (new Longword(31).leftShift(new Longword(8).getSigned())).getSigned())
                            ? "LeftShift succeded"
                            : "LeftShift failed");

            System.out.println((ALU.doOp(halfbyte("1100"), new Longword(231), new Longword(17000))
                    .getSigned() == (new Longword(231).leftShift(new Longword(8).getSigned())).getSigned())
                            ? "LeftShift succeded"
                            : "LeftShift failed");

            System.out.println((ALU.doOp(halfbyte("1100"), new Longword(0), new Longword(0))
                    .getSigned() == (new Longword(0).leftShift(0)).getSigned()) ? "LeftShift succeded"
                            : "LeftShift failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testRightShift() { // Tests 1101 in 4 cases
        try {
            System.out.println((ALU.doOp(halfbyte("1101"), new Longword(31), new Longword(7000))
                    .getSigned() == (new Longword(31).rightShift(new Longword(24).getSigned())).getSigned())
                            ? "RightShift succeded"
                            : "RightShift failed");

            System.out.println((ALU.doOp(halfbyte("1101"), new Longword(31), new Longword(-7000))
                    .getSigned() == (new Longword(31).rightShift(new Longword(8).getSigned())).getSigned())
                            ? "RightShift succeded"
                            : "RightShift failed");

            System.out.println((ALU.doOp(halfbyte("1101"), new Longword(231), new Longword(17000))
                    .getSigned() == (new Longword(231).rightShift(new Longword(8).getSigned())).getSigned())
                            ? "RightShift succeded"
                            : "RightShift failed");

            System.out.println((ALU.doOp(halfbyte("1101"), new Longword(0), new Longword(0))
                    .getSigned() == (new Longword(0).rightShift(0)).getSigned()) ? "RightShift succeded"
                            : "RightShift failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void spacer() {
        System.out.println("----------------------");
    }
}
