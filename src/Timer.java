
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
				landerFrame.setY(10);
				landerFrame.repaint(); // repaint the canvas
			} catch(InterruptedException e) {
				System.out.println("Timer error: " + e.getMessage());
			}
		}
	}

	public static void main(String [] args){

		new Timer();
	}
}
