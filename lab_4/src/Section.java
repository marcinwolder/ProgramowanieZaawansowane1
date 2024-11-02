import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>();

    Section(String title){
        this.title = title;
    }
    Section setTitle(String title){
        this.title = title;
        return this;
    }
    Section addParagraph(String paragraphText){
        paragraphs.add(new Paragraph(paragraphText));
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraphs.add(p);
        return this;
    }
    void writeHTML(PrintStream out){
        out.printf("""
                <section class="w-[calc(100%%-4em)] py-3 mx-auto border-2 border-white border-t-neutral-200 last:border-b-neutral-200">
                    <h2 class="text-lg">%s</h2>
        """, this.title);
        for (Paragraph p : paragraphs){
            p.writeHTML(out);
        }
        out.print("""
                </section>
        """);
    }
}

