import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static void writeFile(String fileName, String contents) {

        Path fp = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(fp)) {

            bw.write(contents);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
