package com.societe.generale.exception;


public class SocieteGeneraleException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5686363051519230930L;
public Exception orignalException;
	
	public Exception getOrignalException() {
		return orignalException;
	}

	public void setOrignalException(Exception orignalException) {
		this.orignalException = orignalException;
	}
	
	/**
	 * This method return the UPOVException object as string
	 */
	public String toString(){
		String exp = "Base class exception :";
		if(orignalException != null)
			exp+=" Original Exception is :"+orignalException.getMessage()+" Original Cause is :"+orignalException.getCause();
		return exp;
	}
	
}
