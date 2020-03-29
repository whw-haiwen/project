package cn.zzjh.result;

import java.io.Serializable;

import cn.zzjh.exception.ErrorEnum;



/**
 * 返回格式
 * @author 王海文
 */
public class JsonResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Boolean flag;	//当前操作是否完成（true false）
	protected Object data;		//具体数据
	protected String code;	//类型编号
	protected String message;	//描述

	public JsonResult() {

	}

	public static <Object> JsonResult<Object> success(Object data, String message) {
		JsonResult<Object> jsonResult = new JsonResult<>();
		jsonResult.setFlag(true);
		jsonResult.setData(data);
		jsonResult.setCode(ErrorEnum.OPERATION);
		jsonResult.setMessage(message);

		return jsonResult;
	}

	public static <T> JsonResult<T> error(T data, ErrorEnum code) {
		JsonResult<T> jsonResult = new JsonResult<>();
		jsonResult.setFlag(false);
		jsonResult.setData(data);
		jsonResult.setCode(code);
		jsonResult.setMessage(code.getDesc());

		return jsonResult;
	}

	public static <T> JsonResult<T> error(T data, ErrorEnum code, String message) {
		JsonResult<T> jsonResult = new JsonResult<>();
		jsonResult.setFlag(false);
		jsonResult.setData(data);
		jsonResult.setCode(code);
		jsonResult.setMessage(message);

		return jsonResult;
	}

	public static <T> JsonResult<T> error(ErrorEnum code) {
		JsonResult<T> jsonResult = new JsonResult<>();
		jsonResult.setFlag(false);
		jsonResult.setCode(code);
		jsonResult.setMessage(code.getDesc());

		return jsonResult;
	}

	public static <T> JsonResult<T> error(ErrorEnum code, String message) {
		JsonResult<T> jsonResult = new JsonResult<>();
		jsonResult.setFlag(false);
		jsonResult.setCode(code);
		jsonResult.setMessage(message);

		return jsonResult;
	}

	public void setSuccessData(Object data) {
		this.flag = true;
		this.setCode(ErrorEnum.OPERATION);
		this.data = data;
		this.message = "操作成功";
	}

	public void setErrorMessage(ErrorEnum code) {
		this.flag = false;
		this.setCode(code);
		this.message = code.getDesc();
	}

	public void setErrorMessage(ErrorEnum code, String message) {
		this.flag = false;
		this.setCode(code);
		this.message = message;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCode(ErrorEnum code) {
		this.code = code.getCode();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

