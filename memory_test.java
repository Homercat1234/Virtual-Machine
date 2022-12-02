public class memory_test { // Tests memory class
    static Memory memory = new Memory();

    public static void runTests() {
        spacer();
        test();
        spacer();
    }

    public static void test() { // Tests read/write in 6 cases
        try {
            Longword address = new Longword();
            memory.write(address, new Longword(160012333));
            System.out.println(memory.read(address).getUnsigned() == 160012333 ? "Write 160012333,0 succeeded!"
                    : "Write 160012333,0 failed:, " + memory.read(address).getUnsigned() + " got.");

            address.set(255);
            memory.write(address, new Longword(-10023241));
            System.out.println(memory.read(address).getSigned() == -10023241 ? "Write -10023241,255 succeeded!"
                    : "Write -10023241,255 failed:, " + memory.read(address).getSigned() + " got.");

            address.set(5);
            memory.write(address, new Longword(10));
            System.out.println(memory.read(address).getSigned() == 10 ? "Write 10,5 succeeded!"
                    : "Write 10,5 failed:, " + memory.read(address).getSigned() + " got.");

            address.set(5);
            memory.write(address, new Longword(0));
            System.out.println(memory.read(address).getSigned() == 0 ? "Write 0,5 succeeded!"
                    : "Write 0,5 failed:, " + memory.read(address).getSigned() + " got.");

            address.set(4);
            memory.write(address, new Longword(801231231));
            System.out.println(memory.read(address).getSigned() == 801231231 ? "Write 801231231,4 succeeded!"
                    : "Write 801231231,4 failed:, " + memory.read(address).getSigned() + " got.");

            address.set(2556);
            memory.write(address, new Longword(801231231));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void spacer() {
        System.out.println("----------------------");
    }
}
