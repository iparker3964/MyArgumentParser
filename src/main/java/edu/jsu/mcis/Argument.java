package edu.jsu.mcis;

import java.util.*;
import java.lang.*;
import java.io.*;

	
public class Argument{
	public enum Type {BOOLEAN, FLOAT, INTEGER, STRING};
	private String value;
	private String description;
	private Type type;
	private String name;
	

	public Argument(){
		value = "";
		description = "";
		type = Type.STRING;
		name = "";
	}

	public void setValue (String value){
		this.value = value;
	}

	public <T> T getValue(){
		if (type == Type.INTEGER) return (T)(new Integer(Integer.parseInt((String)value)));		
		else if (type == Type.BOOLEAN) return (T)(Boolean.valueOf((String)value));
		else if (type == Type.FLOAT) return (T)(Float.valueOf((String)value));
		else if (type == Type.STRING) return (T)(String.valueOf((String)value));
		else return (T)(String)value;
	}

	public void setType(Type type){
		this.type = type;
	}

	public Type getType(){
		return type;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}
	
	public void setName (String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}

}
