package edu.jsu.mcis;

import static org.junit.Assert.*;
import org.junit.*;


public class ArgumentParserTest {
	private ArgumentParser parser;
	private Argument argument;
	
	//@Rule 
	//public ExpectedException expected = ExpectedException.none();
	@Before
	public void setUp(){
		parser = new ArgumentParser();
		argument = new Argument();
	}
	@Test
	public void testArgument(){
		argument.setName("length");
		argument.setDescription("box length");
		argument.setValue("7");
		argument.setType(Argument.Type.INTEGER);
		assertEquals("length", argument.getName());
		assertEquals("box length", argument.getDescription());
		assertEquals(7, argument.getValue());
		assertEquals(Argument.Type.INTEGER, argument.getType());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testTomanyArguments(){
		parser.addArgument("length", "", Argument.Type.INTEGER);
		parser.addArgument("width", "", Argument.Type.INTEGER);
		parser.addArgument("height", "", Argument.Type.INTEGER);
		parser.inputArg("7");
		parser.checkArgInput();
		parser.inputArg("5");
		parser.checkArgInput();
		parser.inputArg("2");
		parser.checkArgInput();
		parser.inputArg("8");
		parser.checkArgInput();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testToFewAruguments(){
		parser.addArgument("length", "", Argument.Type.INTEGER);
		parser.addArgument("width", "", Argument.Type.INTEGER);
		parser.addArgument("height", "", Argument.Type.INTEGER);
		parser.inputArg("7");
		parser.checkArgInput();
		parser.inputArg("2");
		parser.checkArgInput();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testToFewMessage()throws Exception{
		
		String testMessage = "usage: java VolumeCalculator length width height VolumeCalculator.java: error: the following arguments are required: height";
			   testMessage = testMessage.trim();
		//expected.expect(RuntimeException.class);
		//expected.expectMessage(testMessage);
		try{
			parser.setProgramName("VolumeCalculator");
			parser.addArgument("length", "", Argument.Type.INTEGER);
			parser.addArgument("width", "", Argument.Type.INTEGER);
			parser.addArgument("height", "", Argument.Type.INTEGER);
			parser.inputArg("7");
			parser.inputArg("2");
			parser.checkArgInput();
		}
		catch(IllegalArgumentException ex){
			assertEquals(testMessage, ex.getMessage());
			throw ex;
		}
	}
		//fail("Did not catch");
		//AssertEquals(testMessage, parser.checkArgInput());
	@Test(expected = IllegalArgumentException.class)
	public void testToManyMessage()throws Exception{
		String testMessage = "usage: java VolumeCalculator length width height VolumeCalculator.java: error: unrecognized arguments: 43";
		testMessage = testMessage.trim();
		try{
			parser.setProgramName("VolumeCalculator");
			parser.addArgument("length", "", Argument.Type.INTEGER);
			parser.addArgument("width", "", Argument.Type.INTEGER);
			parser.addArgument("height", "", Argument.Type.INTEGER);
			parser.inputArg("7");
			parser.inputArg("5");
			parser.inputArg("2");
			parser.inputArg("43");
			parser.checkArgInput();
		}
		catch(IllegalArgumentException ex){
			assertEquals(testMessage, ex.getMessage());
			throw ex;
				
		}
	}
}

