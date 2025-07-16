public class IdentifierToken implements Token {
    public final String name;

    public IdentifierToken(final String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof IdentifierToken asId) {
            return name.equals(asId.name);
        } else {
            return false;
        }
    }
}
