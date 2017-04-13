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
		ga.setName("length");
		ga.setDescription("box length");
		ga.setType(Argument.Type.INTEGER);
		ga.setValue("7");
		assertEquals("length", ga.getName());
		assertEquals("box length", ga.getDescription());
		assertEquals(Argument.Type.INTEGER, ga.getType());
		assertEquals(7, ga.getValue());
	}
	
	@Test	
	public void testAddArgMultArguments(){
		pa.addArg("Parser", "", false, Argument.Type.INTEGER);
		assertEquals(pa.getNumArguments(), 1);
		pa.addArg("Parser2", "", false, Argument.Type.INTEGER);
		assertEquals(pa.getNumArguments(), 2);
	}
	@Test
	public void testAddArgArgument(){
		ga.setName("length");
		pa.addArg(ga);
		assertEquals(pa.getNumArguments(), 1);
		ga.setName("width");
		pa.addArg(ga);
		assertEquals(pa.getNumArguments(), 2);
	}
	@Test
	public void testParse(){
		Argument ga2 = new Argument();
		ga2.setName("Parser2");
		ga.setName("length");
		pa.addArg(ga);
		pa.addArg(ga2);
		String[] args = {"val1", "val2"};
		pa.parse(args);
		Argument temp = new Argument();
		temp = pa.getArg("length");
		assertEquals("val1", temp.getValue());
		Argument temp2 = pa.getArg("Parser2");
		assertEquals("val2", temp2.getValue());
	}
	@Test (expected= IllegalArgumentException.class)
	public void testTooManyArgs(){
		Argument ga2 = new Argument();
		ga2.setName("Parser2");
		ga.setName("length");
		pa.addArg(ga);
		pa.addArg(ga2);
		String[] args = {"val1", "val2", "val3"};
		pa.parse(args);
		Argument temp = new Argument();
		temp = pa.getArg("length");
		assertEquals("val1", temp.getValue());
		Argument temp2 = pa.getArg("Parser2");
		assertEquals("val2", temp2.getValue());/*
		Argument temp3 = pa.getArg("Parser2");
		assertEquals("val3", temp3.getValue());*/
	}	
	@Test (expected= IllegalArgumentException.class)
	public void testTooFewArgs(){
		Argument ga2 = new Argument();
		ga2.setName("Parser2");
		ga.setName("length");
		pa.addArg(ga);
		pa.addArg(ga2);
		String[] args = {"val1"};
		pa.parse(args);
		Argument temp = new Argument();
		temp = pa.getArg("length");
		assertEquals("val1", temp.getValue());
	}	
	@Test (expected= HelpMessageException.class)
	public void TestUsageMessage(){
		ga.setName("help");
		pa.addArg(ga);
		String[] args = {"-h"};
		pa.parse(args);
	}
	@Test 
	public void testMessage(){
		pa = new ArgumentParser();
		pa.setProgramName("VolumeCalculator");
		pa.addArg("length", "", false, Argument.Type.STRING);
		pa.addArg("width", "", false, Argument.Type.STRING);
		pa.addArg("height", "", false, Argument.Type.STRING);
		
		String[] args = {"7", "something", "2" , "43"};
		String message1 = "usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: unrecognized arguments: 43";
		//String message2 = "usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: argument width: invalid float value: something";
		try{
			pa.parse(args);
		}catch(IllegalArgumentException e){
			assertEquals(message1, e.getMessage());
			//assertEquals(message2, e.getMessage());
		}
		
		
	}
	@Test
	public void TestOptionalArgument(){
		Argument ga2 = new Argument();
		Argument ga3 = new Argument();
		Argument ga4 = new Argument();
		Argument ga5 = new Argument();
		ga.setName("length");
		ga2.setName("width");
		ga3.setName("height");
		ga4.setName("type");
		ga5.setName("digits");
		ga4.setOptional(true);
		ga5.setOptional(true);
		pa.addArg(ga);
		pa.addArg(ga2);
		pa.addArg(ga3);
		pa.addArg(ga4);
		pa.addArg(ga5);
		String[] args = {"7", "5", "2", "--type", "box","--digits", "4"};
		pa.parse(args);
		Argument temp = new Argument();
		temp = pa.getArg("length");
		assertEquals("7", temp.getValue());
		Argument temp2 = pa.getArg("width");
		assertEquals("5", temp2.getValue());
		Argument temp3 = pa.getArg("height");
		assertEquals("2", temp3.getValue());
		Argument temp4 = pa.getArg("type");
		assertEquals("box", temp4.getValue());
		Argument temp5 = pa.getArg("digits");
		System.out.println(temp4.getValue() + "temp4");
		System.out.println(temp4.getName());
		System.out.println(temp5.getValue() + "temp5");
		System.out.println(temp5.getName());
		assertEquals("4", temp5.getValue());
		
	}

}
