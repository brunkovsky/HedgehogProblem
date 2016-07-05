package IO;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileWorker {

    public static String convertFileToString(String fileName) {
        StringBuilder rawData = new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                rawData.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(rawData);
    }

    public static void writeToFile(int value, String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(value));
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
