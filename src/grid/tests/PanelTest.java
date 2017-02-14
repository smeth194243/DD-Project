package grid.tests;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import org.junit.*;
import grid.controller.GridController;
import grid.view.GridPanel;

public class PanelTest
{
	private GridPanel testPanel;
	
	@Before
	public void setUp() throws Exception
	{
		testPanel = new GridPanel(new GridController());
	}

	@After
	public void tearDown() throws Exception
	{
		testPanel = null;
	}

	@Test
	public void testPanelConstructor()
	{
		assertNotNull("Data member not initialized", testPanel.getBaseController());
	}
	
	@Test
	public void testSetupPanel()
	{
		assertTrue("Minimum component amount not met", testPanel.getComponentCount() > 8);
		assertTrue("Layout should be SpringLayout or absolute layout(not preferred)", (testPanel.getLayout() instanceof SpringLayout) || testPanel.getLayout() == null);
		
		int paneCount = 0;
		int tableCount = 0;
		
		for(Component current : testPanel.getComponents() )
		{
			if (current instanceof JScrollPane)
			{
				paneCount++;
				if(((JScrollPane)current).getViewport().getView() instanceof JTable)
				{
					tableCount++;
				}
			}
			if (current instanceof JTextField)
			{
				assertTrue("All text fields must be editable", ((JTextField)current).isEditable());
			}
		}
		
		assertTrue("JScrollPane not installed", paneCount > 0);
		assertTrue("JTable not inserted into JScrollPane", tableCount > 0);
	}
	
	@Test
	public void testSetupListeners()
	{
		for(Component button : testPanel.getComponents() )
		{
			if(button instanceof JButton)
			{
				assertNotNull("Listeners were not added to the button", ((JButton) button).getActionListeners());
				assertTrue("", ((JButton) button).getActionListeners()[0] instanceof ActionListener);
			}
		}
	}

}
