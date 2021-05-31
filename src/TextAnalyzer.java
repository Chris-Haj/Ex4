import java.io.*;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextAnalyzer extends Exception {

private File file;
private String path;
private List<String> stopwords= new ArrayList<String>();


    public TextAnalyzer(String textPath) throws IOException {
         if(textPath.substring(textPath.length()-4,textPath.length()).equals(".txt")){
             file = new File(textPath);
             path = textPath;
         }
         else
             throw new IOException();
    }
    public void cleanText() throws CleanFailException{
        String NormalPath = path.substring(0,path.lastIndexOf('.'));
        File normal = new File(NormalPath+"Normalize.txt");

        try {
            Scanner scan = new Scanner(new File("StopWords.txt"));
            while(scan.hasNextLine()){
            stopwords.add(scan.nextLine());
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    public void countWords() throws CountFailException{

    }


    private class CleanFailException extends Exception {

    }

    private class CountFailException extends Exception {
    }
}
