
import java.awt.color.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
public class GenericRoundedButton extends JButton{
	public GenericRoundedButton(String text){
		super(text);
	}
	public GenericRoundedButton(){

		super();
	}
	public void paint(Graphics g){

		//Set background same as parent
		//SetBackground(getParent().getBackGround());
		//setBorder(Styles.BORDER_NONE)

		//I don't neeed this- calls to above methods will
		//invoke repaint as neeeded
		//super.paint(g)

		//Take advantage of Graphics2D to position string

		Graphics2D g2d=(Graphics2D)g;
		RenderingHints rh=g2d.getRenderingHints();
		rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);


		//Make it grey #dddddd, and make it round with
		//1px black border.
		//Use an HTML color guide to find a desired color.
		//Last Color is alpha, with max 0xFF to make
		//completely opaque.
		g2d.setColor(Color.LIGHT_GRAY);

		//Draw rectangle with rounded corners on
		//button

		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

		//I'm just drawing a border g2d.setColor(getSelectionColor());
		g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);


		//finding size of text so can position in center.
		FontRenderContext frc=new FontRenderContext(null,false,false);
		Rectangle2D r=getFont().getStringBounds(getText(), frc);

		float xMargin=(float)(getWidth()-r.getWidth())/2;
		float yMargin=(float)(getHeight()-getFont().getSize())/2;
		//Draw the text in the center
		g2d.setColor(Color.red);
		g2d.drawString(getText(), xMargin, (float)getFont().getSize()+yMargin);
	}
}
