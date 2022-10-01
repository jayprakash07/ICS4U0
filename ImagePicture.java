import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Jayprakash Pathak
 * Desc: Inherits from the Picture class
 * 		 Draws ImageIcons
 *
 */
public class ImagePicture extends Picture{
	
	// Private data
	private ImageIcon image;

	/**
	 * Constructor that takes only an image
	 */
	public ImagePicture(ImageIcon image) {
		// Calls the Picture Constructor
		super();
		this.image = image;
		
		// Calls Picture setMyWidth and setMyHeight
		setMyWidth(image.getIconWidth());
		setMyHeight(image.getIconHeight());
	}
	
	/**
	 * Constructor to allow an object to be placed in a given x and y
	 */
	public ImagePicture(ImageIcon image, int x, int y) {
		// Calls the picture constructor that takes x, y, width, and height
		super(x, y, image.getIconWidth(), image.getIconHeight());
		this.image = image;
	}
	
	/**
	 * Create a setter method for the image
	 */
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	
	// Override the paint method
	public void paint(Graphics g) {
		// Paint my image
		this.image.paintIcon(this, g, getxPos(), getyPos());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Self testing
		// Declare and create a JFrame object
		JFrame f = new JFrame("Testing");
		
		f.setSize(400, 350);
		
		ImagePicture i = new ImagePicture(new ImageIcon("minion.png"));
		
		f.add(i);
		
		f.setVisible(true);
		
		// Wait message
		JOptionPane.showMessageDialog(null, "Wait to move");
		i.setxPos(50);
		i.setyPos(80);
		
		f.repaint();
		
		// Wait message
		JOptionPane.showMessageDialog(null, "Wait to place a new object");
		ImagePicture i2 = new ImagePicture(new ImageIcon("gru.png"), 150, 100);
		
		f.add(i2);
		f.setVisible(true);
		
		// Wait message
		JOptionPane.showMessageDialog(null, "Wait to change the icon");
		i2.setImage(new ImageIcon("minion.png"));
		
		f.repaint();

	}

}
