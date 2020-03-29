package cn.zzjh.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.zzjh.dao.UsersDao;
import cn.zzjh.exception.BusinessException;
import cn.zzjh.exception.ErrorEnum;
import cn.zzjh.utils.UUIDUtil;

@Service
@Transactional
public class LoginService {
	
	@Resource
	private UsersDao usersDao;
	
	public String login(String account_number,String password) throws BusinessException {
		Map<String, Object> param = new HashMap<>();
		param.put("account_number", account_number);
		
		Map<String, Object> map = usersDao.findByAccountNumber(param);
		if(map == null ||!String.valueOf(map.get("password")).equals(DigestUtils.md5Hex(password))){
			throw new BusinessException(ErrorEnum.SUCCESS);
		}
		
		String token = UUIDUtil.randReplacedLower();
		String values = JSON.toJSONString(map);
		param.put("token", token);
		param.put("token_values", values);
		return token;
	}
	
	/**
	 * 退出
	 * @param request
	 */
	public void signOut(HttpServletRequest request) {
		Cookie[] cookie = request.getCookies();
    	for (int i = 0; i < cookie.length; i++) {
    	Cookie cook = cookie[i];
	    	if(cook.getName().equalsIgnoreCase("token")){ //获取键 
	    	}
    	}
	}
	
	public Map<String, Object> findByUserInformation(HttpServletRequest request) throws BusinessException {
		
		String values=null;
		Cookie[] cookie = request.getCookies();
    	for (int i = 0; i < cookie.length; i++) {
    	Cookie cook = cookie[i];
	    	if(cook.getName().equalsIgnoreCase("token")){ //获取键 
	    		values = findByToken(cook.getValue());
	    	}
    	}
    	if(values == null){
    		throw new BusinessException(ErrorEnum.ILLEGAL_OPRATION);
    	}
    	Map<String, Object> map = JSON.parseObject(values, Map.class);
		return map;
	}
	
	/**
	 * 通过token查询存到数据库中的jsion字符串
	 * @param token
	 * @return
	 */
	public String findByToken(String tooken){
		Map<String, Object> map = new HashMap<>();
		map.put("tooken", tooken);
		
		return null;
	}
}
