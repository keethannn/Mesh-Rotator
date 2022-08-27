package assignment;

import java.lang.Exception;

@SuppressWarnings("serial")

public class WrongFileFormatException extends Exception{ 
	
	String message;
	
	public WrongFileFormatException()
	{
		this.message = "Wrong file type";
	}

}
