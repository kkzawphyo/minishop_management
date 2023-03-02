
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class SimpleInternalFrame extends JFrame {
  JLayeredPane desktop;
  JInternalFrame internalFrame;
  public SimpleInternalFrame() {
    super("");
    setSize(500, 400);

    desktop = new JDesktopPane();
    desktop.setOpaque(true);
    //desktop.setLayout(new BorderLayout());
    add(desktop, BorderLayout.CENTER);

    internalFrame = new JInternalFrame("Meow",true, true, true, true);
    internalFrame.setFrameIcon(new ImageIcon("image/new.gif"));
    internalFrame.setBounds(50, 50, 200, 100);
    internalFrame.getContentPane().add(new JLabel(new ImageIcon("image/new.gif")));
    internalFrame.setVisible(true);

    desktop.add(internalFrame, new Integer(1));
  }

  public static void main(String args[]) {
    SimpleInternalFrame sif = new SimpleInternalFrame();
    sif.setVisible(true);
  }
}


