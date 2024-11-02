import java.io.PrintStream;

public class Photo {
    String url;
    Photo(String url) {
        this.url = url;
    }
    void writeHTML(PrintStream out){
        out.printf("""
                <img src="%s" class="h-32 rounded-2xl my-6" />
        """, this.url);
    }
}