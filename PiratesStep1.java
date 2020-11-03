// Import the GUI libraries
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PiratesStep1 {
	/**
	 * MAIN METHOD
	 * This main method starts the GUI and runs the createMainWindow() method.
	 * This method should not be changed.
	 */
	public static void main (String [] args) {
		javax.swing.SwingUtilities.invokeLater (new Runnable () {
			public void run () {
				createMainWindow ();
			}
		});
	}


	/**
	 * STATIC VARIABLES AND CONSTANTS
	 * Declare the objects and variables that you want to access across
	 * multiple methods.
	 */
	


	/**
	 * CREATE MAIN WINDOW
	 * This method is called by the main method to set up the main GUI window.
	 */
	private static void createMainWindow () {
		// Create and set up the window.
		JFrame frame = new JFrame ("Pirates");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setResizable (false);

		// The panel that will hold the components in the frame.
		JPanel contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(950, 400));
		contentPane.setLayout(new BorderLayout());
	
		// Create the right action panel
		JPanel rightPanel = new JPanel();
		contentPane.add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		
		// Add the score labels
		JLabel scoreTitleLabel = new JLabel("Score");
		rightPanel.add(scoreTitleLabel);
		scoreTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel scoreNumberLabel = new JLabel("0");
		rightPanel.add(scoreNumberLabel);
		scoreNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Add the action labels
		JLabel actionsTitleLabel = new JLabel("Actions");
		rightPanel.add(actionsTitleLabel);
		actionsTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Add New Game button
		JButton newGameButton = new JButton("New Game");
		rightPanel.add(newGameButton);
		newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Add Music button
		JButton musicButton = new JButton("Music Off");
		rightPanel.add(musicButton);
		musicButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Add quit button
		JButton quitButton = new JButton("Quit");
		rightPanel.add(quitButton);
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Add map
		JLayeredPane gamePanel = new JLayeredPane();
		contentPane.add(gamePanel);
		
		JLabel mapImage = new JLabel(new ImageIcon("resources/world-map-animals.jpg"));
		gamePanel.add(mapImage);
		mapImage.setSize(775, 400);
		
		// Add the pirate ship
		JLabel shipImage = createScaledImage("resources/pirate-ship.png", 40, 40);
		shipImage.setSize(40, 40);
		gamePanel.add(shipImage, Integer.valueOf(100));
		
		// Put the ship at a random location
		Random randomGenerator = new Random();
		int pirateX = randomGenerator.nextInt(735);
		int pirateY = randomGenerator.nextInt(360);
		shipImage.setLocation(pirateX, pirateY);
		
		// Add the panel to the frame
		frame.setContentPane(contentPane);

		//size the window.
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	/**
	 * HELPER METHODS
	 * Methods that you create to manage repetitive tasks.
	 */

	/** Creates an image label scaled to the given size. */
	private static JLabel createScaledImage (String filename, int width, int height) {
		Image originalImage = new ImageIcon(filename).getImage();
		Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}


	/**
	 * EVENT LISTENERS
	 * Subclasses that handle events (button clicks, mouse clicks and moves,
	 * key presses, timer expirations)
	 */
	
}