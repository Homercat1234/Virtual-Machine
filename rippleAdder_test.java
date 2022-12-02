public class rippleAdder_test { // A class to test the ripple adder
    public static void runTest() {
        testAdd();
        spacer();
        testSubtract();
    }

    public static void testAdd() { // Tests addition in 6 cases
        int result = RippleAdder.add(new Longword(100), new Longword(5)).getSigned();
        System.out.println(result == 105 ? "5 + 100 succeded!" : "5 + 100 failed: 105 expected, " + result + " got.");
        result = RippleAdder.add(new Longword(5), new Longword(15)).getSigned();
        System.out.println(result == 20 ? "5 + 15 succeded!" : "5 + 15 failed: 20 expected, " + result + " got.");
        result = RippleAdder.add(new Longword(-5), new Longword(15)).getSigned();
        System.out.println(result == 10 ? "-5 + 15 succeded!" : "-5 + 15 failed: 10 expected, " + result + " got.");
        result = RippleAdder.add(new Longword(5), new Longword(-15)).getSigned();
        System.out.println(result == -10 ? "5 + -15 succeded!" : "5 + -15 failed: -10 expected, " + result + " got.");
        result = RippleAdder.add(new Longword(2147483600), new Longword(40)).getSigned();
        System.out.println(result == 2147483640 ? "2147483600 + 40 succeded!"
                : "2147483600 + 40 failed: 2147483640 expected, " + result + " got.");
        result = RippleAdder.add(new Longword(-2147483600), new Longword(-40)).getSigned();
        System.out.println(result == -2147483640 ? "-2147483600 + -40 succeded!"
                : "-2147483600 + -40 failed: -2147483640 expected, " + result + " got.");
    }

    public static void testSubtract() { // Tests subtraction in 6 cases
        int result = RippleAdder.subtract(new Longword(100), new Longword(5)).getSigned();
        System.out.println(result == 95 ? "5 - 100 succeded!" : "5 - 100 failed: 95 expected, " + result + " got.");
        result = RippleAdder.subtract(new Longword(5), new Longword(15)).getSigned();
        System.out.println(result == -10 ? "5 - 15 succeded!" : "5 - 15 failed: -10 expected, " + result + " got.");
        result = RippleAdder.subtract(new Longword(-5), new Longword(15)).getSigned();
        System.out.println(result == -20 ? "-5 - 15 succeded!" : "-5 - 15 failed: -20 expected, " + result + " got.");
        result = RippleAdder.subtract(new Longword(-5), new Longword(-15)).getSigned();
        System.out.println(result == 10 ? "-5 - -15 succeded!" : "-5 - -15 failed: 10 expected, " + result + " got.");
        result = RippleAdder.subtract(new Longword(2147483600), new Longword(40)).getSigned();
        System.out.println(result == 2147483560 ? "2147483600 - 40 succeded!"
                : "2147483600 - 40 failed: 2147483560 expected, " + result + " got.");
        result = RippleAdder.subtract(new Longword(-2147483600), new Longword(-40)).getSigned();
        System.out.println(result == -2147483560 ? "-2147483600 - -40 succeded!"
                : "-2147483600 - -40 failed: -2147483560 expected, " + result + " got.");
    }

    public static void spacer() {
        System.out.println("----------------------");
    }
}
