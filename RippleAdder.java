public class RippleAdder {
    public static Longword add(Longword a, Longword b) { // Adds two longwords
        Bit carry = new Bit(false);
        Longword sum = new Longword();
        for (int i = 31; i >= 0; i--) {
            sum.setBit(i, (a.getBit(i).bitXor(b.getBit(i)).bitXor(carry)));
            carry = a.getBit(i).bitAnd(b.getBit(i)).bitOr(carry.bitAnd(a.getBit(i).bitXor(b.getBit(i))));
        }
        return sum;
    }

    public static Longword subtract(Longword a, Longword b) { // Subtracts two longwords by take the complement of b and
                                                              // adding.
        return add(a, add(b.longwordNot(), new Longword(1)));
    }
}
