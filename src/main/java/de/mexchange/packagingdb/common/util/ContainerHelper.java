package de.mexchange.packagingdb.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import de.mexchange.packagingdb.entity.BigBagsEntity;
import de.mexchange.packagingdb.entity.BulkEntity;
import de.mexchange.packagingdb.entity.ConicalCanisterEntity;
import de.mexchange.packagingdb.entity.ConicalCansEntity;
import de.mexchange.packagingdb.entity.ConicalClampingRingEntity;
import de.mexchange.packagingdb.entity.ContainerEntity;
import de.mexchange.packagingdb.entity.CubicEntity;
import de.mexchange.packagingdb.entity.CylindricalClampingRingEntity;
import de.mexchange.packagingdb.entity.CylindricalEntity;

/**
 * Created by Serozh on 7/12/2016.
 */
public class ContainerHelper {


    private static ContainerHelper containerHelper = new ContainerHelper();

    private Map<String, List<String>> containerFields;
    private Map<String, Map<String, String>> fieldsMap;
    private Map<String, String> tablesMap;
    private List<String> parentContainerFields;

    private Set<String> containers;

    private ContainerHelper () {
        containerFields = new HashMap<>();
        containers = new HashSet<>();
        parentContainerFields = new ArrayList<>();

        //fields and tables mapping
        fieldsMap = new HashMap<>();
        tablesMap = new HashMap<>();

        // load container declared fields
        loadContainerFields();

        // load containers
        initContainers();

        // init entity - table map
        initTablesMap();

        // init entities fields - columns map
        initFieldsMap();
    }

    public static ContainerHelper getInstance() {
        return containerHelper;
    }

    private void initContainers() {
        containers.add(CubicEntity.class.getSimpleName());
        containers.add(CylindricalEntity.class.getSimpleName());
        containers.add(BigBagsEntity.class.getSimpleName());
        containers.add(BulkEntity.class.getSimpleName());
        containers.add(ConicalCanisterEntity.class.getSimpleName());
        containers.add(ConicalCansEntity.class.getSimpleName());
        containers.add(ConicalClampingRingEntity.class.getSimpleName());
        containers.add(CylindricalClampingRingEntity.class.getSimpleName());
    }

    private void loadContainerFields() {
        containerFields.put(CubicEntity.class.getSimpleName(),loadContainerFields(CubicEntity.class));
        containerFields.put(CylindricalEntity.class.getSimpleName(),loadContainerFields(CylindricalEntity.class));
        containerFields.put(BigBagsEntity.class.getSimpleName(),loadContainerFields(BigBagsEntity.class));
        containerFields.put(BulkEntity.class.getSimpleName(),loadContainerFields(BulkEntity.class));
        containerFields.put(ConicalCanisterEntity.class.getSimpleName(),loadContainerFields(ConicalCanisterEntity.class));
        containerFields.put(ConicalCansEntity.class.getSimpleName(),loadContainerFields(ConicalCansEntity.class));
        containerFields.put(ConicalClampingRingEntity.class.getSimpleName(),loadContainerFields(ConicalClampingRingEntity.class));
        containerFields.put(CylindricalClampingRingEntity.class.getSimpleName(),loadContainerFields(CylindricalClampingRingEntity.class));

        containerFields.put(ContainerEntity.class.getSimpleName(),loadContainerFields(ContainerEntity.class));
        parentContainerFields = loadContainerFields(ContainerEntity.class);
    }

    private List<String> loadContainerFields(Class<? extends ContainerEntity> clazz) {
        List<String> __fields = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            __fields.add(field.getName());
        }
        return __fields;
    }

    public List<String> getContainerFields(Class<? extends ContainerEntity> clazz) {
        return containerFields.get(clazz.getSimpleName());
    }

    public boolean hasContainerFields(Class<? extends ContainerEntity> clazz, String fieldName) {
        if (containerFields.get(clazz.getSimpleName()) == null) {
            return false;
        }
        return containerFields.get(clazz.getSimpleName()).contains(fieldName);
    }

    public boolean isCommonField(String fieldName) {
        return parentContainerFields.contains(fieldName);
    }

    public List<String> getContainersContainingField(String fieldName) {
        List<String> containers = new ArrayList<>();

        for (Entry<String, List<String>> entry : containerFields.entrySet()) {
            List<String> fields = containerFields.get(entry.getKey());
            if (fields.contains(fieldName)) {
                containers.add(entry.getKey());
            }
        }
        return containers;
    }

    public Set<String> getContainers() {
        return containers;
    }

    public void initTablesMap() {
        Iterator<String> iterator = containers.iterator();
        while (iterator.hasNext()) {
            String entity =  iterator.next();
            String tableName = ReflationUtil.getTableName(entity);
            if (tableName == null) {
                continue;
            }
            tablesMap.put(entity, tableName);
        }
    }

    public void initFieldsMap() {
        Iterator<String> iterator = containers.iterator();
        Map<String, String> fieldMapByContainer;
        while (iterator.hasNext()) {
            String entity =  iterator.next();

            List<String> fields = containerFields.get(entity);
            fieldMapByContainer = fieldsMap.get(entity);
            if (fieldMapByContainer == null) {
                fieldMapByContainer = new HashMap<String, String>();
            }
            for (String field : fields) {
                String fieldName = ReflationUtil.getColumnName(entity, field);
                if (fieldName == null) {
                    continue;
                }
                fieldMapByContainer.put(field, fieldName);
            }
            fieldsMap.put(entity, fieldMapByContainer);
        }
    }

    public Map<String, String> getTablesMap() {
        return tablesMap;
    }

    public Map<String, Map<String, String>> getFieldsMap() {
        return fieldsMap;
    }

    public Map<String, String> getFieldsMap(String container) {
        return fieldsMap.get(container);
    }

}
