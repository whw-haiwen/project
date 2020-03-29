package cn.zzjh.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

public class MapUtils {
	
	/**
	 * 将字符串封装为map，按照 key,value,key1,value1格式进行调用
	 * @param param
	 * @return
	 */
	public static Map<String, String> instance(String... param) {
		if (param.length == 0 || param.length % 2 != 0)
			return null;

		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < param.length; i++) {
			map.put(param[i], param[++i]);
		}

		return map;
	}

	/**
	 * 将对象封装为map，按照key,obj,key1,obj1格式进行调用
	 * @param param
	 * @return
	 */
	public static Map<String, Object> instanceObject(Object... param) {
		if (param.length == 0 || param.length % 2 != 0)
			return null;

		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < param.length; i++) {
			map.put(param[i].toString(), param[++i]);
		}

		return map;
	}

	/**
	 * 将Map转换为Bean<br>
	 * 若Map为空则返回<code>null</code><br>
	 * 转换以属性值为准, 不以setter方法为准
	 * 
	 * @param map
	 *            待转换的Map
	 * @param beanClass
	 *            转换成的Bean Class定义
	 * @return 转换完成的Bean
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T> T toBean(Map<String, ?> map, Class<T> beanClass)
			throws InstantiationException, IllegalAccessException {
		if (map == null || map.isEmpty())
			return null;

		T bean = beanClass.newInstance();

		Field[] fields = beanClass.getDeclaredFields();
		for (Field field : fields) {
			if (map.containsKey(field.getName())) {
				Object value = map.get(field.getName());
				field.setAccessible(true);
				field.set(bean, value);
			}
		}

		return bean;
	}

	/**
	 * 将bean转换为map
	 * @param bean
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static HashMap<String, Object> toHashMap(Object bean)
			throws IllegalArgumentException, IllegalAccessException {
		if (bean == null)
			return null;

		HashMap<String, Object> map = new HashMap<>();
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object value = field.get(bean);
			map.put(field.getName(), value);
		}

		return map;
	}
	
	/**
	 * 将json字符串转换为map
	 * @param json
	 * @return
	 */
	public static Map<String,Object> toHashMap(String json) {
		
		if(json == null) {
			return null;
		}
		JSONObject obj = JSONObject.parseObject(json);
		return toHashMap(obj);
	}
	
	/**
	 * 将json对象转换为map
	 * @param jsonObj
	 * @return
	 */
	public static Map<String, Object> toHashMap(JSONObject jsonObj) {
		
		if (jsonObj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		
		Set<Entry<String, Object>> entrys = jsonObj.entrySet();
		for (Entry<String, Object> entry : entrys) {
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}
	
	
	/**
	 * 将传入map的key变成小写
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Object> lowercaseMapKey(Map<String, Object> map) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : map.entrySet()) {
			rsMap.put(entry.getKey().toLowerCase(), entry.getValue());
		}
		return rsMap;
	}
	

	/**
	 * 将list中的map key转化成小写,同时处理clob转换为string
	 * 
	 * @param dataList
	 * @param transClob
	 * @return
	 */
	public static List<Map<String, Object>> lowercaseListMapKey(List<Map<String, Object>> dataList) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> dataMap : dataList) {
				resultList.add(lowercaseMapKey(dataMap));
		}
		return resultList;
	}
	/**
	 * 将T类型转换为map对象
	 * @param <T>
	 * @param dataList
	 * @return
	 */
	public static <T> Map<String, Object> Transformation(T t) {
		Gson gson = new Gson();
		return gson.fromJson(gson.toJson(t), Map.class);
	}
	
	/**
	 * 将map对象转换为T对象
	 */
	
	
	
}
