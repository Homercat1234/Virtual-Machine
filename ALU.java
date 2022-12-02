public class ALU { // A class that serves as a calculator of llongwords
    public static Longword doOp(Bit[] operation, Longword a, Longword b) {
        if (operation[0].getValue()) { // 1 _ _ _
            if (operation[1].getValue()) { // 1 1 _ _
                if (operation[2].getValue()) { // 1 1 1 _
                    if (operation[3].getValue()) { // 1 1 1 1
                        return RippleAdder.subtract(a, b);
                    } else { // 1 1 1 0
                        return RippleAdder.add(a, b);
                    }
                } else { // 1 1 0 _
                    try {
                        if (operation[3].getValue()) { // 1 1 0 1
                            return a.rightShift(b.longwordAnd(new Longword(31)).getSigned());
                        } else { // 1 1 0 0
                            return a.leftShift(b.longwordAnd(new Longword(31)).getSigned());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else { // 1 0 _ _
                if (operation[2].getValue()) { // 1 0 1 _
                    if (operation[3].getValue()) { // 1 0 1 1
                        return a.longwordNot();
                    } else { // 1 0 1 0
                        return a.longwordXor(b);
                    }
                } else { // 1 0 0 _
                    if (operation[3].getValue()) { // 1 0 0 1
                        return a.longwordOr(b);
                    } else { // 1 0 0 0
                        return a.longwordAnd(b);
                    }
                }
            }
        } else { // 0 1 1 1
            return Multiplier.multiply(a, b);
        }
        return new Longword();
    }
}