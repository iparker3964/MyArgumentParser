package edu.jsu.mcis;

import static org.junit.Assert.*;
import org.junit.*;


public class ArgumentParserTest {
	private ArgumentParser pa;
	private Argument ga;
	
	
	@Before
	public void setUp(){
		pa = new ArgumentParser();
		ga = new Argument();
	}
	
	@Test
	public void testArgument(){
		ga.setArgumentName("length");
		ga.setArgumentDescription("box length");
		ga.setArgumentType(Argument.Type.INTEGER);
		ga.setArgumentValue("7");
		assertEquals("length", ga.getArgumentName());
		assertEquals("box length", ga.getArgumentDescription());
		assertEquals(Argument.Type.INTEGER, ga.getArgumentType());
		assertEquals(7, ga.getArgumentValue());
	}
	
	@Test	
	public void testAddArgMultArguments(){
		pa.addArg("Parser", "", Argument.Type.INTEGER);
		assertEquals(pa.getNumArguments(), 1);
		pa.addArg("Parser2", "", Argument.Type.INTEGER);
		assertEquals(pa.getNumArguments(), 2);
	}
	@Test
	public void testAddArgArgument(){
		ga.setArgumentName("length");
		pa.addArg(ga);
		assertEquals(pa.getNumArguments(), 1);
		ga.setArgumentName("width");
		pa.addArg(ga);
		assertEquals(pa.getNumArguments(), 2);
	}
	@Test
	public void testParse(){
		Argument ga2 = new Argument();
		ga2.setArgumentName("Parser2");
		ga.setArgumentName("length");
		pa.addArg(ga);
		pa.addArg(ga2);
		String[] args = {"val1", "val2"};
		pa.parse(args);
		Argument temp = new Argument();
		temp = pa.getArg("length");
		assertEquals("val1", temp.getArgumentValue());
		Argument temp2 = pa.getArg("Parser2");
		assertEquals("val2", temp2.getArgumentValue());
	}
	@Test (expected= IllegalArgumentException.class)
	public void testTooManyArgs(){
		Argument ga2 = new Argument();
		ga2.setArgumentName("Parser2");
		ga.setArgumentName("length");
		pa.addArg(ga);
		pa.addArg(ga2);
		String[] args = {"val1", "val2", "val3"};
		pa.parse(args);
		Argument temp = new Argument();
		temp = pa.getArg("length");
		assertEquals("val1", temp.getArgumentValue());
		Argument temp2 = pa.getArg("Parser2");
		assertEquals("val2", temp2.getArgumentValue());
		Argument temp3 = pa.getArg("Parser2");
		assertEquals("val3", temp3.getArgumentValue());
	}	
	@Test (expected= IllegalArgumentException.class)
	public void testTooFewArgs(){
		Argument ga2 = new Argument();
		ga2.setArgumentName("Parser2");
		ga.setArgumentName("length");
		pa.addArg(ga);
		pa.addArg(ga2);
		String[] args = {"val1"};
		pa.parse(args);
		Argument temp = new Argument();
		temp = pa.getArg("length");
		assertEquals("val1", temp.getArgumentValue());
	}	
	@Test (expected= HelpMessageException.class)
	public void TestUsageMessage(){
		ga.setArgumentName("help");
		pa.addArg(ga);
		String[] args = {"-h"};
		pa.parse(args);
	}
	@Test 
	public void testMessage(){
		pa = new ArgumentParser();
		pa.setProgramName("VolumeCalculator");
		pa.addArg("length", "", Argument.Type.STRING);
		pa.addArg("width", "", Argument.Type.STRING);
		pa.addArg("height", "", Argument.Type.STRING);
		
		String[] args = {"7", "5", "2" , "43"};
		String message1 = "usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: unrecognized arguments: 43";
		try{
			pa.parse(args);
		}catch(IllegalArgumentException e){
			assertEquals(message1, pa.getMessage());
		}
		
		
	}	
	
}
