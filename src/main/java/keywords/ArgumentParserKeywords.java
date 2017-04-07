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
		return pa.getArg("width").getArgumentValue();
	}
	
	public String getHeight(){
		return pa.getArg("height").getArgumentValue();
	}
	
	public String getLength(){
		return pa.getArg("length").getArgumentValue();
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
		return pa.getArg("pet").getArgumentValue();
	}
	public String getNumber(){
		return pa.getArg("number").getArgumentValue();
	}
	public String getRainy(){
		return pa.getArg("rainy").getArgumentValue();
	}
	public String getBathrooms(){
		return pa.getArg("bathrooms").getArgumentValue();
	}
	
	public void StartProgramWithArguments(String[] args){
		pa = new ArgumentParser();
		helpError = false;
		pa.setArgumentName("-h");
		pa.setProgramName("VolumeCalculator");
		pa.setProgramDescription("\nCalculate the volume of a box.");
				
		pa.addArg("length", "length the length of the box (float)", Argument.Type.INTEGER);
		pa.addArg("width", "width the width of the box(float)", Argument.Type.INTEGER);
		pa.addArg("height", "height the height of the box(float)", Argument.Type.INTEGER);
		
		try{
			pa.parse(args);
		}
		catch(HelpMessageException e){
			helpError = true;
		}
		
	}
	public String getHelpMessage(){
		return pa.getArg("-h").toString();
	}

}