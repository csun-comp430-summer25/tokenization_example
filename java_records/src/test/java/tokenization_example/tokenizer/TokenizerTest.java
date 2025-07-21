package tokenization_example.tokenizer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TokenizerTest {
    @Test
    public void testEmptyString() throws TokenizerException {
        final ArrayList<Token> received = Tokenizer.tokenize("");
        assertEquals(0, received.size());
    }

    @Test
    public void testOnlyWhitespace() throws TokenizerException {
        final ArrayList<Token> received = Tokenizer.tokenize("  \t  ");
        assertEquals(0, received.size());
    }

    @Test
    public void testSingleDigitInteger() throws TokenizerException {
        final ArrayList<Token> received = Tokenizer.tokenize("1");
        assertEquals(1, received.size());
        assertEquals(new IntegerToken(1), received.get(0));
    }
}
