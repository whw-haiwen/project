package cn.zzjh.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zzjh.exception.BusinessException;
import cn.zzjh.exception.ErrorEnum;
import cn.zzjh.result.JsonResult;
import cn.zzjh.service.impl.LoginService;
import cn.zzjh.utils.SesionUtils;
/**
 * 用户登录、基本信息 相关类
 * @author 王海文
 *
 */

@Controller
@RequestMapping("/log")
public class LoginController {
	
	@Resource
	private LoginService loginService;
	
	/**
	 * 登录
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult<T> login(
			@RequestParam(value = "project_number",required = true)String project_number,
			@RequestParam(value = "password",required = true)String password,
			HttpServletResponse response) throws BusinessException{
		JsonResult<T> result = new JsonResult<T>();
		String token = loginService.login(project_number,password);
		Cookie cookie = new Cookie("token", token);
		cookie.setPath("/");
		response.addCookie(cookie); 
		result.setSuccessData(null);
		return result;
	} 
	
	
	/**
	 * 退出
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/signOut")
	@ResponseBody
	public JsonResult<T> signOut(HttpServletRequest request) throws BusinessException{
		JsonResult<T> result = new JsonResult<T>();
		loginService.signOut(request);
		SesionUtils.deleteObject("");
		result.setSuccessData(null);
		return result;
	} 
	
	/**
	 * 获取用户基本信息(必须在登录后才能使用)
	 */
	@RequestMapping("/findByUserInformation")
	@ResponseBody
	public JsonResult<T> findByUserInformation(HttpServletRequest request) throws BusinessException{
		JsonResult<T> result = new JsonResult<T>();
		Map<String, Object> param = loginService.findByUserInformation(request);
		result.setSuccessData(param);
		return result;
	} 
}
