package cn.zzjh.exception;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常捕获（直接是异常捕获和打印日志）
 *
 */
public class ExceptionResolver implements HandlerExceptionResolver{

	
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  Exception ex) {    
        ModelAndView mv = new ModelAndView();             
        /*  使用response返回    */  
        response.setStatus(HttpStatus.OK.value()); //设置状态码  
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
        response.setCharacterEncoding("UTF-8"); //避免乱码  
        response.setHeader("Cache-Control", "no-cache, must-revalidate"); 
        
        String message=ex.getMessage();	//如果有值说明是自定义跑出异常
        
        if(message == null) {
        	StackTraceElement[] st = ex.getStackTrace();
        	for (StackTraceElement stackTraceElement : st) {
        		String exclass = stackTraceElement.getClassName();
        		String method = stackTraceElement.getMethodName();
        		System.out.println(new Date() + ":" + "[类:" + exclass + "]调用"
        				+ method + "时在第" + stackTraceElement.getLineNumber()
        				+ "行代码处发生异常!异常类型:" + ex.getClass().getName());
        		break;  //只获取具体行错误代码     将其错误日志保存到数据库中一份，（log4j虽然有，最还还是保存一个错误日志到数据库中）
        	}
        	message="系统繁忙，稍后再试！";
        }
        try {  
            response.getWriter().write("{\"flag\":false,\"data\":0,\"code\":0,\"message\":\""+message+"\"}");  
        } catch (IOException e) {  
        }  
        return mv;  
    }  
    
}
