import java.util.Optional;
import java.util.ArrayList;

public class Tokenizer {
    public final String input;
    private int position;
    
    public Tokenizer(final String input) {
        this.input = input;
        position = 0;
    }

    public void skipWhitespace() {
        while (position < input.length() &&
               Character.isWhitespace(input.charAt(position))) {
            position++;
        }
    }

    public Optional<Token> tryReadSymbol() /* throws TokenizerException */ {
        assert position >= 0 && position < input.length();
        assert !Character.isWhitespace(input.charAt(position));
        
        if (input.startsWith("+", position)) {
            position++;
            return Optional.of(new PlusToken());
        } else if (input.startsWith("-", position)) {
            position++;
            return Optional.of(new MinusToken());
        } else if (input.startsWith("&&", position)) {
            position += 2;
            return Optional.of(new LogicalAndToken());
        } else if (input.startsWith("<", position)) {
            position++;
            return Optional.of(new LessThanToken());
        } else if (input.startsWith("==", position)) {
            position += 2;
            return Optional.of(new DoubleEqualsToken());
        } else {
            // not a symbol
            // throw new TokenizerException("Not a symbol");
            // return null;
            return Optional.empty();
        }
    }

    public Optional<Token> tryReadInteger() {
        assert position >= 0 && position < input.length();
        assert !Character.isWhitespace(input.charAt(position));

        String digits = "";
        
        while (position < input.length() &&
               Character.isDigit(input.charAt(position))) {
            digits += input.charAt(position);
            position++;
        }

        if (digits.length() > 0) {
            return Optional.of(new IntegerToken(Integer.parseInt(digits)));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Token> tryReadIdentifierOrReservedWord() {
        assert position >= 0 && position < input.length();
        assert !Character.isWhitespace(input.charAt(position));

        char c = input.charAt(position);
        
        if (Character.isLetter(c) || c == '_') {
            String name = "" + c;
            position++;
            c = input.charAt(position);
            while (Character.isLetterOrDigit(c) || c == '_') {
                name += c;
                position++;
                c = input.charAt(position);
            }

            // figure out if it was a reserved word, and give
            // the appropriate token if so
            if (name.equals("true")) {
                return Optional.of(new TrueToken());
            } else if (name.equals("false")) {
                return Optional.of(new FalseToken());
            } else {
                return Optional.of(new IdentifierToken(name));
            }
        } else {
            // wasn't an identifier or reserved word
            return Optional.empty();
        }
    }

    public Token readToken() throws TokenizerException {
        assert position >= 0 && position < input.length();
        assert !Character.isWhitespace(input.charAt(position));

        Optional<Token> result = tryReadSymbol();
        if (result.isPresent()) {
            return result.get();
        } else {
            result = tryReadInteger();
            if (result.isPresent()) {
                return result.get();
            } else {
                result = tryReadIdentifierOrReservedWord();
                if (result.isPresent()) {
                    return result.get();
                } else {
                    throw new TokenizerException("Not a valid token: " + input.charAt(position));
                }
            }
        }
    }
    
    public ArrayList<Token> readTokens() throws TokenizerException {
        final ArrayList<Token> list = new ArrayList<Token>();
        skipWhitespace();
        while (position < input.length()) {
            list.add(readToken());
            skipWhitespace();
        }
        return list;
    }

    public static ArrayList<Token> tokenize(final String input) throws TokenizerException {
        return new Tokenizer(input).readTokens();
    }
}
