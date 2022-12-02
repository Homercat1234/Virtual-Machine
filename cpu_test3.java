public class cpu_test3 {

    private static String[] convertArray(String[] str) {
        String output = "";
        for (String token : str) {
            output += token;
        }
        output = output.replaceAll("(.{" + "16" + "})", "$1\n").trim(); // Add a new line every 16th char

        return output.split("\n");

    }

    public static void tests() { // Testing the cpu
        Computer cpu = new Computer();

        spacer();
        System.out.println("Program 1");
        spacer();

        String[] words = { // Should output 10
                "mov r1 10",
                "push r1",
                "pop r2",
                "print r2"
        };
        cpu.preload(convertArray(Assembler.assemble(words)));
        cpu.run();

        spacer();
        System.out.println("Program 2");
        spacer();

        cpu = new Computer();

        words = new String[] { // Given
                "call 6",
                "print 0",
                "hlt",
                "print 1",
                "return"
        };

        cpu.preload(convertArray(Assembler.assemble(words)));
        cpu.run();

        spacer();
        System.out.println("Program 3");
        spacer();

        cpu = new Computer();

        words = new String[] { // should be 9
            "move r2 4",
            "move r1 5",
            "pop r3",
            "pop r3",
            "push r1",
            "push r2",
            "move r2 0",
            "move r1 0",
            "call 22",
            "print r3",
            "halt",
            "pop r15",
            "pop r1",
            "pop r2",
            "push r15",
            "add r1 r2 r3",
            "return",
        };

        cpu.preload(convertArray(Assembler.assemble(words)));
        cpu.run();
    }

    public static void spacer() {
        System.out.println("----------------------");
    }
}
