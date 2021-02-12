package commons.read_write;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWrite<T> {
    static final String PATH = "src/data/";
    public void writeToFile(String nameFile, List<T> list, boolean notOverride) {
        File file = new File(PATH+nameFile);
        BufferedWriter bufferedWriter = null;
        try {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileWriter fileWriter = new FileWriter(file,notOverride);
            bufferedWriter = new BufferedWriter(fileWriter);
            for(T t : list) {
                bufferedWriter.write(t.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Writing Error");
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public List<String[]> readFromFile(String nameFile) {
        List<String[]> list = new ArrayList<>();
        File file = new File(PATH+nameFile);
        FileReader fileReader = null;
        BufferedReader bufferedReader= null;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            String[] vehicle;
            while ((line = bufferedReader.readLine()) != null) {
                vehicle = line.split(",");
                list.add(vehicle);
            }
        } catch (IOException e) {
            System.out.println("Reading Error");
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
