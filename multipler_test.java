public class multipler_test { // A method that tests multiplication
    public static void runTests() {
        spacer();
        test();
    }

    public static void test() { // Tests addition in 6 cases
        int result = Multiplier.multiply(new Longword(100), new Longword(5)).getSigned();
        System.out.println(result == 500 ? "5 * 100 succeded!" : "5 * 100 failed: 500 expected, " + result + " got.");
        result = Multiplier.multiply(new Longword(5), new Longword(15)).getSigned();
        System.out.println(result == 75 ? "5 * 15 succeded!" : "5 * 15 failed: 75 expected, " + result + " got.");
        result = Multiplier.multiply(new Longword(-5), new Longword(15)).getSigned();
        System.out.println(result == -75 ? "-5 * 15 succeded!" : "-5 * 15 failed: -75 expected, " + result + " got.");
        result = Multiplier.multiply(new Longword(-5), new Longword(-15)).getSigned();
        System.out.println(result == 75 ? "-5 * -15 succeded!" : "-5 * -15 failed: 75 expected, " + result + " got.");
        result = Multiplier.multiply(new Longword(2147483600), new Longword(40)).getSigned();
        System.out.println(result == -1920 ? "2147483600 * 40 succeded!"
                : "2147483600 * 40 failed: -1920 expected, " + result + " got.");
        result = Multiplier.multiply(new Longword(-2147483600), new Longword(-40)).getSigned();
        System.out.println(result == -1920 ? "-2147483600 * -40 succeded!"
                : "-2147483600 * -40 failed: -1920 expected, " + result + " got.");
    }

    public static void spacer() {
        System.out.println("----------------------");
    }
}
