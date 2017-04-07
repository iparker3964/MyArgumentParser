package edu.jsu.mcis;

import java.lang.*;
import java.util.*;
import java.io.*;



public class ArgumentParser{
    private List<Argument> listArgs;
    private String programName;
    private String programDescription;
	private String argumentName;
    private String argumentDescription;
	private String message;
	private String help;
	boolean error;
	boolean helpError;
	
	public ArgumentParser() {
                    
        listArgs = new ArrayList<Argument>();
		programName = "";
		programDescription = "";
		argumentDescription = "";
		argumentName = "";
		message = "";
		help = "";
          
    }
	public void addArg(String name, String description, Argument.Type type) {
        Argument temp = new Argument();
        temp.setArgumentName(name);
        temp.setArgumentDescription(description);
        temp.setArgumentType(type);
		addArg(temp);
    }
	
	public void addArg(Argument args){
		listArgs.add(args);
	}
	public void parse(String[] args) throws RuntimeException{ 
		String name = "";
		//String message = "";
		for(Argument k : listArgs){
			name += " " + k.getArgumentName();
		}  
		
		
		
		for(int i = 0; i < args.length; i++){			
			if(args[i].equals("-h") || args[i].equals("--help")){
				String argumentDescriptions = "";				
				for(Argument k : listArgs){
					argumentDescription += "\n" + k.getArgumentDescription();
					
				}  
				
				help += "usage: java " + getProgramName() + name + getProgramDescription() + "\n" + "positional arguments:" + getArgumentDescription();
				help = help.trim();
				throw new HelpMessageException(help);
			}
		}
		
			//String message = "";
			if (args.length > listArgs.size()){
				String extra = "";
				
				for (int i = listArgs.size(); i < args.length; i++){
					extra += args[i] + " ";
				} 
				message += "usage: java " + getProgramName() + name + "\n" + getProgramName() + ".java: error: unrecognized arguments: " + extra;
				message = message.trim();
				throw new IllegalArgumentException(message);
			}
				
			else if (args.length < listArgs.size()){
				String less = "";
				
				for (int i = listArgs.size(); i < args.length; i++){
					less += args[i] + " " ; 
				}
				message += ".java: error: unrecognized arguments: "  + less;
				message = message.trim();
				throw new IllegalArgumentException(message);
				
			}
	   
			else {
				for(int i=0; i<args.length; i++){
					listArgs.get(i).setArgumentValue(args[i]);
				}	
			}		
	}

		
	public Argument getArg(String name){
		for(int i=0; i<listArgs.size(); i++){
			if(name.equals(listArgs.get(i).getArgumentName())){
				return listArgs.get(i);
			}
		}
		return null;
		// at some point, we need to throw an exception - argument doesn't exist 
	}	
	public int getNumArguments(){
		return listArgs.size();
	}
	
	public void setArgumentName(String name){
		argumentName = name;
	}
	public String getArgumentName(){
		return argumentName;
	}
	public void setArgumentDescription(String description){
		argumentDescription = description;
	}
	public String getArgumentDescription(){
		return argumentDescription;
	}
	
	public void setProgramName(String nameProgram){
		programName = nameProgram;
	}
	public String getProgramName(){
		return programName;
	}	
	
	public void setProgramDescription(String descriptionProgram){
		programDescription = descriptionProgram;
	}
	
	public String getProgramDescription(){
		return programDescription;
	}	
	
	public String getMessage(){
		return message;
	}
	public String getHelpMessage(){
		return help;
	}
	
	
	
}