package edu.jsu.mcis;

import java.lang.*;
import java.util.*;
import java.io.*;



public class ArgumentParser{
    private List<Argument> listArgs;
    private String programName;
    private String programDescription;
	private int numOfArgs;
	
	public ArgumentParser() {
        listArgs = new ArrayList<Argument>();
		programName = "";
		programDescription = "";
		numOfArgs = 0;
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
		int num = 0;
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
			
			if(args[i].contains("--") && !args[i].contains("--help")){
				String optionalArgName = args[i].substring(2);
				String optionalArgValue = args[i+1];
				i++;
				Object value;
				numOfArgs++;
				if(convert(optionalArgValue, Argument.Type.INTEGER)){
					Argument temp = new Argument();
					value = Integer.parseInt(optionalArgValue);
					listArgs.get(i).setValue(value.toString());
					/* temp.setName(optionalArgName);
					temp.setType(Argument.Type.INTEGER);
					addArg(temp); */					
				}
				else if(convert(optionalArgValue, Argument.Type.FLOAT)){
					Argument temp = new Argument();
					value = Float.valueOf(optionalArgValue);
					listArgs.get(i).setValue(value.toString());
					/* temp.setValue(value);
					temp.setName(optionalArgName);
					temp.setType(Argument.Type.FLOAT);
					addArg(temp); */
					
					
				}
				else{
					Argument.Type type;
					if(optionalArgValue.contains("true")){
						value = true;
						type = Argument.Type.BOOLEAN;
					}
					else if(optionalArgValue.contains("false")){
						value = false;
						type = Argument.Type.BOOLEAN;
					}
					else{
						value = optionalArgValue;
						type = Argument.Type.STRING;
					}
					Argument temp = new Argument();
					listArgs.get(i).setValue(value.toString());
					/* temp.setValue(value);
					temp.setName(optionalArgName);
					temp.setType(type);
					addArg(temp);	 */		
					
				}
				
				
				
			} 
			else if(num < listArgs.size()){
				listArgs.get(num).setValue(args[i]);
				numOfArgs++;
				num++;
			}
			else{
				numOfArgs++;
			}
			
		

			
		}
		if (numOfArgs > listArgs.size()){
			String extra = "";				
			for (int j = listArgs.size(); j < args.length; j++){
				extra += args[j] + " ";
			} 
			String message = "usage: java " + getProgramName() + name + "\n" + getProgramName() + ".java: error: unrecognized arguments: " + extra;
			message = message.trim();
			throw new IllegalArgumentException(message);
		}
		else if (numOfArgs < listArgs.size()){
			String less = "";
			for (int k = listArgs.size(); k < args.length; k++){
				less += args[k] + " " ; 
			}
			String message = ".java: error: unrecognized arguments: "  + less;
			message = message.trim();
			throw new IllegalArgumentException(message);
		}
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