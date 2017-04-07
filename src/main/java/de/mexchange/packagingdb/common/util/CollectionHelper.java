package de.mexchange.packagingdb.common.util;

import de.mexchange.packagingdb.domain.AbstractModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Garik on 4/30/16.
 */
public class CollectionHelper {

	public static boolean isEmpty(Collection<?> list) {
		return list == null || list.size() == 0;
	}

	public static boolean isNotEmpty(Collection<?> list) {
		return !isEmpty(list);
	}

	/**
	 * Excludes required objects from the list
	 * @param list
	 * @param models
	 * @param <T extends AbstractModel>
	 * @return
	 */
	public static <T extends AbstractModel> boolean exclude(List<T> list, T ... models) {
		if (isEmpty(list)) {
			return false;
		}
		if (models == null || models.length == 0) {
			return false;
		}

		List<Long> excludedIDs = new ArrayList<>();
		for (T model : models) {
			excludedIDs.add(model.getId());
		}

		boolean changed = false;
		Iterator<T> iterator = list.iterator();
		while (iterator.hasNext()) {
			T t = iterator.next();
			if (excludedIDs.contains(t.getId())) {
				iterator.remove();
				changed = true;
			}
		}
		return changed;
	}

	public static String getContentAsString(Object[] array, String delimiter, boolean trimContents) {
		if (array == null || array.length == 0)
			return "";
		if (delimiter == null || delimiter.length() == 0)
			delimiter = ",";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length - 1; i++) {
			if (trimContents) {
				sb.append(String.valueOf(array[i]).trim());
			} else {
				sb.append(array[i]);
			}
			sb.append(delimiter);
		}
		sb.append(array[array.length - 1]);

		return sb.toString();
	}
}
