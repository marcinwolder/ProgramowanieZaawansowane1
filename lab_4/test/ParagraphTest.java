import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphTest {
    Paragraph paragraph;
    String content;
    ByteArrayOutputStream os;
    PrintStream ps;

    @BeforeEach
    void setup(){
        content = "Hello World";
        os = new ByteArrayOutputStream();
        ps = new PrintStream(os);
    }

    @Test
    void setContent() {
        paragraph = new Paragraph("");
        paragraph.writeHTML(ps);
        String result = os.toString(StandardCharsets.UTF_8);
        assertFalse(result.contains(content));

        os.reset();
        paragraph.setContent(content);
        paragraph.writeHTML(ps);
        result = os.toString(StandardCharsets.UTF_8);
        assertTrue(result.contains(content));

    }

    @Test
    void writeHTML() {
        new Paragraph(content).writeHTML(ps);
        String result = os.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains("<p"));
        assertTrue(result.contains("</p>"));
        assertTrue(result.contains(content));
    }
}