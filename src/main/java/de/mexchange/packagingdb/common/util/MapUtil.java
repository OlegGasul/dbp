package de.mexchange.packagingdb.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arthur on 7/13/2016.
 */
public class MapUtil {

    public static Map map(Object key, Object val) {
        Map map = new HashMap();
        map.put(key, val);
        return map;
    }
    public static Map map(Object ...args) {
        Map map = new HashMap();
        return map;
    }
}
