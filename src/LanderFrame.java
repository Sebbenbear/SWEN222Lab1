import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class LanderFrame extends javax.swing.JFrame implements java.awt.event.KeyListener {

	private LanderCanvas canvas;
	public static final int OFFSET = 5;
	public static final int WINDOW_SIZE = 600;

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

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
			canvas.translateX(OFFSET);

		} else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
			canvas.translateX(-OFFSET);
		}
	}

	public void setY(int amount){
		canvas.translateY(amount);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}
}








