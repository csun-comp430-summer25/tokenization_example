package tokenization_example.parser;

import tokenization_example.tokenizer.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseTrue() throws ParseException {
        final ArrayList<Token> tokens = new ArrayList<Token>();
        tokens.add(new TrueToken());
        
        assertEquals(new Program(new TrueLiteralExp()),
                     Parser.parseProgram(tokens));
    }
}

