package fis.java.topic3.exception;

public class CustomException extends Exception{
	private String message;
	
	public CustomException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}
	
	
}
