package tokenization_example.parser;

import tokenization_example.tokenizer.*;

import java.util.ArrayList;

public class Parser {
    private final ArrayList<Token> tokens;

    public Parser(final ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    // Recursive-descent parser
    // Each production rule in the concrete grammar becomes a method
    private ParseResult<Exp> parsePrimaryExp(final int startPos) throws ParseException {
        final Token token = tokens.get(startPos);
        if (token instanceof TrueToken) {
            return new ParseResult<Exp>(new TrueLiteralExp(), startPos + 1);
        } else if (token instanceof FalseToken) {
            return new ParseResult<Exp>(new FalseLiteralExp(), startPos + 1);
        } else if (token instanceof IntegerToken asInt) {
            return new ParseResult<Exp>(new IntegerLiteralExp(asInt.value), startPos + 1);
        } else if (token instanceof LeftParenToken) {
            // `(` exp `)`
            // TODO: implement
            
        } else {
            throw new ParseException("Not a primaryExp: " + token);
        }
    }
}
