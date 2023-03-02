
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class MyButtonCellRenderer extends JButton implements TableCellRenderer {
	 public MyButtonCellRenderer() {
	        setOpaque(true);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	    /**   if (isSelected) {
	            setForeground(null);
	            setBackground(null);
	        } else {
	            setForeground(null);
	            setBackground(null);
	        } **/
	        setIcon((ImageIcon)value);

	        setBorderPainted(false);
	        setBorder(null);
	        //button.setFocusable(false);
	        setMargin(new Insets(0, 0, 0, 0));
	        setContentAreaFilled(false);
	    //    button.setIcon(myIcon1);
	     //   button.setRolloverIcon(myIcon2);
	      //  button.setPressedIcon(myIcon3);
	       // button.setDisabledIcon(myIcon4);
	        return this;
	    }
}
