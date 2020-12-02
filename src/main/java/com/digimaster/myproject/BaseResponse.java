package com.digimaster.myproject;

public class BaseResponse<T> {

	private int code; 
	private String message;
	private boolean success;
//	private T data;				
	
	private Iterable<T> data;
	
	
	public Iterable<T> getData() {
		return data;
	}
	public void setData(Iterable<T> data) {
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
//	public T getData() {
//		return data;
//	}
//	public void setData(T data) {
//		this.data = data;
//	}
 
}
