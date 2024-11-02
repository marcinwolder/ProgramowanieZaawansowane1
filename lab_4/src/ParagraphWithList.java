import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {
    UnorderedList list = new UnorderedList();
    ParagraphWithList(){
        super("");
    }
    ParagraphWithList addListItem(String text) {
        list.addItem(text);
        return this;
    }
    @Override
    ParagraphWithList setContent(String content) {
        super.setContent(content);
        return this;
    }
    void writeHTML(PrintStream out){
        super.writeHTML(out);
        list.writeHTML(out);
    }
}
