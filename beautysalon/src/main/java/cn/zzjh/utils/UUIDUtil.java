package cn.zzjh.utils;

import java.util.UUID;

public class UUIDUtil {
	public static String randReplacedLower(){
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
}
