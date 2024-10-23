import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        Document document = new Document();
        document.setPhoto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCnKU5ZXpkJymCHXiEI7ZvIvbbbRF_tm7lo6KK-Lo9Js2PD3bB");
        document.setTitle("Pies Programista");
        document.addSection("Wykształcenie:")
            .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
            .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
            .addParagraph(
                new ParagraphWithList().setContent("Kursy")
                    .addListItem("Języka Angielskiego")
                    .addListItem("Języka Hiszpańskiego")
                    .addListItem("Szydełkowania")
            );
        document.addSection("Umiejętności")
            .addParagraph(
                new ParagraphWithList().setContent("Znane technologie")
                    .addListItem("C")
                    .addListItem("C++")
                    .addListItem("Java")
            );

        String documentJSON = document.toJson();
        Document deserialisedDocument = Document.fromJson(documentJSON);
        deserialisedDocument.writeHTML(new PrintStream("cv.html", StandardCharsets.UTF_8));
        deserialisedDocument.writeHTML(System.out);
        System.out.println(documentJSON);
        BufferedWriter writer = new BufferedWriter(new FileWriter("cv.json"));
        writer.write(documentJSON);
        writer.close();
    }
}