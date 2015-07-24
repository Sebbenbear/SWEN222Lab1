import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Timer extends Thread {

	private LanderFrame landerFrame;

	public Timer(){
		landerFrame = new LanderFrame();
		this.start();
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(100); // 0.1s delay
				landerFrame.fall();
				if(landerFrame.hitGround()){
					String message = checkCrashed();
					int r = JOptionPane.showConfirmDialog(new JLabel(message),
							"You landed!");
					return;
				}
				landerFrame.repaint(); // repaint the canvas
			} catch(InterruptedException e) {
				System.out.println("Timer error: " + e.getMessage());
			}
		}
	}

	private String checkCrashed() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String [] args){

		new Timer();
	}
}
