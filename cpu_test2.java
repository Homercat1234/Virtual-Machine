public class cpu_test2 {

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

        String[] words = { // Testing branch if not equals. Assemble code is in Assemble tests
                "move R0 10",
                "move r1 15",
                "move r2 1",
                "add r3 r2 r3",
                "add r0 r2 r0",
                "interrupt 0",
                "compare r1 r0",
                "branch != -10",
                "interrupt 0",
        };
        cpu.preload(convertArray(Assembler.assemble(words)));
        cpu.run();

        spacer();
        System.out.println("Program 2");
        spacer();

        cpu = new Computer();
        words = new String[] { // Testing branch if equals, jump, branch >= forward. Stops at 16, so correct.
                               // Assemble code is in
                               // Assemble tests
                "jmp 4",
                "halt",
                "move r1 5",
                "move r2 5",
                "compare r1 r2",
                "branch == 4",
                "halt",
                "print regs",
                "move R0 10",
                "move r1 15",
                "move r2 1",
                "add r3 r2 r3",
                "add r0 r2 r0",
                "print regs",
                "cmp r1 r0",
                "branch >= -10",
                "interrupt 0",
        };
        cpu.preload(convertArray(Assembler.assemble(words)));
        cpu.run();

        spacer();
        System.out.println("Program 3");
        spacer();
        cpu = new Computer();

        words = new String[] { // Testing branch >. Stops at 15, so correct. Assemble code is in Assemble tests
                "interrupt 0",
                "move R0 10",
                "move r1 15",
                "move r2 1",
                "add r3 r2 r3",
                "add r0 r2 r0",
                "interrupt 0",
                "compare r1 r0",
                "branch > -10",
                "interrupt 0",
        };

        cpu.preload(convertArray(Assembler.assemble(words)));
        cpu.run();

        spacer();
        System.out.println("Fib");
        spacer();
        cpu = new Computer();

        words = new String[] { // First 20 numbers of fib. Assembly is in assembly test
                "mov r0 2 ; Program counter",
                "mov r1 0 ; N1",
                "mov r2 1 ; N2",
                "mov r4 20 ; Stop",
                "mov r5 1 ; # of steps each iteration",
                "print r1",
                "add r1 r2 r3 ; N1 + N2 = N3",
                "add r2 r6 r1 ; Copy N2 to N1",
                "add r3 r6 r2 ; Copy N3 to N2",
                "add r5 r0 r0 ; Increase the program counter",
                "print r1",
                "cmp r4 r0",
                "b >= -14 ; Loop while Program counter >= Stop"
        };

        cpu.preload(convertArray(Assembler.assemble(words)));
        cpu.run();
    }

    public static void spacer() {
        System.out.println("----------------------");
    }
}
