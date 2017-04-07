package util;

import de.mexchange.packagingdb.entity.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Arthur on 6/20/2016.
 */
public class ObjectFields {

    public static void main(String[] args) {
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        List<String> names3 = new ArrayList<>();
        List<String> names4 = new ArrayList<>();
        List<String> names5 = new ArrayList<>();
        List<String> names6 = new ArrayList<>();
        List<String> names7 = new ArrayList<>();
        List<String> names8 = new ArrayList<>();
        Field[] fields;

        fields = CylindricalEntity.class.getDeclaredFields();
        for (Field field : fields) {
            names1.add(field.getName());
        }

        fields = CubicEntity.class.getDeclaredFields();
        for (Field field : fields) {
            names2.add(field.getName());
        }

        fields = BulkEntity.class.getDeclaredFields();
        for (Field field : fields) {
            names3.add(field.getName());
        }

        fields = ConicalCanisterEntity.class.getDeclaredFields();
        for (Field field : fields) {
            names4.add(field.getName());
        }

        fields = ConicalCansEntity.class.getDeclaredFields();
        for (Field field : fields) {
            names5.add(field.getName());
        }

        fields = ConicalClampingRingEntity.class.getDeclaredFields();
        for (Field field : fields) {
            names6.add(field.getName());
        }

        fields = CylindricalClampingRingEntity.class.getDeclaredFields();
        for (Field field : fields) {
            names7.add(field.getName());
        }

        fields = BigBagsEntity.class.getDeclaredFields();
        for (Field field : fields) {
            names8.add(field.getName());
        }

        Collections.sort(names1);
        Collections.sort(names2);
        Collections.sort(names3);
        Collections.sort(names4);
        Collections.sort(names5);
        Collections.sort(names6);
        Collections.sort(names7);
        Collections.sort(names8);


        /*List<String> notExist = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i < names3.size()) {
                if (names3.contains(names3.get(i))) {
                    names3.remove(names3.get(i));
                    System.out.println(names3.get(i));
                } else {
                    notExist.add(names3.get(i));
                }
            }

        }*/
        System.out.println("\n\n------------------------------------------\n\n");
        for (int i = 0; i < names3.size(); i++) {
            System.out.println((i+1)+". "+names3.get(i));
        }

       /* System.out.println("\n\n------------------------------------------\n\n");
        for (int i = 0; i < notExist.size(); i++) {
            System.out.println(notExist.get(i));
        }*/

    }
}
