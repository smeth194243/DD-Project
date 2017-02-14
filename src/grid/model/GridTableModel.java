package grid.model;

import javax.swing.table.DefaultTableModel;

public class GridTableModel extends DefaultTableModel {
	
	public GridTableModel(){
		super();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
