public class PlusToken implements Token {
    public PlusToken() {}

    @Override
    public int hashCode() {
        return 0;
    }
    
    @Override
    public String toString() {
        return "PlusToken";
    }

    @Override
    public boolean equals(Object other) {
        // if (other instanceof PlusToken) {
        //     return true;
        // } else {
        //     return false;
        // }
        return other instanceof PlusToken;
    }
}
