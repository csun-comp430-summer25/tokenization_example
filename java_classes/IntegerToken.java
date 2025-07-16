public class IntegerToken implements Token {
    public final int value;

    public IntegerToken(final int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        // if (other instanceof IntegerToken) {
        //     IntegerToken otherInt = (IntegerToken)other;
        //     return value == otherInt.value;
        // }
        if (other instanceof IntegerToken otherInt) {
            return value == otherInt.value;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "IntegerToken(" + value + ")";
    }
}
