package util;

import static util.FileHelper.fromSetToList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Arthur on 5/9/2016.
 */
public class ObjectFieldAnalyser {

    static String[] objects =
            new String[]{
                    "big_bags", "bulk", "canonical_cans",
                    "conical_clamping_ring", "cubic",
                    "cubic_canister", "cylindrical", "cylindrical_clamping_ring"
    };


    private static final int MAX_FIELD_SIZES = 500;
    private static final Set<String> big_bags = new HashSet<>(MAX_FIELD_SIZES);
    private static final Set<String> bulk = new HashSet<>(MAX_FIELD_SIZES);
    private static final Set<String> canonical_cans = new HashSet<>(MAX_FIELD_SIZES);
    private static final Set<String> conical_clamping_ring = new HashSet<>(MAX_FIELD_SIZES);
    private static final Set<String> cubic = new HashSet<>(MAX_FIELD_SIZES);
    private static final Set<String> cubic_canister = new HashSet<>(MAX_FIELD_SIZES);
    private static final Set<String> cylindrical = new HashSet<>(MAX_FIELD_SIZES);
    private static final Set<String> cylindrical_clamping_ring = new HashSet<>(MAX_FIELD_SIZES);

    public void readContents() {
        ClassLoader classLoader = ObjectFieldAnalyser.class.getClassLoader();

        File bigBagsFile = new File(classLoader.getResource("objects/big_bags").getFile());
        readFileToList(bigBagsFile, big_bags);

        File bulkFile = new File(classLoader.getResource("objects/bulk").getFile());
        readFileToList(bulkFile, bulk);

        File canonicalCansFile = new File(classLoader.getResource("objects/canonical_cans").getFile());
        readFileToList(canonicalCansFile, canonical_cans);

        File conical_clamping_ringFile = new File(classLoader.getResource("objects/conical_clamping_ring").getFile());
        readFileToList(conical_clamping_ringFile, conical_clamping_ring);

        File cubicFile = new File(classLoader.getResource("objects/cubic").getFile());
        readFileToList(cubicFile, cubic);

        File cubic_canisterFile = new File(classLoader.getResource("objects/cubic_canister").getFile());
        readFileToList(cubic_canisterFile, cubic_canister);

        File cylindricalFile = new File(classLoader.getResource("objects/cylindrical").getFile());
        readFileToList(cylindricalFile, cylindrical);

        File cylindrical_clamping_ringFile = new File(classLoader.getResource("objects/cylindrical_clamping_ring").getFile());
        readFileToList(cylindrical_clamping_ringFile, cylindrical_clamping_ring);
    }

    public void analyser() throws Exception {
        List<String> bulkList = fromSetToList(bulk);

        List<String> cubicList = fromSetToList(cubic);
        List<String> cubicCanisterList = fromSetToList(cubic_canister);

        List<String> cylindricalClampingRingList = fromSetToList(cylindrical_clamping_ring);
        List<String> cylindricalList = fromSetToList(cylindrical);

        List<String> bigBagsList = fromSetToList(big_bags);

        List<String> canonicalCansList = fromSetToList(canonical_cans);
        List<String> conicalClampingRingList = fromSetToList(conical_clamping_ring);



        List<List<String>> listOfFields = new ArrayList<>();
        listOfFields.add(new ArrayList<String>());
        listOfFields.add(bulkList);
        listOfFields.add(cubicList);
        listOfFields.add(cubicCanisterList);
        listOfFields.add(cylindricalClampingRingList);
        listOfFields.add(cylindricalList);
        listOfFields.add(bigBagsList);
        listOfFields.add(canonicalCansList);
        listOfFields.add(conicalClampingRingList);

        List<String> allFields = allFields(listOfFields);

        String[][] result = loadDataToArray(listOfFields, allFields);

        analyser(result, listOfFields, allFields.size());
    }

    public void analyser(String[][] result, List<List<String>> listOfFields, int size) throws Exception {
        String value;
        for (int j = 0; j < size; j++) {
            value = result[j][0];
            for (int i = 0; i < 9; i++) {

                String val = result[j][i];

                if (value != null) {

                    if (val.equals(value)) {
                        // OK
                    } else {
                        int index = getIndex(result, i, value, 0, size);
                        if (index == -1) {
                            index = getIndex(result, i, "...", listOfFields.get(i).size(), size);
                            if (index == -1) {
                                System.out.println("space is not enough");
                            } else {
                                result[index][i] = val;
                                result[j][i] = "...";
                            }
                        } else {
                            result[j][i] = result[index][i];
                            result[index][i] = val;
                        }
                    }
                }
            }
        }

        print(result, size);
    }

    /*
     listOfFields.add(bulkList);
        listOfFields.add(cubicList);
        listOfFields.add(cubicCanisterList);
        listOfFields.add(cylindricalClampingRingList);
        listOfFields.add(cylindricalList);
        listOfFields.add(bigBagsList);
        listOfFields.add(canonicalCansList);
        listOfFields.add(conicalClampingRingList);
     */
    public void print(String[][] array, int size) {
        System.out.println("IBC Bulk | IBC Cubic | Cubic Canister | Cylindrical clamping ring | IBC Cylindrical | Big Bag | Canonical Cans | Conical clamping ring");
        System.out.println("--- | --- | --- | --- | --- | --- | --- | --- | ---" );
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < 9 ; j++) {
                System.out.print(array[i][j]);
                System.out.print("| ");
                if (j == 8) {
                    System.out.print("\n");
                }
            }
        }
    }

    public int getIndex(String[][] array, int column, String value, int start, int size){
        for (int i = start; i < size; i++){
            if (array[i][column].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    public String[][] loadDataToArray(List<List<String>> listArray, List<String> firstColumn ){
        String[][] result = new String[firstColumn.size()][9];
        for (int i = 0; i < firstColumn.size(); i++) {
            for (int j = 1; j < 9 ; j++) {
                result[i][j] = "...";
            }
        }

        for (int i = 0; i < firstColumn.size(); i++) {
            result[i][0] = firstColumn.get(i);
        }

        int a = 0, b;
        for (List<String> list : listArray) {
            b = 1;
            for (String str : list) {
                result[b][a] = str;
                b++;
            }
            a++;
        }

        return result;
    }

    public List<String> allFields(List<List<String>> list) {
        Set<String> set = new HashSet<String>();
        for (List<String> l : list) {
            for (String s : l) {
                set.add(s);
            }
        }
        List<String> listResult = new ArrayList<String>(set);
        Collections.sort(listResult);
        return listResult;
    }

    protected static void readFileToList(File file, List<String> list) {
        try {
            FileHelper.readFileToList(file, list);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    protected static void readFileToList(File file, Set<String> list) {
        try {
            FileHelper.readFileToList(file, list);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public static void main(String[] args) throws Exception {
        ObjectFieldAnalyser objectFieldAnalyser = new ObjectFieldAnalyser();
        objectFieldAnalyser.readContents();
        objectFieldAnalyser.analyser();
    }
}
