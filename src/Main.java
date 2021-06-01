import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        try {
            TextAnalyzer text = new TextAnalyzer("/Users/chris/Desktop/beatles.txt");
            text.cleanText();
            text.countWords();
            text.generateStatistics();
        } catch (IOException | CountFailException e) {
            e.printStackTrace();
        } catch (GenerationFailException | CleanFailException e) {
            e.printStackTrace();
        }
    }
}
