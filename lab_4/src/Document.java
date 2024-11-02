import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();
    private static RuntimeTypeAdapterFactory<Paragraph> adapter = RuntimeTypeAdapterFactory
            .of(Paragraph.class)
            .registerSubtype(Paragraph.class)
            .registerSubtype(ParagraphWithList.class);
    private static Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).setPrettyPrinting().setPrettyPrinting().create();

    Document setTitle(String title){
        this.title = title;
        return this;
    }
    Document setPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
        return this;
    }
    Section addSection(String sectionTitle){
        this.sections.add(new Section(sectionTitle));
        return this.sections.getLast();
    }
    Document addSection(Section s){
        this.sections.add(s);
        return this;
    }
    void writeHTML(PrintStream out){
        out.printf("""
                <!DOCTYPE html>
                <html lang="pl">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <script src="https://cdn.tailwindcss.com"></script>
                    <title>%s</title>
                </head>
                <body>
                    <div class="flex flex-col p-4 items-center">
                """, this.title);
        this.photo.writeHTML(out);
        out.printf("""
                        <h1 class="text-3xl font-medium mb-3">%s</h1>
                    </div>
                    <div>
                """, this.title);
        for (Section section : this.sections){
            section.writeHTML(out);
        }
        out.println("""
                    </div>
                    <div class="text-xs p-4 font-thin text-neutral-400 text-justify">
                        Wyrażam zgodę na przetwarzanie moich danych osobowych dla potrzeb niezbędnych do realizacji procesu rekrutacji (zgodnie z ustawą z dnia 10 maja 2018 roku o ochronie danych osobowych (Dz. Ustaw z 2018, poz. 1000) oraz zgodnie z Rozporządzeniem Parlamentu Europejskiego i Rady (UE) 2016/679 z dnia 27 kwietnia 2016 r. w sprawie ochrony osób fizycznych w związku z przetwarzaniem danych osobowych i w sprawie swobodnego przepływu takich danych oraz uchylenia dyrektywy 95/46/WE (RODO).
                    </div>
                </body>
                </html>""");
    }
    String toJson(){
        return gson.toJson(this);
    }
    static Document fromJson(String jsonString){
        return gson.fromJson(jsonString, Document.class);
    }
}


