package com.qa.gorest.frameworkExcemptions;

public class APIFrameworkException extends RuntimeException {

	public APIFrameworkException(String mesg) {
		super(mesg);
	}
}
	//APIFrameworkException(String mesg)
	//↓
	//RuntimeException(String message)
	//↓
	//Exception(String message)
	//↓
	//Throwable(String message)
	//↓
	//Object()