import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class LanderFrame extends javax.swing.JFrame implements java.awt.event.KeyListener {

	private LanderCanvas canvas;
	public static final int OFFSET = 5;
	public static final int WINDOW_SIZE = 600;
	public static final int UNIT_FUEL = 1;

	public static final int GRAVITY = 3;
	public static final int TERMINAL_VELOCITY = 5;
	public int vertical_speed = 0;

	public int fuel = 100;
	private int vertical_position;

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
	 * Changes ships Y position.
	 * @param amount
	 */
	public void fall(){
		//calculatePositionDownwards();
		//canvas.translateY(vertical_position);
		if(!hitGround()){
			canvas.translateY(GRAVITY);
		}
	}

	private boolean hitGround() {
		// TODO Auto-generated method stub
		return canvas.hitGround();
	}

	/**
	 * Simple model for gravity
	 * TODO: Could be changes to not use so many fields? Probably not...
	 */
	private void calculatePositionDownwards() {
		this.vertical_speed = this.vertical_speed + GRAVITY;        
		this.vertical_speed = this.vertical_speed > TERMINAL_VELOCITY ? this.vertical_speed = TERMINAL_VELOCITY : this.vertical_speed;  //Keep at terminal speed if it's reached
		this.vertical_position = this.vertical_position + this.vertical_speed;		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
			canvas.translateX(OFFSET);

		} else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
			canvas.translateX(-OFFSET);
		}
		else if(code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP) {
			if(fuel <= 0){  //If the fuel has been used, do nothing.
				return;
			}
			fuel -= UNIT_FUEL;
			canvas.setFuel(fuel);

			canvas.translateY(-OFFSET);
			canvas.setThrusting(true);
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		canvas.setThrusting(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}








