public class MinusToken implements Token {
    public MinusToken() {}

    @Override
    public int hashCode() {
        return 1;
    }
    
    @Override
    public String toString() {
        return "MinusToken";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof MinusToken;
    }
}
