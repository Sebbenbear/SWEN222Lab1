import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

/**
 * LanderFrame class that contains the canvas.
 * @author Pauline Kelly
 *
 */
public class LanderFrame extends javax.swing.JFrame implements java.awt.event.KeyListener {

	//http://stackoverflow.com/questions/23304775/looping-music-in-java  MUSIC
	//https://www.youtube.com/watch?v=rf_p3-8fTo0  -- first track

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LanderCanvas canvas;
	public static final int OFFSET = 5;
	public static final int WINDOW_SIZE = 600;
	public static final int UNIT_FUEL = 1;

	public static final float GRAVITY_ACCELERATION = 1;
	public static final float MAIN_ENGINE_ACCELERATION = 2;
	public static final float THRUSTER_ACCELERATION = 1;
	public static final int TERMINAL_VELOCITY = 5;

	public float verticalSpeed = 0;
	public float horizontalSpeed = 0;

	private float mainEngineAcceleration;
	private float horizontalAcceleration;

	public int fuel = 100;

	public LanderFrame() {
		super("Moon Lander");
		canvas = new LanderCanvas(); // create canvas
		setLayout(new BorderLayout()); // use border layour
		add(canvas, BorderLayout.CENTER); // add canvas
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); // pack components tightly together
		setResizable(false); // prevent us from being resizeable
		setVisible(true); // make sure we are visible!
		this.addKeyListener(this);  //add the keylistener to itself.
	}

	/**
	 * Returns the preferred dimension of the window - 600x600
	 */
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(WINDOW_SIZE,WINDOW_SIZE);
	}

	/**
	 * Changes ships position - calculates the physics for moving vertically and horizonatally.
	 * @param amount
	 */
	public void fall(){
		calculatePositionDownwards();
		calculatePositionSideways();

		if(!hitGround()){
			float timeDelta = 1;

			canvas.translateY((int)(this.verticalSpeed * timeDelta));
			canvas.translateX((int)(this.horizontalSpeed * timeDelta));
		}
	}

	private void calculatePositionSideways() {
		float acceleration = horizontalAcceleration;

		float time_delta = 1;

		this.horizontalSpeed = this.horizontalSpeed + acceleration * time_delta; 		
	}

	boolean hitGround() {
		return canvas.hitGround();
	}

	/**
	 * Simple model for gravity
	 * TODO: Could be changes to not use so many fields? Probably not...
	 */
	private void calculatePositionDownwards() {
		float acceleration = GRAVITY_ACCELERATION + mainEngineAcceleration;

		float timeDelta = 1;

		this.verticalSpeed = this.verticalSpeed + acceleration * timeDelta;  

		//		this.vertical_speed = this.vertical_speed > TERMINAL_VELOCITY ? this.vertical_speed = TERMINAL_VELOCITY : this.vertical_speed;  //Keep at terminal speed if it's reached
		//		this.vertical_position = this.vertical_position + this.vertical_speed;		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
			horizontalAcceleration = THRUSTER_ACCELERATION;

		} else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
			horizontalAcceleration = -THRUSTER_ACCELERATION;
		}
		else if(code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP) {
			if(fuel <= 0){  //If the fuel has been used, do nothing.
				canvas.setThrusting(false);
				mainEngineAcceleration = 0;
				return;
			}
			fuel -= UNIT_FUEL;
			canvas.setFuel(fuel);

			canvas.setThrusting(true);
			mainEngineAcceleration = -MAIN_ENGINE_ACCELERATION;
		}		
	}

	/**
	 * Resets the acceleration
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT ||
				code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
			horizontalAcceleration = 0;
		}
		else if(code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP) {
			canvas.setThrusting(false);
			mainEngineAcceleration = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}








