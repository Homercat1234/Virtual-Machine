public class Computer { // Setup for computer class.
    private Bit halted = new Bit(false);
    private Memory memory = new Memory();
    private Longword[] registers = new Longword[16];
    private Bit[] compared = new Bit[2];
    // Temporary storage for all non-register stored longwords needed during the
    // course of the operation of computer.
    private Longword op1 = new Longword(), op2 = new Longword(), result = new Longword(),
            currentInstruction = new Longword(), pc = new Longword(), stackPointer = new Longword(1020);

    public Computer() {
        for (int i = 0; i < 16; i++)
            registers[i] = new Longword();
    }

    public void run() { // A method that runs the computer
        try {
            while (!halted.getValue()) {
                fetch();
                decode();
                execute();
                store();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void preload(String[] words) { // A method to preload memroy
        try {
            for (int i = 0, x = 0; i < words.length; i++, x += 2) // Write one longword per two address as each are 16
                                                                  // bits and a longword is 32 bits
                memory.write(new Longword(x), new Longword(words[i]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetch() { // A method that fetches the instruction
        try {
            currentInstruction = memory.read(pc);
            pc = RippleAdder.add(pc, new Longword(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decode() { // A method that decodes the instructions
        try {
            Bit[] opcode = new Bit[4];
            Longword operation = currentInstruction.getBits(4, 0);
            for (int i = 31; i > 27; i--) // Get the opcode to check it for interrupt or move
                opcode[i - 28] = operation.getBit(i);
            if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(false)) && opcode[2].equals(new Bit(true))
                    && opcode[3].equals(new Bit(false))) // Opcode for interrupt
                return;

            if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(false)) && opcode[2].equals(new Bit(false))
                    && opcode[3].equals(new Bit(true))) // Opcode for move
                return;

            if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(true)) && opcode[2].equals(new Bit(true))
                    && opcode[3].equals(new Bit(false))) // Opcode for stack
                return;

            op1.copy(registers[(int) currentInstruction.getBits(4, 1).getUnsigned()]);
            op2.copy(registers[(int) currentInstruction.getBits(4, 2).getUnsigned()]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() { // A method that executes the giving instruction.
        try {
            Bit[] opcode = new Bit[4];
            Longword operation = currentInstruction.getBits(4, 0);
            for (int i = 31; i > 27; i--) // Converting type Longword to type Bit[] so that it can be passed into the
                                          // ALU
                opcode[i - 28] = operation.getBit(i);

            if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(false)) && opcode[2].equals(new Bit(false))
                    && opcode[3].equals(new Bit(false))) // Opcode for halt
                halted = new Bit(true);

            else if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(false))
                    && opcode[2].equals(new Bit(false))
                    && opcode[3].equals(new Bit(true))) { // Opcode for move
                result = new Longword(currentInstruction.getBits(8, 1).getSigned());
                if (result.getBit(24).getValue() == true) // If it's negative, extend the value
                    for (int i = 0; i < 24; i++)
                        result.setBit(i, new Bit(true));
            }

            else if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(true))
                    && opcode[2].equals(new Bit(false))
                    && opcode[3].equals(new Bit(false))) {
                // If the opcode = 0100 then compare
                if ((registers[currentInstruction.getBits(4, 2).getSigned()].getUnsigned()
                        - registers[currentInstruction.getBits(4, 3).getSigned()].getUnsigned() > 0)) { // Greater than
                                                                                                        // 01
                    compared[0] = new Bit(false);
                    compared[1] = new Bit(true);
                } else if ((registers[currentInstruction.getBits(4, 2).getSigned()].getUnsigned()
                        - registers[currentInstruction.getBits(4, 3).getSigned()].getUnsigned() < 0)) { // Less than 11
                    compared[0] = new Bit(true);
                    compared[1] = new Bit(true);
                } else { // Equal to 00
                    compared[0] = new Bit(false);
                    compared[1] = new Bit(false);
                }

            }

            else if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(true))
                    && opcode[2].equals(new Bit(false))
                    && opcode[3].equals(new Bit(true))) {
                // If the opcode = 0101 then branch
                // compared = 01 = greater than
                // compared = 00 = equal
                // compared = 11 = less than
                if (currentInstruction.getBits(2, 2).getBit(31).equals(new Bit(true))) { // _1
                    if (currentInstruction.getBits(2, 2).getBit(30).equals(new Bit(true))) {
                        // 11 | Greater than or equal | OPCODE
                        if ((compared[0].equals(new Bit(false)) && compared[1].equals(new Bit(true)))
                                || (compared[0].equals(new Bit(false)) && compared[1].equals(new Bit(false)))) {
                            // Compared == Greater than or equal
                            compared[0] = new Bit(true);
                            compared[1] = new Bit(false);
                        }
                    } else { // 01 | Not equal | OPCODE
                        if ((compared[0].equals(new Bit(false)) && compared[1].equals(new Bit(true)))
                                || (compared[0].equals(new Bit(true)) && compared[1].equals(new Bit(true)))) {
                            // Compared == Greater than or Less than
                            compared[0] = new Bit(true);
                            compared[1] = new Bit(false);
                        }
                    }
                } else { // _0
                    if (currentInstruction.getBits(2, 2).getBit(30).equals(new Bit(true))) {
                        // 10 | Geater than | OPCODE
                        if (compared[0].equals(new Bit(false)) && compared[1].equals(new Bit(true))) {
                            // Compared == Greater than
                            compared[0] = new Bit(true);
                            compared[1] = new Bit(false);
                        }
                    } else { // 00 | Equals | OPCODE
                        if (!(compared[0].equals(new Bit(true)) && compared[1].equals(new Bit(true)))) {
                            // Compared == equals
                            compared[0] = new Bit(true);
                            compared[1] = new Bit(false);
                        }
                    }
                }
            }

            else if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(false))
                    && opcode[2].equals(new Bit(true))
                    && opcode[3].equals(new Bit(false))) // Opcode for interrupt
                if (!currentInstruction.getBit(14).getValue() && currentInstruction.getBit(15).getValue()) // Opcode for
                                                                                                           // print
                                                                                                           // memory
                    for (int i = 0; i/4 < 256; i +=4) {
                        Longword current = memory.read(new Longword(i));
                        System.out.println("Longword #" + i/4 + ": " + current);
                    }
                else if (!currentInstruction.getBit(14).getValue() && !currentInstruction.getBit(15).getValue()) // Opcode
                                                                                                                 // for
                                                                                                                 // print
                                                                                                                 // registers
                    for (int i = 0; i < 16; i++)
                        System.out.println("Longword #" + i + ": " + registers[i]);
                else { // Custom opcode to print a register
                    System.out.println(registers[(int) currentInstruction.getBits(4, 2).getUnsigned()].getUnsigned());
                }
            else if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(true))
                    && opcode[2].equals(new Bit(true))
                    && opcode[3].equals(new Bit(false))) { // Opcode for stack
                if (currentInstruction.getBits(2, 2).getUnsigned() == 0) { // Push
                    memory.write(stackPointer, registers[(int) currentInstruction.getBits(4, 3).getUnsigned()]);
                    stackPointer = RippleAdder.add(stackPointer, new Longword(4));
                } else if (currentInstruction.getBits(2, 2).getUnsigned() == 2) { // Call
                    memory.write(stackPointer, pc);
                    stackPointer = RippleAdder.add(stackPointer, new Longword(4));
                    pc = currentInstruction.leftShift(6).rightShift(22);
                } else if (currentInstruction.getBits(2, 2).getUnsigned() == 3) { // Return
                    stackPointer = RippleAdder.add(new Longword(-4), stackPointer);
                    pc = memory.read(stackPointer);
                } else { // Pop
                    stackPointer = RippleAdder.add(new Longword(-4), stackPointer);
                    registers[(int) currentInstruction.getBits(4, 3).getUnsigned()] = memory.read(stackPointer);
                }

            } else { // ALU opcodes
                result = ALU.doOp(opcode, op1, op2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void store() throws Exception { // A method that stores the result into a register.
        try {
            if (halted.getValue())
                return;
            Bit[] opcode = new Bit[4];
            Longword operation = currentInstruction.getBits(4, 0);
            for (int i = 31; i > 27; i--) // Get the opcode to check it for interrupt or move
                opcode[i - 28] = operation.getBit(i);

            if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(false)) && opcode[2].equals(new Bit(true))
                    && opcode[3].equals(new Bit(false))
                    || (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(true))
                            && opcode[2].equals(new Bit(false))
                            && opcode[3].equals(new Bit(false)))
                    || opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(true))
                            && opcode[2].equals(new Bit(true))
                            && opcode[3].equals(new Bit(false)))
                // Do nothing if opcode = 0010 = intererupt or opcode = 0100, compare or call, pop, push.
                return;
            else if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(false))
                    && opcode[2].equals(new Bit(false))
                    && opcode[3].equals(new Bit(true)))// If opcode = 0001 = move then copy result into regsiters at
                                                       // current instruction at 2nd nibble's value
                registers[(int) currentInstruction.getBits(4, 1).getUnsigned()] = result.duplicate();
            else if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(false))
                    && opcode[2].equals(new Bit(true))
                    && opcode[3].equals(new Bit(true))) { // If the opcode = 0011 then jump
                if (currentInstruction.leftShift(4).rightShift(20).getUnsigned() < 0)
                    throw new Exception("Memory address is negative");
                pc = new Longword((int) currentInstruction.leftShift(4).rightShift(20).getUnsigned());
            } else if (opcode[0].equals(new Bit(false)) && opcode[1].equals(new Bit(true))
                    && opcode[2].equals(new Bit(false))
                    && opcode[3].equals(new Bit(true))) { // If the opcode = 0101 then branch
                if (compared[0].equals(new Bit(true)) && compared[1].equals(new Bit(false))) { // If the branch
                                                                                               // condition is met
                    String ci = currentInstruction.leftShift(6).rightShift(22).toString(); // Get the correct bits
                    // Remove all ,s spaces ts and fs from the string and then get the bottom 10
                    // charaters
                    ci = ci.replaceAll("[ ,]", "").replaceAll("f", "0").replaceAll("t", "1").substring(22, 32);
                    if (ci.charAt(0) == '1') {
                        for (int i = 0; i < 22; i++)
                            ci = "1" + ci;
                    }
                    pc = new Longword(pc.getSigned() + (int) Long.parseLong(ci, 2));
                }
            } else // Else copy result into regsiters at current instruction at 4th nibble's value
                registers[(int) currentInstruction.getBits(4, 3).getUnsigned()] = result.duplicate();
        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }
}