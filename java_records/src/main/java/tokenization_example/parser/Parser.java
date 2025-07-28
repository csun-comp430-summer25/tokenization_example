package tokenization_example.parser;

import tokenization_example.tokenizer.*;

import java.util.ArrayList;

public class Parser {
    private final ArrayList<Token> tokens;

    public Parser(final ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    private Token getToken(final int pos) throws ParseException {
        if (pos < 0 || pos >= tokens.size()) {
            throw new ParseException("Not in bounds: " + pos);
        } else {
            return tokens.get(pos);
        }
    }

    private Program parseProgramInternal() throws ParseException {
        final ParseResult<Program> result = parseProgram(0);
        if (result.nextPos() == tokens.size()) {
            return result.value();
        } else {
            throw new ParseException("tokens remaining at position " + result.nextPos());
        }
    }
        
    // program ::= exp
    private ParseResult<Program> parseProgram(final int startPos) throws ParseException {
        final ParseResult<Exp> inner = parseExp(startPos);
        return new ParseResult<Program>(new Program(inner.value()), inner.nextPos());
    }
    
    // exp ::= addExp
    private ParseResult<Exp> parseExp(final int startPos) throws ParseException {
        return parseAddExp(startPos);
    }

    // addExp ::= multExp ((`+` | `-`) multExp)*
    private ParseResult<Exp> parseAddExp(final int startPos) throws ParseException {
        final ParseResult<Exp> initialExp = parseMultExp(startPos);
        Exp currentExp = initialExp.value();
        int currentPosition = initialExp.nextPos();
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                final Token operator = getToken(currentPosition);
                if (operator instanceof PlusToken) {
                    final ParseResult<Exp> innerExp = parseMultExp(currentPosition + 1);
                    currentExp = new BinopExp(currentExp, new PlusOp(), innerExp.value());
                    currentPosition = innerExp.nextPos();
                } else if (operator instanceof MinusToken) {
                    final ParseResult<Exp> innerExp = parseMultExp(currentPosition + 1);
                    currentExp = new BinopExp(currentExp, new MinusOp(), innerExp.value());
                    currentPosition = innerExp.nextPos();
                } else {
                    shouldRun = false;
                }
            } catch (final ParseException e) {
                shouldRun = false;
            }
        }
        return new ParseResult<Exp>(currentExp, currentPosition);
    }
        
    // multExp ::= primaryExp ((`*` | `/`) primaryExp)*
    private ParseResult<Exp> parseMultExp(final int startPos) throws ParseException {
        final ParseResult<Exp> initialExp = parsePrimaryExp(startPos);
        Exp currentExp = initialExp.value();
        int currentPosition = initialExp.nextPos();
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                final Token operator = getToken(currentPosition);
                if (operator instanceof MultToken) {
                    final ParseResult<Exp> innerExp = parsePrimaryExp(currentPosition + 1);
                    currentExp = new BinopExp(currentExp, new MultOp(), innerExp.value());
                    currentPosition = innerExp.nextPos();
                } else if (operator instanceof DivToken) {
                    final ParseResult<Exp> innerExp = parsePrimaryExp(currentPosition + 1);
                    currentExp = new BinopExp(currentExp, new DivOp(), innerExp.value());
                    currentPosition = innerExp.nextPos();
                } else {
                    shouldRun = false;
                }
            } catch (final ParseException e) {
                shouldRun = false;
            }
        }
        return new ParseResult<Exp>(currentExp, currentPosition);
    }
                    
    // Recursive-descent parser
    // Each production rule in the concrete grammar becomes a method
    //
    // primaryExp ::= `true` | `false` | INTEGER | `(` exp `)`
    private ParseResult<Exp> parsePrimaryExp(final int startPos) throws ParseException {
        final Token token = getToken(startPos);
        if (token instanceof TrueToken) {
            return new ParseResult<Exp>(new TrueLiteralExp(), startPos + 1);
        } else if (token instanceof FalseToken) {
            return new ParseResult<Exp>(new FalseLiteralExp(), startPos + 1);
        } else if (token instanceof IntegerToken asInt) {
            return new ParseResult<Exp>(new IntegerLiteralExp(asInt.value()), startPos + 1);
        } else if (token instanceof LeftParenToken) {
            // `(` exp `)`
            final ParseResult<Exp> nestedExp = parseExp(startPos + 1);
            if (getToken(nestedExp.nextPos()) instanceof RightParenToken) {
                // if parens were a separate AST node
                // return new ParseResult<Exp>(new ParenExp(nestedExp.value()), nestedExp.nextPos() + 1)
                return new ParseResult<Exp>(nestedExp.value(), nestedExp.nextPos() + 1);
            } else {
                throw new ParseException("Not a right paren: " + getToken(nestedExp.nextPos()));
            }
        } else {
            throw new ParseException("Not a primaryExp: " + token);
        }
    }

    public static Program parseProgram(final ArrayList<Token> tokens) throws ParseException {
        return new Parser(tokens).parseProgramInternal();
    }
}
