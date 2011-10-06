package com.es.zumeh.client.model.work;

public class InvalidContentException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8614631483197971124L;
	
	public InvalidContentException(String message) {
		super(message);
	}
	
	public InvalidContentException() {
		super();
	}
}
