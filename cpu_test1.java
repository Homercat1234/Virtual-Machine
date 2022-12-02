public class cpu_test1 {
    public static void runTests() {
        tests();
    }

    public static void tests() { // Testing the cpu
        Computer cpu = new Computer();

        String[] words = { // Testing memory and register saving
                "0001 1111 1000 0001", // Move
                "0010 0000 0000 0001", // Print memory
                "0010 0000 0000 0000", // Print registers
        };
        cpu.preload(words);
        cpu.run();

        spacer();

        cpu = new Computer();

        words = new String[] { // Testing addition
                "0001 1111 1000 0001", // Move
                "0001 1110 0000 0011", // Move
                "0010 0000 0000 0001", // Print memory
                "1110 1110 1111 1010", // Add
                "0010 0000 0000 0000", // Print all retgisters
                "0000 0000 0000 0000", // Halt

        };
        cpu.preload(words);
        cpu.run();

        spacer();

        cpu = new Computer();

        words = new String[] { // More addtion tests
                "0001 1111 0011 0010", // Move
                "0001 1110 0001 0100", // Move
                "0010 0000 0000 0001", // Print memory
                "1110 1110 1111 1101", // Add
                "0010 0000 0000 0000", // Print registers

        };
        cpu.preload(words);
        cpu.run();

        spacer();

        cpu = new Computer();

        words = new String[] { // Chaining instructions test
                "0001 1111 0000 0010", // Move
                "0001 1110 0000 0100", // Move
                "0010 0000 0000 0001", // Print memory
                "0111 1110 1111 0001", // Multiply
                "1110 0001 1111 0010", // Add
                "0010 0000 0000 0000", // Print registers

        };
        cpu.preload(words);
        cpu.run();

        // Tests produce desired results
    }

    public static void spacer() {
        System.out.println("----------------------");
    }
}
