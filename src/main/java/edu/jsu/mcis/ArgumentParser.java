package edu.jsu.mcis;

import java.lang.*;
import java.util.*;
import java.io.*;



public class ArgumentParser{
    private List<Argument> listArgs;
    private String programName;
    private String programDescription;
	private int numOfArgs;
	private List<String> positionalKeys;
	
	public ArgumentParser() {
		positionalKeys = new ArrayList<String>();
        listArgs = new ArrayList<Argument>();
		programName = "";
		programDescription = "";
		numOfArgs = 0;
    }
	public void addArg(String name, String description, Boolean optional, Argument.Type type) {
        Argument temp = new Argument();
        temp.setName(name);
        temp.setDescription(description);
        temp.setType(type);
		temp.setOptional(optional);
		addArg(temp);
    }
	
	public void addArg(Argument args){
		listArgs.add(args);
	}
	
	/* private String buildMessage(String[] args) {
		
	} */
	
	public void parse(String[] args) { 
		addPositionalKeys();
		String name = "";
		
		for(Argument k : listArgs){
			if(!k.getOptional())
				name += " " + k.getName();
		}  
		
		int num = 0;
		int posCount = 0;
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("-h") || args[i].equals("--help")){
				String argumentDescriptions = "";				
				
				for(Argument k : listArgs){
					if(!k.getOptional())
						argumentDescriptions += "\n" + k.getDescription();
				}  
				String help = "usage: java " + getProgramName() + name + getProgramDescription() + "\n" + "positional arguments:" + argumentDescriptions;
				help = help.trim();
				throw new HelpMessageException(help);
			}
			String optionalArgName;
			String optionalArgValue;
			if(args[i].contains("--") && !args[i].contains("--help")){
				optionalArgName = args[i].substring(2);
				i++;
				optionalArgValue = args[i];
			}
			else if(posCount >= numOfPositional()){
				String extra = "";
				for (int j = i; j < args.length; j++){
					extra += args[j] + " ";
				} 
				String message = "usage: java " + getProgramName() + name + "\n" + getProgramName() + ".java: error: unrecognized arguments: " + extra;
				message = message.trim();
				throw new IllegalArgumentException(message);
			}
			else{
				//throw new IllegalArgumentException(Integer.toString(positionalKeys.size()));
				optionalArgName = positionalKeys.get(posCount);
				//optionalArgName = listArgs.get(num).getName();
				optionalArgValue = args[i];
				posCount++;
				
			}
			int placement = getPlace(optionalArgName);
			Argument temp = listArgs.get(placement);
			if(convert(optionalArgValue, temp.getType())){
				listArgs.get(placement).setValue(optionalArgValue);
				
			}
			
				
			else{
				String badArg = listArgs.get(placement) + " ";
				String message = "usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: argument width: invalid float value:" + badArg ;
				message = message.trim();
				throw new NumberFormatException();
			}
			num++;
		}
		if(posCount < numOfPositional()){
			String less = "";
			for (int k = listArgs.size(); k < args.length; k++){
				less += args[k] + " " ; 
			}
			String message = ".java: error: unrecognized arguments: "  + less;
			message = message.trim();
			throw new IllegalArgumentException(message);
		}
		
		
		
	}
	
	public void addPositionalKeys(){
		for(int i = 0; i < listArgs.size(); i++){
			if(listArgs.get(i).getOptional() == false){
				positionalKeys.add(listArgs.get(i).getName());
			}
		}
	}
	
	public int numOfOptional(){
		int count = 0;
		for(int i = 0; i < listArgs.size(); i++){
			if(listArgs.get(i).getOptional() == true){
				count++;
			}
		}
		return count;
	}
	
	public int numOfPositional(){
		int count = 0;
		for(int i = 0; i < listArgs.size(); i++){
			if(listArgs.get(i).getOptional() == false){
				count++;
			}
		}
		return count;
	}
	
	public boolean convert(String value, Argument.Type type){
		Object o;
		if(type == Argument.Type.INTEGER){
			try{
				o = Integer.parseInt(value);
				return true;
			}
			catch(NumberFormatException e){ 
				return false;
			}
		}
		else if(type == Argument.Type.FLOAT){
			try{
				o = Float.valueOf(value);
				return true;
			}
			catch(NumberFormatException e){
				return false;
			}
		}
		else if(type == Argument.Type.BOOLEAN){
			if(value.contains("true") || value.contains("false")){
				return true;
			}
			else{
				return false;
			}
		}
		else if(type == Argument.Type.STRING){
			return true;
		}
		
		return false;
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
	
	public int getPlace(String name){
		for(int i = 0; i < listArgs.size(); i++){
			if(name.equals(listArgs.get(i).getName())){
				return i;
			}
		}
		return 0;
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