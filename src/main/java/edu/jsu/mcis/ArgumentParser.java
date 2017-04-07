package edu.jsu.mcis;

import java.lang.*;
import java.util.*;
import java.io.*;



public class ArgumentParser{
    private List<Argument> listArgs;
    private String programName;
    private String programDescription;
	
	public ArgumentParser() {
        listArgs = new ArrayList<Argument>();
		programName = "";
		programDescription = "";
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
	
	/* private String buildMessage(String[] args) {
		
	} */
	
	public void parse(String[] args) { 
		String name = "";
		
		for(Argument k : listArgs){
			name += " " + k.getName();
		}  
		
		for(int i = 0; i < args.length; i++){			
			if(args[i].equals("-h") || args[i].equals("--help")){
				String argumentDescriptions = "";				
				for(Argument k : listArgs){
					argumentDescriptions += "\n" + k.getDescription();
				}  
				String help = "usage: java " + getProgramName() + name + getProgramDescription() + "\n" + "positional arguments:" + argumentDescriptions;
				help = help.trim();
				throw new HelpMessageException(help);
			}
		}
			
		if (args.length > listArgs.size()){
			String extra = "";
			for (int i = listArgs.size(); i < args.length; i++){
				extra += args[i] + " ";
			} 
			String message = "usage: java " + getProgramName() + name + "\n" + getProgramName() + ".java: error: unrecognized arguments: " + extra;
			message = message.trim();
			throw new IllegalArgumentException(message);
		}
		else if (args.length < listArgs.size()){
			String less = "";
			for (int i = listArgs.size(); i < args.length; i++){
				less += args[i] + " " ; 
			}
			String message = ".java: error: unrecognized arguments: "  + less;
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
}