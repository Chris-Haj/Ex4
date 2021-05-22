import java.io.IOException;
import java.io.*;

public class TextAnalyzer extends Exception {

private File file;


    public TextAnalyzer(String textPath) throws IOException {
         if(textPath.substring(textPath.lastIndexOf('.'+1)).equals("txt"))
             file = new File(textPath);
         else
             throw new IOException();
    }
    public void cleanText() throws CleanFailException{

        file = new File(file.getName()+"Normalize");
        

    }

    public void countWords() throws CountFailException{

    }


    private class CleanFailException extends Exception {

    }

    private class CountFailException extends Exception {
    }
}
