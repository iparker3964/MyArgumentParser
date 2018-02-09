package edu.jsu.mcis;

import java.lang.*;
import java.util.*;
import java.io.*;



public class ArgumentParser{
	private Argument argument;
	private static int count;
	private ArrayList<String> argList;
	private List<Argument> listArg;
	private String message;
	private String programName;
	private ArrayList<String> nameList;
	private boolean isCorrectAmountOfArgs;
	private String programDescription;
	
	public ArgumentParser(){
		count = 0;
		argList = new ArrayList<String>();
		argument = new Argument();
		listArg = new ArrayList<Argument>();
		nameList = new ArrayList<String>();
		message = "";
		isCorrectAmountOfArgs = true;
		programDescription = "";
	}
	public void addArgument(String name, String description, Argument.Type type){
		count++;
		Argument tempArg = new Argument();
		tempArg.setName(name);
		nameList.add(name);
		tempArg.setDescription(description);
		tempArg.setType(type);
		addArgument(tempArg);
	}
	public void addArgument(Argument args){
		listArg.add(args);
	}
	public void inputArg(String arg){
		argList.add(arg);
	
	}
	public void checkArgInput(){
		String name = "";
		String missingArgs = "";
		String incorrectArg = " ";
		for(Argument arg: listArg){
			name += " " + arg.getName();
		}
		String message = "usage: java " + getProgramName() ;
		//not enough arguments
		if(count > argList.size()){
			isCorrectAmountOfArgs = false;
			for(int i = argList.size(); i < count; i++){
				missingArgs += listArg.get(2).getName();
			}
			message += name + " " +getProgramName() +".java: error: the following arguments are required: " + missingArgs;
			message = message.trim();
			throw new IllegalArgumentException(message);
		}
		//to many arguments
		if(count < argList.size()){
			isCorrectAmountOfArgs = false;
			for(int i = count; i < argList.size(); i++){
				incorrectArg += argList.get(i);
			}
			message += name + " " +getProgramName() +".java: error: unrecognized arguments:" + incorrectArg;
			message = message.trim();
			throw new IllegalArgumentException(message);
		}
	}
	public void parser(String[] args){
		String name = "";
		for(Argument arg: listArg){
			name += " " + arg.getName();
		}
		for(String word: args){
			if(word.equals("-h")){
				
				String message = "usage: java " + getProgramName() + name + "\n" getProgramDescription() + "\npositional arguments: \n";
				for(Argument arg: listArg){
					message += arg.getName() + " the " + arg.getName() + "of the box(float)\n";
				}
				message = message.trim();
				
				throw new HelpMessageException(message);
			}
		}
		for(String arg: args){
			inputArg(arg);
		}
		checkArgInput();
		if(isCorrectAmountOfArgs == true){
			for(int i = 0; i < listArg.size(); i++){
				listArg.get(i).setValue(args[i]);
			}
		}
		/*if(count > argList.size()){
			String message = "";
			throw new IllegalArgumentException();
		}*/
		
		
	} 
	public String getArg(String arg){
		String value = "";
		for(int i = 0; i < listArg.size(); i++){
			if(listArg.get(i).getName().equals(arg)){
				value +=listArg.get(i).getValue();
			}
		}
		return value;
	}
	public void setProgramName(String programName){
			this.programName = programName;
	}
	public String getProgramName(){
		return programName;
	}
	public void setProgramDescription(String description){
		this.programDescription = description;
	}
	public String getProgramDescription(){
		return programDescription;
	}
	//public void get
	
}