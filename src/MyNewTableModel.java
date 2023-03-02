

import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
public class MyNewTableModel extends DefaultTableModel{
		public MyNewTableModel(){

		}

		public MyNewTableModel(Object[][] data,String[] columnNames){
			super(data, columnNames);
		}

		public Class getColumnClass(int column){
			return getValueAt(0, column).getClass();
		}

		public boolean isCellEditable(int row, int column){
			return false;
		}

		public void removeRow(int[] row) {
			// TODO Auto-generated method stub
			for(int i=0 ; i<row.length ; i++){
				removeRow(row[i]-i);
			//	System.out.print(row[i]+" removed!");
			}

		}

	}



