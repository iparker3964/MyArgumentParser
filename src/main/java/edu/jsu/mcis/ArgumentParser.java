package edu.jsu.mcis;

import java.lang.*;
import java.util.*;
import java.io.*;



public class ArgumentParser{
    private List<Argument> listArgs;
    private String programName;
    private String programDescription;
	private String message;
	private String help;
	
	public ArgumentParser() {
                    
        listArgs = new ArrayList<Argument>();
		programName = "";
		programDescription = "";
		message = "";
		help = "";
          
    }
	public void addArg(String name, String description, Argument.Type type) {
        Argument temp = new Argument();
        temp.setName(name);
        temp.setDescription(description);
        temp.setType(type);
		addArg(temp);
    }
	
	public void addArg(Argument args){
		listArgs.add(args);
	}
	public void parse(String[] args) throws RuntimeException{ 
		
		for(int i = 0; i < args.length; i++){			
			if(args[i].equals("-h") || args[i].equals("--help")){
				String help = "usage: java " + getProgramName() + "\n" + getProgramDescription() + "\npositional arguments:";
				for(Argument k : listArgs){
					help += "\n" + k.getDescription();
				}  
				help = help.trim();
				throw new HelpMessageException(help);
			}
		}
		if (args.length > listArgs.size()){
			String extra = "";
			String message = "usage: java " + getProgramName() + "\n" + getProgramDescription();
			for (int i = listArgs.size(); i < args.length; i++){
				extra += args[i] + " "  ;
			}
			message = message + extra;
			message = message.trim();
			throw new IllegalArgumentException(message);
		}
				
		else if (args.length < listArgs.size()){
			String less = "";
			String message = "usage: java " + getProgramName() + "\n" + getProgramDescription();
			for (int i = listArgs.size(); i < args.length; i++){
				less += args[i] + " " ; 
			}
			message = message + less;
			message = message.trim();
			throw new IllegalArgumentException(message);
			
		}
	   
		else {
			for(int i=0; i<args.length; i++){
				listArgs.get(i).setValue(args[i]);
			}	
		}		
	}

		
	public Argument getArg(String name){
		for(int i=0; i<listArgs.size(); i++){
			if(name.equals(listArgs.get(i).getName())){
				return listArgs.get(i);
			}
		}
		return null;
		// at some point, we need to throw an exception - argument doesn't exist 
	}	
	public int getNumArguments(){
		return listArgs.size();
	}
	public String getProgramName(){
		return programName;
	}
	public String getProgramDescription(){
		return programDescription;
	}
	public void setProgramDescription(String description){
		programDescription = description;
	}
	
	public void setProgramName(String name){
		programName = name;
	}	
	
	public String getMessage(){
		return message;
	}
	public String getHelpMessage(){
		return help;
	}
	
}