package grid.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import grid.model.Bike;
import grid.view.GridFrame;

public class GridController {
	

	private Bike[][] bikes;
	private GridFrame gridFrame;
	
	public GridController(){
		this.populateBikeArray();
		this.gridFrame = new GridFrame(this);
	}
	
	public void start(){
		
	}
	
	public void populateBikeArray(){
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/grid/assets/bikes.txt")));
			this.bikes = new Bike[5][10];
			for(int row = 0; row < this.bikes.length; row++){
				String[] bikeData = reader.readLine().split(",");
				for(int col = 0; col < bikes[0].length; col++)
				{
					 					if(bikeData.length - 1 < col)
					 					{
					 						bikes[row][col] = new Bike("");
					 					}
					 					else
					 					{
					 						bikes[row][col] = new Bike(bikeData[col]);
					 					}
					  				}
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error", "Error in reading file!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Bike[][] getGrid(){
		return bikes;
	}

	public GridFrame getFrame() {
		return gridFrame;
	}
	

}
