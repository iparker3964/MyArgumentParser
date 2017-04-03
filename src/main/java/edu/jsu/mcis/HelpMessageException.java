package edu.jsu.mcis;


public class HelpMessageException extends RuntimeException { 

    public HelpMessageException () {
		super ();
	
    }
	public HelpMessageException (String message) {
		super (message);
	}
	
}