package grid.view;

import javax.swing.JFrame;

import grid.controller.GridController;

public class GridFrame extends JFrame {
	
	private GridController gridController;
	
	private GridPanel gridPanel;
	
	public GridFrame(GridController gridController){
		super();
		this.gridController = gridController;
		this.gridPanel = new GridPanel(gridController);
		
		this.setupFrame();
	}
	
	public void setupFrame(){
		this.setContentPane(gridPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("2D Array Manipulator");
		
		this.setSize(600, 570);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
	
	public GridController getBaseController(){
		return gridController;
	}

}
