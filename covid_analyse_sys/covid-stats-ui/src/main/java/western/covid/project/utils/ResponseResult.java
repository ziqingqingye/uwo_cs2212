package western.covid.project.utils;

/**
 * A class functions as common response result including response code and message attribute.
 * @author Tianci Du
 * @version 1.0
 */
public class ResponseResult {
	
	private Integer code;
	private String message;
	public ResponseResult() {
		
	}
	
	public ResponseResult(Integer code,String message) {
		this.code=code;
		this.message=message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
