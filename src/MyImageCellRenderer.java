

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyImageCellRenderer extends DefaultTableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocused, int row, int column){

		Image image = ((ImageIcon)value).getImage();
		ImageViewer imageViewer = new ImageViewer(image);

		return imageViewer;
	}

}
 class ImageViewer extends JPanel {
	private Image image ;
	private int xCoordinate;
	private int yCoordinate;
	public ImageViewer(){

	}

	ImageViewer(Image image){
		this.image =image;
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image,xCoordinate+55, yCoordinate+5, this);
	}

}

