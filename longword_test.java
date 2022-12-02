public class longword_test { // A class to test longword
    private static Longword longword;

    public static void runTests() {
        testCreate();
        spacer();
        testGetBit();
        spacer();
        testSetBit();
        spacer();
        testLongwordAnd();
        spacer();
        testLongwordOr();
        spacer();
        testLongwordXor();
        spacer();
        testLeftShift();
        spacer();
        testRightShift();
        spacer();
        testGetUnSigned();
        spacer();
        testGetSigned();
        spacer();
        testSet();
        spacer();
        testCopy();
        spacer();
    }

    public static void testCreate() { // Tests the creation of a new longword
        longword = new Longword();
        System.out.println("Binary " + longword);
        System.out.println(longword.getUnsigned() == 0 ? "Creation succeded!"
                : "Creation failed: 0 expected, " + longword.getUnsigned() + " got.");
    }

    public static void testGetBit() {
        longword = new Longword();
        System.out.println(!longword.getBit(0).getValue() ? "GetBit (0) succeded!"
                : "GetBit failed: f expected, " + longword.getBit(0) + " got.");
        longword.setBit(31, new Bit(true));
        System.out.println(longword.getBit(31).getValue() ? "GetBit (31) succeded!"
                : "GetBit failed: t expected, " + longword.getBit(31) + " got.");
        System.out.println(!longword.getBit(15).getValue() ? "GetBit (15) succeded!"
                : "GetBit failed: f expected, " + longword.getBit(15) + " got.");
    }

    public static void testSetBit() { // Tests set bit in three cases
        longword = new Longword();
        longword.setBit(0, new Bit(true));
        System.out.println(longword.getBit(0).getValue() ? "SetBit (0) succeded!"
                : "SetBit failed: t expected, " + longword.getBit(0) + " got.");
        longword.setBit(31, new Bit(false));
        System.out.println(!longword.getBit(31).getValue() ? "SetBit (31) succeded!"
                : "SetBit failed: f expected, " + longword.getBit(31) + " got.");
        longword.setBit(0, new Bit(false));
        System.out.println(!longword.getBit(15).getValue() ? "SetBit (0) succeded!"
                : "SetBit failed: f expected, " + longword.getBit(0) + " got.");
    }

    public static void testLongwordAnd() { // Tests longwordand in three cases
        longword = new Longword();
        Longword second = new Longword();
        second.set(-1);
        longword = longword.longwordAnd(second);
        System.out.println("Binary " + longword);
        System.out.println(longword.getUnsigned() == 0 ? "LongWordAnd succeded!"
                : "LongWordAnd failed: 0 expected, " + longword.getUnsigned() + " got.");
        longword.set(-1);
        longword = longword.longwordAnd(second);
        System.out.println("Binary " + longword);
        System.out.println((long) longword.getUnsigned() == 4294967295L ? "LongWordAnd succeded!"
                : "LongWordAnd failed: 4294967295 expected, " + longword.getUnsigned() + " got.");
        longword = new Longword();
        for (int i = 0; i < 32; i += 5)
            longword.setBit(i, new Bit(true));
        longword = longword.longwordAnd(second);
        System.out.println("Binary " + longword);
        System.out.println((long) longword.getUnsigned() == 2216757314L ? "LongWordAnd succeded!"
                : "LongWordAnd failed: 2216757314 expected, " + longword.getUnsigned() + " got.");
    }

    public static void testLongwordOr() { // Tests longwordor in three cases
        longword = new Longword();
        Longword second = new Longword();
        longword = longword.longwordOr(second);
        System.out.println("Binary " + longword);
        System.out.println(longword.getUnsigned() == 0 ? "LongWordOR succeded!"
                : "LongWordOR failed: 0 expected, " + longword.getUnsigned() + " got.");
        longword.set(-1);
        longword = longword.longwordOr(second);
        System.out.println("Binary " + longword);
        System.out.println((long) longword.getUnsigned() == 4294967295L ? "LongWordOR succeded!"
                : "LongWordOR failed: 4294967295 expected, " + longword.getUnsigned() + " got.");
        longword = new Longword();
        for (int i = 0; i < 32; i += 5)
            longword.setBit(i, new Bit(true));
        longword = longword.longwordOr(second);
        System.out.println("Binary " + longword);
        System.out.println((long) longword.getUnsigned() == 2216757314L ? "LongWordOR succeded!"
                : "LongWordOR failed: 2216757314 expected, " + longword.getUnsigned() + " got.");
    }

    public static void testLongwordXor() { // Tests longwordxor in three cases
        longword = new Longword();
        Longword second = new Longword();
        second.set(-1);
        longword = second.longwordXor(longword);
        System.out.println("Binary " + longword);
        System.out.println(longword.getUnsigned() == 4294967295L ? "LongWordXor succeded!"
                : "LongWordXor failed: 4294967295 expected, " + longword.getUnsigned() + " got.");
        longword.set(-1);
        longword = longword.longwordXor(second);
        System.out.println("Binary " + longword);
        System.out.println((long) longword.getUnsigned() == 0 ? "LongWordXor succeded!"
                : "LongWordXor failed: 0 expected, " + longword.getUnsigned() + " got.");
        longword = new Longword();
        for (int i = 0; i < 32; i += 5)
            longword.setBit(i, new Bit(true));
        longword = longword.longwordXor(second);
        System.out.println("Binary " + longword);
        System.out.println((long) longword.getUnsigned() == 2078209981L ? "LongWordXor succeded!"
                : "LongWordXor failed: 2078209981 expected, " + longword.getUnsigned() + " got.");
    }

    public static void testLeftShift() { // Tests leftshift in 4 cases
        try {
            longword = new Longword();
            longword = longword.leftShift(10);
            System.out.println("Binary " + longword);
            System.out.println(longword.getUnsigned() == 0 ? "LeftShift succeded!"
                    : "LeftShift failed: 0 expected, " + longword.getUnsigned() + " got.");
            longword.set(300000000);
            longword = longword.leftShift(3);
            System.out.println("Binary " + longword);
            System.out.println((long) longword.getUnsigned() == 2400000000L ? "LeftShift succeded!"
                    : "LeftShift failed: 2400000000 expected, " + longword.getUnsigned() + " got.");
            longword.set(-1);
            longword.leftShift(30);
            System.out.println("Binary " + longword);
            System.out.println((long) longword.getUnsigned() == 4294967295L ? "LeftShift succeded!"
                    : "LeftShift failed: 4294967295 expected, " + longword.getUnsigned() + " got.");
            longword.leftShift(-50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testRightShift() { // Tests rightshift in 4 cases
        try {
            longword = new Longword();
            longword = longword.rightShift(10);
            System.out.println("Binary " + longword);
            System.out.println(longword.getUnsigned() == 0 ? "RightShift succeded!"
                    : "RightShift failed: 0 expected, " + longword.getUnsigned() + " got.");
            longword.set(300000000);
            longword = longword.rightShift(3);
            System.out.println("Binary " + longword);
            System.out.println((long) longword.getUnsigned() == 37500000 ? "RightShift succeded!"
                    : "RightShift failed: 37500000 expected, " + longword.getUnsigned() + " got.");
            longword.set(-1);
            longword.rightShift(30);
            System.out.println("Binary " + longword);
            System.out.println((long) longword.getUnsigned() == 4294967295L ? "RightShift succeded!"
                    : "RightShift failed: 4294967295 expected, " + longword.getUnsigned() + " got.");
            longword.rightShift(60);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testSet() { // Tests set in 4 cases
        longword = new Longword();
        longword.set(10);
        System.out.println("Binary " + longword);
        System.out.println(longword.getSigned() == 10 ? "Set (10) succeded!"
                : "Set (10) failed: 10 expected, " + longword.getSigned() + " got.");
        longword.set(-2147483648);
        System.out.println("Binary " + longword);
        System.out.println(longword.getSigned() == -2147483648 ? "Set (-2147483648) succeded!"
                : "Set (-2147483648) failed: -2147483648 expected, " + longword.getSigned() + " got.");
        longword.set(2147483647);
        System.out.println("Binary " + longword);
        System.out.println(longword.getSigned() == 2147483647 ? "Set (2147483647) succeded!"
                : "Set (2147483647) failed: 2147483647 expected, " + longword.getSigned() + " got.");
        longword.set(2147483647 / 2);
        System.out.println("Binary " + longword);
        System.out.println(longword.getSigned() == 2147483647 / 2 ? "Set 1073741823 succeded!"
                : "Set (1073741823) failed: 1073741823 expected, " + longword.getSigned() + " got.");
    }

    public static void testCopy() { // Tests copy in 4 cases
        longword = new Longword();
        Longword second = new Longword();
        longword.set(10);
        second.copy(longword);
        System.out.println("Binary " + longword);
        System.out.println(second.getSigned() == 10 ? "Copy (10) succeded!"
                : "Copy (10) failed: 10 expected, " + longword.getSigned() + " got.");
        longword.set(-2147483648);
        second.copy(longword);
        System.out.println("Binary " + longword);
        System.out.println(second.getSigned() == -2147483648 ? "Copy (-2147483648) succeded!"
                : "Copy (-2147483648) failed: -2147483648 expected, " + longword.getSigned() + " got.");
        longword.set(2147483647);
        second.copy(longword);
        System.out.println("Binary " + longword);
        System.out.println(second.getSigned() == 2147483647 ? "Copy (2147483647) succeded!"
                : "Copy (2147483647) failed: 2147483647 expected, " + longword.getSigned() + " got.");
        longword.set(2147483647 / 2);
        second.copy(longword);
        System.out.println("Binary " + longword);
        System.out.println(second.getSigned() == 2147483647 / 2 ? "Copy 1073741823 succeded!"
                : "Copy (1073741823) failed: 1073741823 expected, " + longword.getSigned() + " got.");
    }

    public static void testGetSigned() { // Tests getSigned in 4 cases
        longword = new Longword();
        longword.set(10);
        System.out.println("Binary " + longword);
        System.out.println(longword.getSigned() == 10 ? "GetSigned (10) succeded!"
                : "GetSigned (10) failed: 10 expected, " + longword.getSigned() + " got.");
        longword.set(-2147483648);
        System.out.println("Binary " + longword);
        System.out.println(longword.getSigned() == -2147483648 ? "GetSigned (-2147483648) succeded!"
                : "GetSigned (-2147483648) failed: -2147483648 expected, " + longword.getSigned() + " got.");
        longword.set(2147483647);
        System.out.println("Binary " + longword);
        System.out.println(longword.getSigned() == 2147483647 ? "GetSigned (2147483647) succeded!"
                : "GetSigned (2147483647) failed: 2147483647 expected, " + longword.getSigned() + " got.");
        longword.set(2147483647 / 2);
        System.out.println("Binary " + longword);
        System.out.println(longword.getSigned() == 2147483647 / 2 ? "GetSigned 1073741823 succeded!"
                : "GetSigned (1073741823) failed: 1073741823 expected, " + longword.getSigned() + " got.");
    }

    public static void testGetUnSigned() { // Tests getUnSigned in 4 cases
        longword = new Longword();
        longword.set(-1);
        System.out.println("Binary " + longword);
        System.out.println(longword.getUnsigned() == 4294967295L ? "GetUnSigned (4294967295) succeded!"
                : "GetUnSigned (4294967295) failed: 4294967295 expected, " + longword.getSigned() + " got.");
        longword.set(-2147483648);
        System.out.println("Binary " + longword);
        System.out.println(longword.getUnsigned() == 2147483648L ? "GetUnSigned (2147483648) succeded!"
                : "GetUnSigned (2147483648) failed: -2147483648 expected, " + longword.getSigned() + " got.");
        longword.set(2147483647);
        System.out.println("Binary " + longword);
        System.out.println(longword.getUnsigned() == 2147483647 ? "GetUnSigned (2147483647) succeded!"
                : "GetUnSigned (2147483647) failed: 2147483647 expected, " + longword.getSigned() + " got.");
        longword.set(2147483647 / 2);
        System.out.println("Binary " + longword);
        System.out.println(longword.getUnsigned() == 2147483647 / 2 ? "GetUnSigned 1073741823 succeded!"
                : "GetUnSigned (1073741823) failed: 1073741823 expected, " + longword.getSigned() + " got.");
    }

    public static void spacer() {
        System.out.println("----------------------");
    }
}