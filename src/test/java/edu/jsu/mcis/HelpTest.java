package edu.jsu.mcis;

import static org.junit.Assert.*;
import org.junit.*;

public class HelpTest{
	private ArgumentParser ap;
	private Argument arg;
	
	@Before
	public void setUp(){
		ap = new ArgumentParser();
		arg = new Argument();
	}
	@Test (expected = HelpMessageException.class)
	public void testHelpMessage(){
		ap.setProgramName("VolumeCalculator");
		ap.setProgramDescription("Calculate the volume of a box.");
		ap.addArgument("length", "", Argument.Type.INTEGER);
		ap.addArgument("width", "", Argument.Type.INTEGER);
		ap.addArgument();
	}
	
}