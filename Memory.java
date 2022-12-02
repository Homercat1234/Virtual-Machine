public class Memory { // A class to serve as storage
    private Bit[] memory = new Bit[8192];

    public Memory() {
        for (int i = 0; i < 8192; i++)
            memory[i] = new Bit(false);
    }

    public Longword read(Longword address) throws Exception { // A method that reads a longword at an address
        long index = address.getUnsigned();
        if (index > 1023 || index < 0)  // If index is out of bounds throw an execption.
            throw new Exception("Error, address equals " + index + ", which is out of bounds");
        Longword results = new Longword();
        for (long i = index * 8, x = 0; x < 32 && i < 8192; i++, x++) // Gets the starting index and then loops 31 times.
            results.setBit((int) x, new Bit(memory[(int) i].getValue()));
        return results;
    }

    public void write(Longword address, Longword value) throws Exception { // A method that writes to a longword at an
                                                                           // addresss
        long index = address.getUnsigned();
        if (index > 1023 || index < 0) // If index is out of bounds throw an execption.
            throw new Exception("Error, address equals " + index + ", which is out of bounds"); // Gets the starting
                                                                                                // index and then loops
                                                                                                // 31 times.
        for (long i = index * 8, x = 0; x < 32 && i < 8192; i++, x++)
            memory[(int) i] = new Bit(value.getBit((int) x).getValue());
    }
}