// src/test/java/watson/parser/ParserTest.java
package Watson.parser;

import Watson.command.*;
import Watson.exception.WatsonException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parse_validTodoCommand_returnsAddCommand() throws WatsonException {
        Command cmd = Parser.parse("todo Buy milk");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void parse_invalidCommand_throwsException() {
        assertThrows(WatsonException.class, () -> {
            Parser.parse("blah invalid command");
        });
    }
}