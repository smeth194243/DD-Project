package grid.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import grid.controller.GridController;
import grid.model.GridTableModel;
import grid.model.Bike;

public class GridPanel extends JPanel {
	
	private GridController gridController;
	
	private SpringLayout springLayout;
	private DefaultTableModel tableModel;
	
	private JScrollPane dataTableScrollPane;
	private JTable dataTable;
	private JLabel titleLabel;
	private JLabel currentDataLabel;
	private JTextField editCellField;
	private JButton insertButton;
	private JButton deleteButton;
	private JButton clearButton;
	private JButton quitButton;
	private JButton randomButton;
	
	public GridPanel(GridController gridController){
		super();
		
		this.gridController = gridController;
		this.springLayout = new SpringLayout();
		
		this.tableModel = new GridTableModel();
		this.dataTable = new JTable(tableModel);
		this.dataTableScrollPane = new JScrollPane(dataTable);
		this.titleLabel = new JLabel("Cell Editor");
		this.currentDataLabel = new JLabel("Edit Data:");
		this.editCellField = new JTextField();
		this.insertButton = new JButton("Insert");
		this.deleteButton = new JButton("Delete");
		this.clearButton = new JButton("Clear");
		this.quitButton = new JButton("Quit");
		this.randomButton = new JButton("Random");
		
		this.populateTableModel();
		this.setupComponents();
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}
	
	private void populateTableModel(){ // This method inverts the row and column data and populates the jTableModel
		Bike[][] bikes = gridController.getGrid();
		
		this.tableModel.setRowCount(bikes[0].length);
		this.tableModel.setColumnCount(bikes.length);
		this.tableModel.setColumnIdentifiers(new String[]{"Specialized", "Cannondale", "Santa Cruz", "Trek", "Yeti"});
		
		for(int row = 0; row < bikes.length; row++){
			for(int col = 0; col < bikes[0].length; col++){
				Bike bike = bikes[row][col];
				this.tableModel.setValueAt(bike.getBikeName(), col, row);
			}
		}
	}
	
	private void setupComponents(){
		this.dataTable.setRowSelectionAllowed(false);
		this.dataTable.setColumnSelectionAllowed(false);
		this.dataTable.setCellSelectionEnabled(true);
		
		
		Font font = new Font("Verdana", Font.PLAIN, 14);
		this.titleLabel.setFont(font.deriveFont(25f));
		this.dataTable.setFont(font.deriveFont(13f));
		this.currentDataLabel.setFont(font);
		this.editCellField.setFont(font);
		this.insertButton.setFont(font);
		this.deleteButton.setFont(font);
		this.clearButton.setFont(font);
		this.quitButton.setFont(font);
		this.randomButton.setFont(font);
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(dataTableScrollPane);
		this.add(titleLabel);
		this.add(currentDataLabel);
		this.add(editCellField);
		this.add(insertButton);
		this.add(deleteButton);
		this.add(clearButton);
		this.add(quitButton);
		this.add(randomButton);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, titleLabel, 20, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, dataTableScrollPane, 20, SpringLayout.SOUTH, titleLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, dataTableScrollPane, 250, SpringLayout.NORTH, dataTableScrollPane);
		springLayout.putConstraint(SpringLayout.EAST, dataTableScrollPane, -20, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, dataTableScrollPane, 20, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, currentDataLabel, 30, SpringLayout.SOUTH, dataTableScrollPane);
		springLayout.putConstraint(SpringLayout.WEST, currentDataLabel, 0, SpringLayout.WEST, dataTableScrollPane);
		
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, editCellField, 0, SpringLayout.VERTICAL_CENTER, currentDataLabel);
		springLayout.putConstraint(SpringLayout.EAST, editCellField, 0, SpringLayout.EAST, dataTableScrollPane);
		springLayout.putConstraint(SpringLayout.WEST, editCellField, 10, SpringLayout.EAST, currentDataLabel);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, clearButton, 0, SpringLayout.HORIZONTAL_CENTER, dataTableScrollPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, insertButton, 20, SpringLayout.SOUTH, editCellField);
		springLayout.putConstraint(SpringLayout.EAST, insertButton, 0, SpringLayout.WEST, clearButton);
		
		springLayout.putConstraint(SpringLayout.NORTH, deleteButton, 20, SpringLayout.SOUTH, editCellField);
		springLayout.putConstraint(SpringLayout.WEST, deleteButton, 0, SpringLayout.EAST, clearButton);
		
		springLayout.putConstraint(SpringLayout.NORTH, clearButton, 20, SpringLayout.SOUTH, insertButton);
		
		springLayout.putConstraint(SpringLayout.NORTH, quitButton, 20, SpringLayout.SOUTH, clearButton);
		springLayout.putConstraint(SpringLayout.EAST, quitButton, 0, SpringLayout.WEST, clearButton);
		
		springLayout.putConstraint(SpringLayout.NORTH, randomButton, 20, SpringLayout.SOUTH, clearButton);
		springLayout.putConstraint(SpringLayout.WEST, randomButton, 0, SpringLayout.EAST, clearButton);
	}
	
	private void setupListeners(){
		this.insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = editCellField.getText();
				if(text != null && !text.equals("") && dataTable.getSelectedRow() > 0 && dataTable.getSelectedColumn() > 0){
					editCellField.setText("");
					dataTable.getModel().setValueAt(text, dataTable.getSelectedRow(), dataTable.getSelectedColumn());
				}
			}
		});
		this.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dataTable.getSelectedRow() > 0 && dataTable.getSelectedColumn() > 0){
					dataTable.getModel().setValueAt(null, dataTable.getSelectedRow(), dataTable.getSelectedColumn());
				}
			}
		});
		this.clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableModel tableModel = dataTable.getModel();
				for(int row = 0; row < tableModel.getRowCount(); row++){
					for(int col = 0; col < tableModel.getColumnCount(); col++){
						tableModel.setValueAt(null, row, col);
					}
				}
			}
		});
		this.quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.randomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableModel tableModel = dataTable.getModel();
				for(int row = 0; row < tableModel.getRowCount(); row++){
					for(int col = 0; col < tableModel.getColumnCount(); col++){
						String randomness = "";
						for(int rand = 0; rand < (int)(Math.random() * 15); rand++){
							randomness += (char)((int)(Math.random() * 255));
						}
						tableModel.setValueAt(randomness, row, col);
					}
				}
			}
		});
	}
	
	
	public GridController getBaseController(){
		return gridController;
	}

}
