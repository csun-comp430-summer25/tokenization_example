package tokenization_example.parser;

import tokenization_example.tokenizer.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class ParserTest {
    public void assertParses(final Program expected,
                             final Token... tokens) throws ParseException {
        final ArrayList<Token> tokenList = new ArrayList<Token>();
        for (final Token token : tokens) {
            tokenList.add(token);
        }
        assertEquals(expected, Parser.parseProgram(tokenList));
    }
    
    @Test
    public void parseTrue() throws ParseException {
        // true
        assertParses(new Program(new TrueLiteralExp()),
                     new TrueToken());
    }

    @Test
    public void parseFalse() throws ParseException {
        // false
        assertParses(new Program(new FalseLiteralExp()),
                     new FalseToken());
    }

    @Test
    public void parseIntegerLiteral() throws ParseException {
        // 123
        assertParses(new Program(new IntegerLiteralExp(123)),
                     new IntegerToken(123));
    }

    @Test
    public void testPlus() throws ParseException {
        // 1 + 2
        assertParses(new Program(new BinopExp(new IntegerLiteralExp(1),
                                              new PlusOp(),
                                              new IntegerLiteralExp(2))),
                     new IntegerToken(1),
                     new PlusToken(),
                     new IntegerToken(2));
    }
}

