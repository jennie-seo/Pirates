// Import the GUI libraries
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PiratesStep2 {
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

	// pirate ship image
	static JLabel pirateShip; 

	// score label 
	static int score = 0;
	static JLabel scoreLabel;
	
	// speed 
	static int speed = 2000;
	
	// move timer
	static Timer MoveTimer;

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
		JPanel contentPane = new JPanel ();
		contentPane.setPreferredSize(new Dimension(950, 400));

		// Making the content pane use BorderLayout
		contentPane.setLayout(new BorderLayout());

		// Make the side panel
		JPanel sideBar = new JPanel();
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.PAGE_AXIS));
		sideBar.setPreferredSize(new Dimension(175, 300));
		sideBar.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.add(sideBar, BorderLayout.EAST);

		// Make the score title
		JLabel scoreTitle = new JLabel("Score");
		scoreTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		sideBar.add(scoreTitle);
		sideBar.add(Box.createRigidArea(new Dimension(135, 10)));

		// Make the score label
		scoreLabel = new JLabel("0");
		scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		sideBar.add(scoreLabel);

		// Add the filler "glue"
		sideBar.add(Box.createVerticalGlue());

		// Make sidebar title
		JLabel actionsLabel = new JLabel("Actions");
		actionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		actionsLabel.setHorizontalAlignment(JLabel.CENTER);
		actionsLabel.setVerticalAlignment(JLabel.CENTER);
		sideBar.add(actionsLabel);
		sideBar.add(Box.createRigidArea(new Dimension(135, 10)));

		// Make sidebar buttons
		JButton newGameButton = new JButton("New Game");
		newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		sideBar.add(newGameButton);
		sideBar.add(Box.createRigidArea(new Dimension(135, 10)));
		newGameButton.addActionListener(new NewGameListener());

		JButton musicButton = new JButton("Music Off");
		musicButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		sideBar.add(musicButton);
		sideBar.add(Box.createRigidArea(new Dimension(135, 10)));

		JButton quitButton = new JButton("Quit");
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.addActionListener(new QuitButtonListener());
		sideBar.add(quitButton);

		// Make the center "map" panel
		JLayeredPane mapPanel = new JLayeredPane();
		contentPane.add(mapPanel, BorderLayout.CENTER);

		// Get the map image
		JLabel mapImage = new JLabel(new ImageIcon("resources/world-map-animals.jpg"));
		mapPanel.add(mapImage, Integer.valueOf(-300));
		mapImage.setSize(775, 400);
		mapImage.setLocation(0, 0);

		// Create the pirate ship
		pirateShip = createScaledImage("resources/pirate-ship.png", 40, 40);
		pirateShip.setSize(40, 40);
		MoveShip();
		mapPanel.add(pirateShip);
		
		// Create a timer that moves the pirate ship
		MoveTimer = new Timer(2000, new MoveShipHandler());
		MoveTimer.start();
		

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
	
	/** Randomly change the ship's location. */
	private static void MoveShip() {
		Random randomGenerator = new Random();
		int pirateX = randomGenerator.nextInt(735);
		int pirateY = randomGenerator.nextInt(360);
		pirateShip.setLocation(pirateX, pirateY);
		pirateShip.setVisible(true);
	}

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

	/** Handles clicks on the quit button. */
	private static class QuitButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			int answer = JOptionPane.showConfirmDialog(null, "Are you sure your want to quit?", 
					"Quit?", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	/** This listens for mouse interactions with the pirate ship */
	private static class ShipMouseHandler implements MouseListener {
		public void mouseClicked (MouseEvent event) {
			// Score up by one
			score++;
			scoreLabel.setText(String.valueOf(score));
			// Ship disappear
		
			// Make the speed faster
			speed -= 50;
			MoveTimer.setDelay(speed);
			
			pirateShip.setVisible(false);
		}

		public void mousePressed (MouseEvent event) {
		}
		public void mouseReleased (MouseEvent event) {
		}
		public void mouseEntered (MouseEvent event) {
		}
		public void mouseExited (MouseEvent event) {
		}


	}

	/** Handles when the user clicks the new game button */
	private static class NewGameListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			// set the score to zero
			score = 0;
			scoreLabel.setText("0");
			// move the ship to a new location
			MoveShip();
			pirateShip.addMouseListener(new ShipMouseHandler());
			pirateShip.setVisible(true);
			
			speed = 2000;
			MoveTimer.setDelay(2000);

		}

	}
	
	 /** Moves the pirate ship everytime the time expires */
    private static class MoveShipHandler implements ActionListener {
        public void actionPerformed (ActionEvent event) {
        	MoveShip();

        }

    }
    
}