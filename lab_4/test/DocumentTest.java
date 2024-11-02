import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DocumentTest {

    @Test
    void testJsonOperations() {
        Document document = new Document();
        document.setPhoto("img/avatar.jpg");
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

        String oldJson = document.toJson();
        String newJson = Document.fromJson(oldJson).toJson();
        assertEquals(oldJson, newJson);
    }
}