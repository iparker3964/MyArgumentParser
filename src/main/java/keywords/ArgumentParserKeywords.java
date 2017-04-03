import edu.jsu.mcis.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class ArgumentParserKeywords {
	private ArgumentParser pa;
	boolean error;
	boolean helpError;
	
	
	public void StartVolumeCalculatorWithArguments(String[] args){
		pa = new ArgumentParser();
		
		pa.setProgramName("VolumeCalculator");
		pa.addArg("length", "", Argument.Type.STRING);
		pa.addArg("width", "", Argument.Type.STRING);
		pa.addArg("height", "", Argument.Type.STRING);
		try{
			pa.parse(args);
		}
		catch(IllegalArgumentException e){
			error = true;
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
			return pa.getMessage();
		}
		
		else if (helpError == true){
			return pa.getHelpMessage();
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
				
		pa.addArg("pet", "", Argument.Type.STRING);
		pa.addArg("number", "", Argument.Type.STRING);
		pa.addArg("rainy", "", Argument.Type.STRING);
		pa.addArg("bathrooms", "", Argument.Type.STRING);
		
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
	
	public void StartProgramWithArguments(String[] args){
		pa = new ArgumentParser();
		pa.setProgramName("VolumeCalculator");
		pa.setProgramDescription("Calcuate the volume of a box.");
				
		pa.addArg("length", "\tlength the length of the box (float)", Argument.Type.INTEGER);
		pa.addArg("width", "\twidth the width of the box(float)", Argument.Type.INTEGER);
		pa.addArg("height", "\theight the height of the box(float)", Argument.Type.INTEGER);
		
		try{
			pa.parse(args);
		}
		catch(HelpMessageException e){
			helpError = true;
		}
	}

}