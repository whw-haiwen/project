package cn.zzjh.utils;

import java.util.Collection;

public class CollectionUtils {
	public static boolean isBlank(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isNotBlank(Collection<?> collection) {
		return !isBlank(collection);
	}
}
