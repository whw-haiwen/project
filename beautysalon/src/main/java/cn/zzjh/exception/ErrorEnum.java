package cn.zzjh.exception;

/**
 * 自定义异常类
 * @author 王海文
 *
 */
public enum ErrorEnum {
	/**
	 * "000000", "操作成功"
	 */
	OPERATION("000000", "操作成功"),	
	/**
	 * "000001", "用户名或密码错误"
	 */
	SUCCESS("000001", "用户名或密码错误"),	
	/**
	 * "000002", "数据不存在"
	 */
	NONEXISTENT("000002", "数据不存在"),	
	/**
	 * "000003", "无此操作权限"
	 */
	NO_AUTHORITY("000003", "无此操作权限"),	
	/**
	 * "000004", "非法操作"
	 */
	ILLEGAL_OPRATION("000004", "非法操作"),	
	/**
	 * "000101", "网咯超时！"
	 */
	AUTH_ERROR_IDENTIFYING_CODE("000101", "网咯超时！");  

	protected String code;
	protected String desc;

	ErrorEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static ErrorEnum parse(String code) {
		for (ErrorEnum e : ErrorEnum.values()) {
			if (e.getCode().equals(code)) {
				return e;
			}
		}
		
		return null;
	}
}

