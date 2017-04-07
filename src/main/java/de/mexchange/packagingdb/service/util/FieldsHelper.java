package de.mexchange.packagingdb.service.util;

import de.mexchange.packagingdb.common.search.FieldInfo;
import de.mexchange.packagingdb.common.search.FieldType;
import de.mexchange.packagingdb.domain.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: Garik
 * Date: 6/23/16
 * Time: 11:59 PM
 */
public class FieldsHelper {

    public static final String status = "sapCode";
    public static final String sapCode = "status";
    public static final String businessUnit = "businessUnit";
    public static final String coating = "coating";
    public static final String frameHeight = "frameHeight";
    public static final String dateCreated = "dateCreated";


    public static List<FieldInfo> getContainerFields() {
        List<FieldInfo> fields = new ArrayList<>();
        fields.add(new FieldInfo("status", FieldType.ENUM));
        fields.add(new FieldInfo("sapCode", FieldType.STRING));
        fields.add(new FieldInfo("localCode", FieldType.STRING));
        fields.add(new FieldInfo("globalCode", FieldType.STRING));
        fields.add(new FieldInfo("nominalVolume", FieldType.STRING));
        fields.add(new FieldInfo("designation", FieldType.STRING));
        fields.add(new FieldInfo("location", FieldType.OBJECT));
        fields.add(new FieldInfo("businessUnit", FieldType.OBJECT));
        fields.add(new FieldInfo("exZone", FieldType.OBJECT));
        fields.add(new FieldInfo("coatingFrame", "coating", FieldType.OBJECT));
        fields.add(new FieldInfo("frameHeight", FieldType.DOUBLE));
        fields.add(new FieldInfo("dateCreated", FieldType.DATE));
        return fields;
    }

    public  static void getContainerFields(List<FieldInfo> fieldsInfos ) {

        fieldsInfos.add(new FieldInfo("sapCode", FieldType.STRING));
        fieldsInfos.add(new FieldInfo("localCode", FieldType.STRING));
        fieldsInfos.add(new FieldInfo("globalCode", FieldType.STRING));
        fieldsInfos.add(new FieldInfo("nominalVolume", FieldType.STRING));
        fieldsInfos.add(new FieldInfo("designation", FieldType.STRING));
        fieldsInfos.add(new FieldInfo("location", FieldType.OBJECT));
        fieldsInfos.add(new FieldInfo("businessUnit", FieldType.OBJECT));
        fieldsInfos.add(new FieldInfo("frameHeight", FieldType.DOUBLE));
        fieldsInfos.add(new FieldInfo("dateCreated", FieldType.DATE));

        getContainerFields(fieldsInfos,BigBags.class.getDeclaredFields());
        getContainerFields(fieldsInfos,Bulk.class.getDeclaredFields());
        getContainerFields(fieldsInfos,ConicalCanister.class.getDeclaredFields());
        getContainerFields(fieldsInfos,ConicalCans.class.getDeclaredFields());
        getContainerFields(fieldsInfos,ConicalClampingRing.class.getDeclaredFields());
        getContainerFields(fieldsInfos,Cubic.class.getDeclaredFields());
        getContainerFields(fieldsInfos,CylindricalClampingRing.class.getDeclaredFields());
        getContainerFields(fieldsInfos,Cylindrical.class.getDeclaredFields());
    }

    private  static void getContainerFields(List<FieldInfo> fieldsInfos, Field[] fields) {
        for(Field field : fields){
            String fName = field.getName();
            if(fName.equals("drawing") || fName.equals("drawingFilename") || fName.equals("drawingContentType") ||
                    fName.startsWith("drawingCAD") ||
                    fName.startsWith("manufacturerSpecifications")){
                continue;
            }

            Class<?> type = field.getType();
            if(field.isEnumConstant()){
                FieldInfo info = new FieldInfo(fName, FieldType.ENUM);
                if(!contains(fieldsInfos, info)){
                    fieldsInfos.add(info);
                }
            } else if ( type.isAssignableFrom(Integer.class) || type.isAssignableFrom(int.class) ||
                    type.isAssignableFrom(Double.class) || type.isAssignableFrom(double.class) ||
                    type.isAssignableFrom(Long.class) || type.isAssignableFrom(long.class)
                    ) {
                FieldInfo info = new FieldInfo(fName, FieldType.DOUBLE);
                if(!contains(fieldsInfos, info)){
                    fieldsInfos.add(info);
                }
            } else if (type.isAssignableFrom(String.class)
                    || type.isAssignableFrom(Character.class)|| type.isAssignableFrom(char.class) ) {
                FieldInfo info = new FieldInfo(fName, FieldType.STRING);
                if(!contains(fieldsInfos, info)){
                    fieldsInfos.add(info);
                }
            } else if ( type.isAssignableFrom(Date.class)|| type.isAssignableFrom(Calendar.class)) {
                FieldInfo info = new FieldInfo(fName, FieldType.DATE);
                if(!contains(fieldsInfos, info)){
                    fieldsInfos.add(info);
                }
            } else if ( Material.class.getPackage().getName().equals(type.getPackage().getName())) {
                String typename =  type.getSimpleName();
                typename = Character.toLowerCase(typename.charAt(0)) + typename.substring(1);
                FieldInfo info = new FieldInfo(fName,typename, FieldType.OBJECT);
                if(!contains(fieldsInfos, info)){
                    fieldsInfos.add(info);
                }
            }
        }
    }

    private static boolean contains(List<FieldInfo> list, FieldInfo info) {
        for (FieldInfo object : list) {
            if (object.getName().equals(info.getName())) {
                return true;
            }
        }
        return false;
    }
}
