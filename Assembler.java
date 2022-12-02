import java.util.ArrayList;

enum Instruction { // An enum that contains the opcodes binaries
    and("1000"), or("1001"), xor("1010"), not("1011"), leftShift("1100"),
    rightShift("1101"), add("1110"), subtract("1111"), multiply("0111"),
    halt("0000"), interrupt("0010"), move("0001");

    private String token;

    public String getToken() {
        return this.token;
    }

    private Instruction(String instruction) {
        this.token = instruction;
    }
}

public class Assembler {

    public static String[] assemble(String[] input) { // Assembles an input
        try {
            return (parse(lexicalAnalyzer(input)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    private static String[] lexicalAnalyzer(String[] tokens) {
        ArrayList<String> tokenList = new ArrayList<String>(); // Creating an array list because it has dynamic size
        for (String token : tokens) { // Loop through every index in tokens
            String currentToken = new String();
            boolean whitespace = false, comment = false;
            for (int i = 0; i < token.length(); i++) { // Loop through every character in token
                if (!comment) {
                    if (token.charAt(i) == ';') { // If we are entering a comment
                        comment = true;
                        if (!whitespace) { // If currenttoken is not empty
                            tokenList.add(currentToken);
                        }
                    } else if (whitespace) {
                        if (!Character.isWhitespace(token.charAt(i))) {
                            whitespace = false;
                            currentToken = new String();
                            currentToken += token.charAt(i);
                            if (i == token.length() - 1) {
                                // Add the current token to the list if we are at the end
                                tokenList.add(currentToken);
                            }
                        }
                    } else {
                        if (Character.isWhitespace(token.charAt(i))) {
                            // If the charater is a whitespace add it to the list
                            tokenList.add(currentToken);
                            currentToken = new String();
                            whitespace = true;
                        } else {
                            if (i == token.length() - 1) {
                                // If it is the end of token add it to the list
                                currentToken += token.charAt(i);
                                tokenList.add(currentToken);
                            } else {
                                currentToken += token.charAt(i);
                            }
                        }
                    }
                }
            }
        }
        String[] tokenArray = new String[tokenList.size()]; // Convert the arraylist to a correctly sized string array.
        for (int i = 0; i < tokenList.size(); i++) {
            tokenArray[i] = tokenList.get(i);
        }
        return tokenArray;
    }

    private static String[] parse(String[] tokens) {
        ArrayList<String> tokenList = new ArrayList<String>(); // Create a dynamically
        try {
            for (int i = 0; i < tokens.length;) {
                String[] temp = isValidOperator(tokens, tokens[i], i);
                for (String token : temp) {
                    tokenList.add(token);
                }
                i += temp.length; // Skip the already validated tokens
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] tokenArray = new String[tokenList.size()];
        for (int i = 0; i < tokenList.size(); i++) { // Convert the list to a string array
            tokenArray[i] = tokenList.get(i);
        }
        return tokenArray;
    }

    private static String[] isValidOperator(String[] tokens, String token, int tokenIndex) throws Exception {
        try { // Detect operation
            switch (token.toLowerCase()) {
                case "and":
                case "or":
                case "xor":
                case "not":
                case "subtract":
                case "sub":
                case "multiply":
                case "imul":
                case "mul":
                case "mult":
                case "add":
                case "leftshift":
                case "shl":
                case "rightshift":
                case "shr":
                    return normalOperation(tokens, token, tokenIndex);

                case "halt":
                case "hlt":
                case "move":
                case "mov":
                case "interrupt":
                case "print":
                    return specialInstruction(tokens, token, tokenIndex);

                case "jump":
                case "jmp":
                case "compare":
                case "cmp":
                case "branch":
                case "b":
                case "call":
                case "return":
                case "pop":
                case "push":
                    return otherInstruction(tokens, token, tokenIndex);

                default:
                    throw new Exception("Invalid operator");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private static String getBinary(int number, int bits) { // Convert an in into binary of length bits.
        String binary = Integer.toBinaryString(number);
        if (binary.length() > bits) { // If the length of binary is greater than bits get the bottom bitth bits.
            binary = binary.substring(binary.length() - bits);
        } else if (binary.length() < bits) { // Else if the length is less than bits than pad the output with 0s.
            String temp = "";
            for (int x = 0; x < bits - binary.length(); x++)
                temp += "0";
            binary = temp + binary;
        }
        return binary;
    }

    // A helper method that validates a register and returns the register as an int.
    // If the register is invalid throws an exectpion.
    public static int validateRegister(String tokens[], int index) throws Exception {
        if (tokens[index].toLowerCase().charAt(0) != 'r') // Testing to see if it the next token is a
            // register
            throw new Exception("Invalid register");

        String temp = "";
        for (int i = 1; i < tokens[index].length(); i++) { // Convert the number part of the register to a string
            temp += tokens[index].charAt(i);
        }

        int register = Integer.parseInt(temp); // Convert that string into an int so that it can be converted into a
        // binary later

        if (register < 0 || register > 15)
            throw new Exception("Register value not accepted");

        return register;
    }

    // If a normal operation was detected
    private static String[] normalOperation(String[] tokens, String token, int tokenIndex) throws Exception {
        if (tokenIndex + 3 >= tokens.length) // Validating the lenght of input
            throw new Exception("Invalid input");
        String output[] = new String[4];

        switch (token.toLowerCase()) { // Getting the opcode
            case "add":
                output[0] = Instruction.add.getToken();
                break;
            case "subtract":
            case "sub":
                output[0] = Instruction.subtract.getToken();
                break;
            case "multiply":
            case "mul":
            case "imul":
            case "mult":
                output[0] = Instruction.multiply.getToken();
                break;
            case "and":
                output[0] = Instruction.and.getToken();
                break;
            case "not":
                output[0] = Instruction.not.getToken();
                break;
            case "or":
                output[0] = Instruction.or.getToken();
                break;
            case "xor":
                output[0] = Instruction.xor.getToken();
                break;
            case "leftshift":
            case "shl":
                output[0] = Instruction.leftShift.getToken();
                break;
            case "rightshift":
            case "shr":
                output[0] = Instruction.rightShift.getToken();
                break;
        }

        try {
            for (int i = 1; i < 4; i++) { // Loop through the remaining tokens which will be registers
                output[i] = getBinary(validateRegister(tokens, tokenIndex + i), 4); // Get the binary value of the
                                                                                    // register
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return output;
    }

    // A helper method for move instruction
    private static String[] moveInstruction(String[] tokens, String token, int tokenIndex) throws Exception {
        String[] output = new String[3];
        try {
            if (tokenIndex + 2 >= tokens.length) // Validate the length of the token array
                throw new Exception("Invalid input");
            if (tokens[tokenIndex + 1].toLowerCase().charAt(0) != 'r')
                throw new Exception("Invalid register");

            output[0] = Instruction.move.getToken();
            output[1] = getBinary(validateRegister(tokens, tokenIndex + 1), 4); // Get the binary value of the register

            if (Integer.parseInt(tokens[tokenIndex + 2]) < -128 || Integer.parseInt(tokens[tokenIndex + 2]) > 127)
                throw new Exception("Int out of bounds");

            output[2] = getBinary(Integer.parseInt(tokens[tokenIndex + 2]), 8); // Get the binary of the 8 bit value

        } catch (Exception e) {
            throw new Exception(e);
        }
        return output;
    }

    private static String[] interruptInstruction(String[] tokens, String token, int tokenIndex) throws Exception {
        try {
            String[] output = new String[2];
            output[0] = Instruction.interrupt.getToken();
            if (tokens[tokenIndex + 1].toLowerCase().charAt(0) == 'r'
                    && Character.isDigit(tokens[tokenIndex + 1].charAt(1))) {
                output[1] = getBinary(0, 4) + getBinary(validateRegister(tokens, tokenIndex + 1), 4) + getBinary(2, 4);
            } else if (tokens[tokenIndex + 1].equals("mem") || tokens[tokenIndex + 1].equals("memory")) {
                output[1] = getBinary(1, 12);
            } else if (tokens[tokenIndex + 1].equals("regs") || tokens[tokenIndex + 1].equals("registers")) {
                output[1] = getBinary(0, 12);
            } else if (!(Integer.parseInt(tokens[tokenIndex + 1]) == 0
                    || Integer.parseInt(tokens[tokenIndex + 1]) == 1)) {// Validate the instruction
                throw new Exception("Invalid number");
            } else {
                output[1] = getBinary(Integer.parseInt(tokens[tokenIndex + 1]), 12);
            }
            return output;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private static String[] specialInstruction(String[] tokens, String token, int tokenIndex) throws Exception {
        try {
            switch (token.toLowerCase()) { // Detect the correct operation
                case "move":
                case "mov":
                    return moveInstruction(tokens, token, tokenIndex);

                case "interrupt":
                case "print":
                    return interruptInstruction(tokens, token, tokenIndex);

                case "halt":
                case "hlt":
                    String[] output = new String[1];
                    output[0] = getBinary(0, 16);
                    return output;

                default:
                    throw new Exception("Invalid operator");

            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private static String[] jumpInstruction(String[] tokens, String token, int tokenIndex) throws Exception {
        String[] output = new String[2];
        try {
            if (tokenIndex + 1 >= tokens.length) // Validate the length of the token array
                throw new Exception("Invalid input");
            output[0] = "0011";
            output[1] = getBinary(Integer.parseInt(tokens[tokenIndex + 1]), 12);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return output;
    }

    private static String[] compareInstruction(String[] tokens, String token, int tokenIndex) throws Exception {
        String[] output = new String[3];
        try {
            if (tokenIndex + 2 >= tokens.length) // Validate the length of the token array
                throw new Exception("Invalid input");
            output[0] = "01000000";
            output[1] = getBinary(validateRegister(tokens, tokenIndex + 1), 4); // Get the binary value of the register
            output[2] = getBinary(validateRegister(tokens, tokenIndex + 2), 4); // Get the binary value of the register
        } catch (Exception e) {
            throw new Exception(e);
        }
        return output;
    }

    private static String[] branchInstruction(String[] tokens, String token, int tokenIndex) throws Exception {
        String[] output = new String[3];
        try {
            if (tokenIndex + 2 >= tokens.length) // Validate the length of the token array
                throw new Exception("Invalid input");
            output[0] = "0101";
            switch (tokens[tokenIndex + 1]) { // Get the operator
                case "==":
                    output[1] = "00";
                    break;
                case ">":
                    output[1] = "10";
                    break;
                case ">=":
                case "=>":
                    output[1] = "11";
                    break;
                case "=!":
                case "!=":
                    output[1] = "01";
                    break;
                default:
                    throw new Exception("Invalid token");
            }
            output[2] = getBinary(Integer.parseInt(tokens[tokenIndex + 2]), 10); // Get the binary value of the register
        } catch (Exception e) {
            throw new Exception(e);
        }
        return output;
    }

    private static String[] memoryInstruction(String tokens[], String token, int tokenIndex) throws Exception {
        try {
            String output[];
            switch (token.toLowerCase()) { // Detect the correct operation
                case "return":
                    return new String[] { "0110110000000000" };

                case "call":
                    output = new String[2];
                    output[0] = "011010";
                    output[1] = getBinary(Integer.parseInt(tokens[tokenIndex + 1]), 10);
                    return output;

                case "pop":
                    output = new String[2];
                    output[0] = "011001000000";
                    output[1] = getBinary(validateRegister(tokens, tokenIndex + 1), 4); // Get the binary value of the
                                                                                        // register
                    return output;

                case "push":
                    output = new String[2];
                    output[0] = "011000000000";
                    output[1] = getBinary(validateRegister(tokens, tokenIndex + 1), 4); // Get the binary value of the
                                                                                        // register
                    return output;

                default:
                    throw new Exception("Invalid token");

            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private static String[] otherInstruction(String[] tokens, String token, int tokenIndex) throws Exception {
        try {
            switch (token.toLowerCase()) { // Detect the correct operation
                case "jump":
                case "jmp":
                    return jumpInstruction(tokens, token, tokenIndex);

                case "compare":
                case "cmp":
                    return compareInstruction(tokens, token, tokenIndex);

                case "branch":
                case "b":
                    return branchInstruction(tokens, token, tokenIndex);

                case "call":
                case "return":
                case "pop":
                case "push":
                    return memoryInstruction(tokens, token, tokenIndex);

                default:
                    throw new Exception("Invalid operator");

            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}