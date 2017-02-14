package grid.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import grid.view.GridFrame;
import grid.view.GridPanel;
import grid.controller.GridController;

public class FrameTest
{
	private GridFrame testFrame;

	@Before
	public void setUp() throws Exception
	{
		testFrame = new GridFrame(new GridController());
	}

	@After
	public void tearDown() throws Exception
	{
		testFrame = null;
	}

	@Test
	public void testGridFrame()
	{
		assertNotNull("Data member not initialized", testFrame.getBaseController());
		assertTrue("Data member is of incorrect type", testFrame.getBaseController() instanceof GridController);
		assertNotNull("Data member not initialized", testFrame.getContentPane());
	}
	
	@Test
	public void testSetupFrame()
	{
		assertTrue("Title must be more than 5 characters long.", testFrame.getTitle().trim().length() > 5);
		assertTrue("Correct panel not installed", testFrame.getContentPane() instanceof GridPanel);
		assertTrue("Minimum size not met", testFrame.getWidth() >= 500 && testFrame.getHeight() >= 500);
	}
	

}
