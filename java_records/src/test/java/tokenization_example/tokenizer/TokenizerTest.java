package tokenization_example.tokenizer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class TokenizerTest {
    // assertTokenizes("")
    // assertTokenizes("   ")
    // assertTokenizes("1", new IntegerToken(1))
    // assertTokenizes("123", new IntegerToken(123))
    // assertTokenizes("1+3", new IntegerToken(1), new PlusToken(), new IntegerToken(1))
    public void assertTokenizes(final String input,
                                final Token... expected) throws TokenizerException {
        assertArrayEquals(expected,
                          Tokenizer.tokenize(input).toArray(new Token[0]));
    }
                                
    @Test
    public void testEmptyString() throws TokenizerException {
        assertTokenizes("");
    }

    @Test
    public void testOnlyWhitespace() throws TokenizerException {
        assertTokenizes("  ");
    }

    @Test
    public void testSingleDigitInteger() throws TokenizerException {
        assertTokenizes("1", new IntegerToken(1));
    }

    @Test
    public void testPlus() throws TokenizerException {
        assertTokenizes("+", new PlusToken());
    }

    @Test
    public void testMinus() throws TokenizerException {
        assertTokenizes("-", new MinusToken());
    }

    @Test
    public void testLogicalAnd() throws TokenizerException {
        assertTokenizes("&&", new LogicalAndToken());
    }

    @Test
    public void testLessThanToken() throws TokenizerException {
        assertTokenizes("<", new LessThanToken());
    }

    @Test
    public void testDoubleEqualsToken() throws TokenizerException {
        assertTokenizes("==", new DoubleEqualsToken());
    }

    @Test
    public void testIdentifierToken() throws TokenizerException {
        assertTokenizes("x", new IdentifierToken("x"));
    }

    @Test
    public void testTrueToken() throws TokenizerException {
        assertTokenizes("true", new TrueToken());
    }

    @Test
    public void testFalseToken() throws TokenizerException {
        assertTokenizes("false", new FalseToken());
    }

    @Test
    public void testInvalid() {
        try {
            assertTokenizes("$");
            assertEquals(0, 1);
        } catch (final TokenizerException e) {
        }
    }
}
