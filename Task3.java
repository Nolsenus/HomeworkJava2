import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task3 {

    static String[] formStrings(List<HashMap<String,String>> list) {
        String[] result = new String[list.size()];
        int i = 0;
        for (HashMap<String, String> map : list) {
            StringBuilder sb = new StringBuilder();
            sb.append("Студент ");
            sb.append(map.get("фамилия"));
            sb.append(" получил ");
            sb.append(map.get("оценка"));
            sb.append(" по предмету ");
            sb.append(map.get("предмет"));
            sb.append(".");
            result[i++] = sb.toString();
        }
        return result;
    }

    static List<HashMap<String, String>> parseJSON(String contents) {
        String[] maps = contents.replaceAll("[\\[\\]]", "").split("}, *\\{");
        List<HashMap<String, String>> result = new ArrayList<>(maps.length);
        for (String map : maps) {
            HashMap<String, String> entry = new HashMap<>();
            String[] kvPairs = map.replaceAll("[{}]", "").split(",");
            for (String kvPair : kvPairs) {
                int indexOfColon = kvPair.indexOf(':');
                String key = kvPair.substring(0, indexOfColon).replaceAll("\"", "");
                String value = kvPair.substring(indexOfColon + 1).replaceAll("\"", "");
                entry.put(key, value);
            }
            result.add(entry);
        }
        return result;
    }

    static String fileAsOneLine(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString().replaceAll("\n", "");
    }

    public static void main(String[] args) {
        try {
            String contents = fileAsOneLine("./src/task3.json");
            String[] strings = formStrings(parseJSON(contents));
            for (String s : strings) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ошибка:");
            e.printStackTrace();
        }
    }
}
