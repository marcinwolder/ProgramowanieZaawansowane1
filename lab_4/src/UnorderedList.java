import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> items = new ArrayList<>();
    UnorderedList addItem(String item){
        items.add(new ListItem(item));
        return this;
    }
    void writeHTML(PrintStream out){
        out.print("""
                    <ul class="ml-8 list-inside list-disc">
        """);
        for (ListItem item : items) {
            item.writeHTML(out);
        }
        out.print("""
                    </ul>
        """);
    }
}
