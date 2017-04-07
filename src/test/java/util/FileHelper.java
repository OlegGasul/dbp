package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Arthur on 5/9/2016.
 */
public class FileHelper {



    public static void readFileToList(File file, List<String> list) throws IOException {
        InputStream in = new FileInputStream(file);
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }
    }

    public static void readFileToList(File file, Set<String> list) throws IOException {
        InputStream in = new FileInputStream(file);
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }
    }

    public static List<String> fromSetToList(Set<String> set){
        List<String> list = new ArrayList<>(set.size());
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

}
