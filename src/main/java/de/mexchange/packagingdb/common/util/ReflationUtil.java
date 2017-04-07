package de.mexchange.packagingdb.common.util;

import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by Arthur on 7/13/2016.
 */
public class ReflationUtil {

    private static Logger logger = Logger.getLogger(ReflationUtil.class);

    private static final String BASE_PACKAGE = "de.mexchange.packagingdb.entity";

    public static Class getFieldType(String objName, String field) throws Exception {
        return getFieldType(Class.forName(BASE_PACKAGE + "." + objName), field);
    }

    public static Class getFieldType(Object obj, String field) throws Exception {
        return getFieldType(obj.getClass(), field);
    }

    public static Class getFieldType(Class cls, String field) throws Exception {
        Class type = null;
        while (cls != null && type == null) {
            try {
                type = cls.getDeclaredField(field).getType();
            } catch (NoSuchFieldException nsfe) {
                logger.error(nsfe);
            }
            cls = cls.getSuperclass();
        }
        return type;
    }

    public static String getTableName(String entity) {
        try {
            Class clazz = Class.forName(BASE_PACKAGE + "." + entity);
            Annotation[] annotations = clazz.getAnnotations();
            if (annotations == null || annotations.length == 0) return entity;

            for (Annotation annotation : annotations) {
                if (annotation instanceof Table) {
                    Table table = (Table)annotation;
                    return table.name();
                }
            }
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }
        return null;
    }

    public static String getColumnName(String entity, String fieldname) {
        try {
            Class clazz = Class.forName(BASE_PACKAGE + "." + entity);
            Field field = clazz.getDeclaredField(fieldname);
            Annotation[] annotations = field.getAnnotations();
            if (annotations == null || annotations.length == 0) return entity;

            for (Annotation annotation : annotations) {
                if (annotation instanceof Column) {
                    Column column = (Column)annotation;
                    return column.name();
                }
            }
        } catch (ClassNotFoundException e) {
            logger.error(e);
        } catch (NoSuchFieldException e) {
            logger.error(e);
        }
        return null;
    }
}
