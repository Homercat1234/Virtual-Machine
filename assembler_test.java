public class assembler_test {
    public static void runTests() {
        testNormalOp();
        testSpecialInstruction();
    }

    private static String display(String[] str, String[] cases) {
        String output = "";
        for (String token : str) {
            output += token;
        }
        output = output.replaceAll("(.{" + "4" + "})", "$1 ").trim(); // Add spaces every 4th char
        output = output.replaceAll("(.{" + "20" + "})", "$1\n").trim(); // Add a new line every 20th char

        String[] spilt = output.split("\n");
        output = new String();
        for (int i = 0; i < spilt.length; i++) { // Add the input
            output += cases[i] + ": " + spilt[i];
            if (i != spilt.length - 1)
                output += "\n";
        }
        return output;
    }

    public static void testNormalOp() { // Testing normal operation (logical operators and math)
        String[] testCases = { "add r1 r2 r4", "add r15 r11 r2" }; // Testing add
        spacer();
        System.out.println("Add");
        spacer();
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[2]; // Testing subtract
        spacer();
        System.out.println("Subtract");
        spacer();
        testCases[0] = "subtract r1           r6 r4";
        testCases[1] = "subtract r8 r11 r2";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[2]; // Testing multiply
        spacer();
        System.out.println("Multiply");
        spacer();
        testCases[0] = "Multiply r15 r10 r14";
        testCases[1] = "MULTIPLy r0 r11 r2";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[2]; // Testing leftshift
        spacer();
        System.out.println("leftshift");
        spacer();
        testCases[0] = "leftshift r1\nr7 r8";
        testCases[1] = "leftshift R8 r13 r9";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[2]; // Testing rightshift
        spacer();
        System.out.println("rightshift");
        spacer();
        testCases[0] = "rightshift r11 r10 r10";
        testCases[1] = "RigHtSHIft r7 r0 r1";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[2]; // Testing and
        spacer();
        System.out.println("and");
        spacer();
        testCases[0] = "And r15 r15 r15";
        testCases[1] = "and r1 R2 r3";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[2]; // Testing or
        spacer();
        System.out.println("or");
        spacer();
        testCases[0] = "or r10 r12 r15";
        testCases[1] = "OR r11 r12 r13";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[2]; // Testing xor
        spacer();
        System.out.println("xor");
        spacer();
        testCases[0] = "xor r5 R6 r3";
        testCases[1] = "xOR R10 r9 R8";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[2]; // Testing not
        spacer();
        System.out.println("not");
        spacer();
        testCases[0] = "nOt r15 R6 r3";
        testCases[1] = "not R10 r4 R8";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[1]; // test Invalid Opcode
        spacer();
        System.out.println("Invalid Opcode");
        spacer();
        testCases[0] = "InvalidToken r15 R6 r3";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[1]; // test Invalid Register
        spacer();
        System.out.println("Invalid Register");
        spacer();
        testCases[0] = "not r16";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[1]; // test Invalid Register
        spacer();
        System.out.println("Invalid Input");
        spacer();
        testCases[0] = "not r14";
        System.out.println(display(Assembler.assemble(testCases), testCases));
    }

    public static void testSpecialInstruction() { // Testing a special instruction
        String[] testCases = { "Move r2 -100", "move R10 120" }; // Testing Move
        spacer();
        System.out.println("Move");
        spacer();
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[2]; // Testing interrupt
        spacer();
        System.out.println("interrupt");
        spacer();
        testCases[0] = "interrupt 0";
        testCases[1] = "inTerrupt 1";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[1]; // Testing halt
        spacer();
        System.out.println("halt");
        spacer();
        testCases[0] = "halt";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[1]; // Testing errors
        spacer();
        System.out.println("Invalid input");
        spacer();
        testCases[0] = "interrupt 10";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[2]; // Testing jump
        spacer();
        System.out.println("Jump");
        spacer();
        testCases[0] = "jump 10";
        testCases[1] = "J 80";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[2]; // Testing compare
        spacer();
        System.out.println("Compare");
        spacer();
        testCases[0] = "cmp R10 r5";
        testCases[1] = "compare R0 R14";
        System.out.println(display(Assembler.assemble(testCases), testCases));

        testCases = new String[4]; // Testing branch
        spacer();
        System.out.println("Branch");
        spacer();
        testCases[0] = "branch > -80";
        testCases[1] = "b == 180";
        testCases[2] = "branch != 185";
        testCases[3] = "branch => -790";
        System.out.println(display(Assembler.assemble(testCases), testCases));

    }

    public static void spacer() {
        System.out.println("----------------------");
    }
}