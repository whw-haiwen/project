package cn.zzjh.dao;

import java.util.Map;

public interface UsersDao {
	Map<String, Object> findByAccountNumber(Map<String, Object> param);

}
