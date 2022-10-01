import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Jayprakash Pathak
 * Desc: Draws a picture on a JFrame
 * 		 Inherits from JComponent
 *
 */
public class Picture extends JComponent{
	
	// Private data for Picture
	private Color color;
	private int xPos, yPos, myWidth, myHeight;

	/**
	 * Default contructor
	 */
	public Picture() {
		// Initialize my data
		this.color = Color.red;
		this.xPos = 0;
		this.yPos = 0;
		this.myWidth = 50;
		this.myHeight = 25;
	}
	
	/**
	 * Overloaded contructor
	 */
	public Picture(int x, int y, int w, int h) {
		this.color = Color.RED;
		this.xPos = x;
		this.yPos = y;
		this.myWidth = w;
		this.myHeight = h;
	}
	
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Method to get color
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Overloading the setColor method
	 * Set color using RGB
	 */
	public void setColor(int r, int g, int b) {
		this.color = new Color(r, g, b);
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * @param myWidth the myWidth to set
	 */
	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
	}

	/**
	 * @param myHeight the myHeight to set
	 */
	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
	}
	
	/**
	 * Method to get myHeight
	 */
	public int getMyHeight() {
		return this.myHeight;
	}
	
	/**
	 * Method to get myWidth
	 */
	public int getMyWidth() {
		return this.myWidth;
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	// Override the JComponent paint
	public void paint(Graphics g) {
		g.setColor(this.color);
		g.drawRect(this.xPos, this.yPos, myWidth, myHeight);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Self testing
		// Create a JFrame
		JFrame f = new JFrame("Testing");
		
		// Set the size of the JFrame
		f.setSize(400, 350);
		
		// Instantiates a Picture object
		Picture p = new Picture();
		
		// Add the object to f
		f.add(p);
		
		f.setVisible(true);
		
		// Wait command
		JOptionPane.showMessageDialog(null, "Wait to change color");
		
		// Change color
		p.setColor(Color.BLUE);
		
		// Repaint the frame
		f.repaint();
		
		// Wait command
		JOptionPane.showMessageDialog(null, "Wait to change color");
		
		// Change color using RGB
		p.setColor(204, 0, 204);
		
		// Repaint the frame
		f.repaint();
		
		// Wait command
		JOptionPane.showMessageDialog(null, "Wait to change position and size");
		
		p.setxPos(50);
		p.setyPos(100);
		
		p.setMyWidth(30);
		p.setMyHeight(80);
		
		f.repaint();
		
		// Wait command
		JOptionPane.showMessageDialog(null, "Wait to create new object");
		
		Picture p1 = new Picture(200, 100, 120, 50);
		f.add(p1);
		f.setVisible(true);

	}

}
