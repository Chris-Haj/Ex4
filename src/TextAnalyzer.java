import java.io.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.sql.Wrapper;
import java.util.*;

public class TextAnalyzer extends Exception {

    private File file;
    private String path;
    private ArrayList<String> stopwords = new ArrayList<String>();
    private HashMap<String, Integer> CountMap = new HashMap<String, Integer>();
    String clean;
    int wordcount=0;
    private ArrayList<Integer> Values = new ArrayList<Integer>();


    public TextAnalyzer(String textPath) throws IOException {
        if (textPath.substring(textPath.length() - 4, textPath.length()).equals(".txt")) {
            file = new File(textPath);
            path = textPath;
        } else
            throw new IOException();
    }

    public void cleanText() throws CleanFailException {
        FileWriter fr2 = null;
        Scanner reader = null;
        FileWriter fr = null;
        Scanner stopscanner = null;
        try {
            String NormalPath = path.substring(0, path.lastIndexOf('.'));
            String StpWrdsPath = path.substring(0, path.lastIndexOf('/') + 1);

            File normal = new File(NormalPath + "Normalize.txt");
            fr2 = new FileWriter(normal);
            String NrmlTXT = "";
            reader = new Scanner(file);
            while (reader.hasNextLine()) {
                NrmlTXT = NrmlTXT.concat(reader.nextLine() + "\n");
            }


            NrmlTXT = NrmlTXT.toLowerCase();
            NrmlTXT = NrmlTXT.replaceAll("[^a-z ]", "");
            clean = NrmlTXT;
            fr = new FileWriter(normal);
            fr.write(NrmlTXT);
            File Stopper = new File(StpWrdsPath + "stopWords.txt");
            stopscanner = new Scanner(Stopper);
            while (stopscanner.hasNextLine()) {
                stopwords.add(stopscanner.nextLine());
            }
            stopscanner.close();
            for (int i=0;i< stopwords.size();i++) {
                stopwords.set(i, " "+stopwords.get(i)+" ");
            }
            for(String i: stopwords){
                NrmlTXT = NrmlTXT.replaceAll(i, " ");
            }
            File clean = new File(NormalPath + "Clean.txt");
            fr2 = new FileWriter(clean);
            fr2.write(NrmlTXT);
            System.out.println(stopwords);


        } catch (IOException e) {
            throw new CleanFailException();
        } finally {
            try {
                if (fr2 != null)
                    fr2.close();
                if (reader != null)
                    reader.close();
                if (fr != null)
                    fr.close();
                if (stopscanner != null)
                    stopscanner.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }


    public void countWords() throws CountFailException {
        String StpWrdsPath = path.substring(0, path.lastIndexOf('/') + 1);
        File cleanfile = new File(StpWrdsPath + "StopWords.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(cleanfile);
            int i = 0;
            String word = "";
            int j;
            while ((j=fr.read())!=-1) {
                if (j == ' ') {
                    if (CountMap.containsKey(word)) {
                        CountMap.put(word, CountMap.get(word) + 1);
                        word = "";
                        wordcount++;
                    }
                } else
                    word += (char)j;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    public void generateStatistics() throws GenerationFailException{
        String[] words = new String[CountMap.size()];
        int[] wordcount = new int[CountMap.size()];
        int j=0;
        for(String i : CountMap.keySet() ){
            words[j]=i;
            j++;
        }
        j=0;
        for(int i: CountMap.values()){
            wordcount[j]=i;
        }
        int big =0;
        for(int i =0; i<wordcount.length;i++){
            if(wordcount[i]>big) {
                big = wordcount[i];
                Values.clear();
                Values.add(wordcount[i]);
            }
            else if (wordcount[i]==big)
                Values.add(wordcount[i]);
        }
        String MostOccurredWords="";
        for(int i =0;i<Values.size();i++) {
            if(i==Values.size()-1)
                MostOccurredWords+= words[Values.get(i)];
            else
                MostOccurredWords+= words[Values.get(i)]+", ";
        }
        String stat = path.substring(0,path.lastIndexOf('.'));
        File stats = new File(stat+"Stat.txt");
        FileWriter fw=null;
        try {
            fw = new FileWriter(stats);
            String wordcountString= String.valueOf(wordcount);
            fw.write("Total words: "+wordcount+"\n");
            fw.write("Total unique words: "+CountMap.size()+"\n");
            fw.write("Most occured word(s): "+MostOccurredWords+"\n");
            fw.write("Total words: "+wordcount.toString()+"\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fw!=null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}




