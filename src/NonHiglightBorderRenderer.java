

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class NonHiglightBorderRenderer extends DefaultTableCellRenderer{

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocused, int row, int column){
		super.getTableCellRendererComponent(table, value, isSelected,	isFocused, row, column);
		 Color foreColor = Color.black;
		 Color backColor = Color.white;
	        if(isFocused){
	        	foreColor = Color.white;
	        	backColor=Color.gray;
	        }

	        else if(isSelected){
	        	   foreColor = Color.white;
	        	   backColor = Color.gray;
	        }

	        setForeground(foreColor);
	        setBackground(backColor);
	        setHorizontalAlignment(JLabel.CENTER);

		setBorder(noFocusBorder);
		return this;
	}

}
