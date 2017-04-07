package de.mexchange.packagingdb.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.exception.InvalidParameterException;

/**
 * Created by Arthur on 7/17/2016.
 */
public class QueryUtil extends QueryConstants {

    private static ContainerHelper containerHelper = ContainerHelper.getInstance();

    public static String buildQuery(Map queryMap, String operator) throws InternalErrorException {
        StringBuilder builder = new StringBuilder();
        buildResult(queryMap, operator, builder);
        buildNativeQuery(queryMap, operator, builder);
        limitCredentials(queryMap, builder);
        return builder.toString();
    }
    public static String buildCountQuery(Map queryMap, String operator) throws InternalErrorException {
        StringBuilder builder = new StringBuilder();
        buildResultCount(queryMap, operator, builder);
        buildNativeQuery(queryMap, operator, builder);
        return builder.toString();
    }

    public static void buildResult(Map queryMap, String operator, StringBuilder builder) throws InternalErrorException {
        builder.append("SELECT c.id,c.designation,c.global_code,c.local_code,c.nominal_volume,c.sap_code,c.status_id,c.business_unit_id,c.location_id,c.company_id,c.packaging_form_id FROM container c ");
    }

    public static void buildResultCount(Map queryMap, String operator, StringBuilder builder) throws InternalErrorException {
        builder.append("SELECT count(c.id) FROM container c ");
    }

    public static void buildNativeQuery(Map queryMap, String operator, StringBuilder builder) throws InternalErrorException {
        StringBuilder where = new StringBuilder("WHERE");

        Set<String> keySet = queryMap.keySet();

        Iterator<String> iterator = keySet.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (key.equals(LIMIT) || key.equals(OFFSET)) {
                continue;
            }

            String tblName = containerHelper.getTablesMap().get(key);
            // JOIN tables
            builder.append("\n LEFT JOIN ");
            String alias = tblName + "_" + index++;
            builder.append(tblName).append(" ").append(alias).append(" ON c.id = ").append(String.format("%s.id ", alias));

            Map subQueryMap = (Map) queryMap.get(key);
            if (subQueryMap == null) {
                throw new InvalidParameterException("invalid query map for entity : " + key);
            }

            if (where.length() > 5) {
                where.append("\n OR \n");
            }

            where.append(buildCriteria(subQueryMap, alias, key, operator));

        }
        builder.append(where);
    }

    private static String buildCriteria(Map<String, Object> queryMap, String alias, String entity,
            String operator) throws InternalErrorException {
        StringBuilder builder = new StringBuilder("(");
        Set<String> keySet = queryMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            // fieldName
            String key = iterator.next();
            String fieldName = containerHelper.getFieldsMap(entity).get(key);
            if (fieldName == null) {
                throw new InternalErrorException("Invalid Search Field " + key);
            }

            Object value = queryMap.get(key);
            if (index > 0) {
                builder.append(" AND ");
                index ++;
            }
            String condition = getConditionBy(operator, value);
            builder.append(String.format(" %s.%s ", alias, fieldName)).append(condition);
        }
        return builder.append(")").toString();
    }

    private static void limitCredentials(Map queryMap, StringBuilder builder) {
        Integer limit, offset;
        limit = (Integer) queryMap.get(LIMIT);
        offset = (Integer) queryMap.get(OFFSET);
        if (limit != null) {
            builder.append(LIMIT).append(" ").append(limit);
            if (offset != null) {
                builder.append(OFFSET).append(" ").append(offset);
            }
        }
    }

}
