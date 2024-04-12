package com.sundirect.crm.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;



public class ApiReturn {
	private Integer code;
	private String status;
	private String message;
	private List<String> errors = new ArrayList<String>(0);
	private String requestId = MDC.get("requestId");
	private String timestamp = Instant.now().atZone(ZoneId.systemDefault()).toString();
	private List list;
	private List<? extends Object> results = new ArrayList<>();
	//private float balance;
	

	public ApiReturn() {
		this(HttpStatus.OK.value(), ApiConstants.Status.SUCCESS.name(), ApiConstants.Status.SUCCESS.name(),
				new ArrayList<String>(0));
	}

	@Override
	public String toString() {
		return "ApiReturn [code=" + code + ", status=" + status + ", message=" + message + ", errors=" + errors
				+ ", requestId=" + requestId + ", timestamp=" + timestamp + ", list=" + list + ", results=" + results
				+ "]";
	}

	public ApiReturn(Integer code, String status, String message) {
		this(code, status, message, new ArrayList<String>(0));
	}
	
	public ApiReturn(Integer code, String status, List list) {
		super();
		this.code=code;
		this.status=status;
		this.list=list;
		
	}
	
	public ApiReturn(Integer code, String status, Object obj) {
		super();
		this.code=code;
		this.status=status;
		//this.obj= obj;
		
	}


	public ApiReturn(Integer code, String status, String message, List<String> errors) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
		this.errors = errors;		
	}
	
	public ApiReturn(Integer code, String status, String message, Object results) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
		//this.results = results;		
	}
	
	/*
	 * public ApiReturn(Integer code, String status, String message, Object
	 * results,float balance) { super(); this.code = code; this.status = status;
	 * this.message = message; this.balance=balance; //this.results = results; }
	 */

}
