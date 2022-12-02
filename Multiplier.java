public class Multiplier { // A class that multiplies two longwords
    public static Longword multiply(Longword a, Longword b) {
        Longword product = new Longword();
        for (int i = 31; i >= 0; i--) {
            if (a.getBit(i).getValue()) // If a[i] == true then add b shifted by 31-i to product as shifted copies of b
                                        // are needed
                try {
                    product = RippleAdder.add(b.leftShift(31 - i), product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return product;
    }
}