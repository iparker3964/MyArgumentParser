import edu.jsu.mcis.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class ArgumentParserKeywords {
	private ArgumentParser parser;
	private boolean error;
	private String errorMessage;
	private boolean helpError;
	
	
	public void StartVolumeCalculatorWithArguments(String[] args){
		parser = new ArgumentParser();
		parser.setProgramName("VolumeCalculator");
		parser.addArgument("length", "", Argument.Type.INTEGER);
		parser.addArgument("width", "", Argument.Type.INTEGER);
		parser.addArgument("height","", Argument.Type.INTEGER);
		
		try{
			parser.parser(args);
		}
		catch(IllegalArgumentException ex){
			error = true;
			errorMessage = ex.getMessage();
		}
		
	}
	public void StartProgramWithArguments(String[] args){
		parser = new ArgumentParser();
		parser.setProgramName("VolumeCalaculator");
		parser.setProgramDescription("Calculate the volume of a box.");
		parser.addArgument("length", "length the length of the box (float)", Argumet.Type.INTEGER);
		parser.addArgument("width", "width the width of the box (float)", Argument.Type.INTEGER);
		parser.addArgument("height", "height the height of the box (float)");
		try{
			parser.parser(args);
		}
		catch(HelpMessageException ex){
			helpError = true;
			errorMessage = ex.getHelpMessage();
		}
		
	}
	public void StartAbsurdProgramWithArguments(String[] args){
		parser = new ArgumentParser();
		parser.addArgument("pet", "", Argument.Type.STRING);
		parser.addArgument("number", "", Argument.Type.INTEGER);
		parser.addArgument("rainy", "", Argument.Type.BOOLEAN);
		parser.addArgument("bathrooms", "", Argument.Type.FLOAT);
		
		try{
			parser.parser(args);
		}catch(IllegalArgumentException ex){
			error = true;
			errorMessage = ex.getMessage();
		}
	}
	public String getLength(){
		return parser.getArg("length");
	}
	public String getWidth(){
		return parser.getArg("width");
	}
	public String getHeight(){
		return parser.getArg("height");
	}
	public String getPet(){
		return parser.getArg("pet");
	}
	public String getNumber(){
		return parser.getArg("number");
	}
	public String getRainy(){
		return parser.getArg("rainy");
	}
	public String getBathrooms(){
		return parser.getArg("bathrooms");
	}
	public String getProgramOutput(){
		if(error == true){
			return errorMessage;
		}
		else{
			int length = Integer.parseInt(getLength());
			int width = Integer.parseInt(getWidth());
			int height = Integer.parseInt(getHeight());
			return "" + length * width * height;
		}
		
	}
	/*public void StartVolumeCalculatorWithArguments(String[] args){
		pa = new ArgumentParser();
		pa.setProgramName("VolumeCalculator");
		pa.addArg("length", "", false,Argument.Type.STRING);
		pa.addArg("width", "", false, Argument.Type.STRING);
		pa.addArg("height", "", false, Argument.Type.STRING);
		pa.addArg("type", "", true, Argument.Type.STRING);
		pa.getArg("type").setDefault("box");
		pa.addArg("digits", "", true, Argument.Type.STRING);
		pa.getArg("digits").setDefault("4");
		try{
			pa.parse(args);
		}
		catch(IllegalArgumentException e){
			error = true;
			errorMessage = e.getMessage();
		}	
	}
	
	public String getWidth(){
		return pa.getArg("width").getValue();
	}
	
	public String getHeight(){
		return pa.getArg("height").getValue();
	}
	
	public String getLength(){
		return pa.getArg("length").getValue();
	}
	
	public String getProgramOutput(){
		if (error == true){
			return errorMessage;
		}
		
		else if (helpError == true){
			return errorMessage;
		}

		else {
			int length = Integer.parseInt(getLength());
			int width = Integer.parseInt(getWidth());
			int height = Integer.parseInt(getHeight());
			
			int volume = length*width*height;
			
			return Integer.toString(volume);
		}
	}
	
	public void StartAbsurdProgramWithArguments(String[] args){
		pa = new ArgumentParser();
		error = false;
				
		pa.addArg("pet", "", false, Argument.Type.STRING);
		pa.addArg("number", "", false, Argument.Type.STRING);
		pa.addArg("rainy", "", false, Argument.Type.STRING);
		pa.addArg("bathrooms", "", false, Argument.Type.STRING);
		
		try{
			pa.parse(args);
		}
		catch(IllegalArgumentException e){
			error = true;
		}
	}
	public String getPet(){
		return pa.getArg("pet").getValue();
	}
	public String getNumber(){
		return pa.getArg("number").getValue();
	}
	public String getRainy(){
		return pa.getArg("rainy").getValue();
	}
	public String getBathrooms(){
		return pa.getArg("bathrooms").getValue();
	}
	public String getType(){
		return pa.getArg("type").getValue();
	}
	public String getDigits(){
		return pa.getArg("digits").getValue();
	}
	
	
	public void StartProgramWithArguments(String[] args){
		pa = new ArgumentParser();
		helpError = false;
		pa.setProgramName("VolumeCalculator");
		pa.setProgramDescription("\nCalculate the volume of a box.");
				
		pa.addArg("length", "length the length of the box (float)", false, Argument.Type.INTEGER);
		pa.addArg("width", "width the width of the box(float)", false, Argument.Type.INTEGER);
		pa.addArg("height", "height the height of the box(float)", false, Argument.Type.INTEGER);
		
		try{
			pa.parse(args);
		}
		catch(HelpMessageException e){
			helpError = true;
			errorMessage = e.getMessage();
		}
		catch(NumberFormatException e){
			error = true;
			errorMessage = "usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: argument width: invalid float value: something";
		}
		
	}
	public String getHelpMessage(){
		return errorMessage;
	}*/

}