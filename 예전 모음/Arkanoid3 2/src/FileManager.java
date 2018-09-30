import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static String readFile(String filename){

        String line;
        StringBuilder builder = new StringBuilder();

        try(FileReader fileReader = new FileReader(filename)){

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static List<String> splitString(String std, String src) {

        List<String> strings = new ArrayList<>();

        String[] str;
        str = src.split(std);
        for (String s : str) {
            strings.add(s);
        }

        return strings;
    }





}
