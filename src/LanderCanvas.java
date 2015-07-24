import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class LanderCanvas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int FUEL_X = 10;
	private static final int FUEL_Y = 10;
	
	private Polygon ground;
	private Polygon midGround;
	
	private int x = 300;
	private int y = 10;

	private int fuel = 100;
	private boolean thrusting = false;
	
	private int[] landerXS = {11,13,27,29,30,26,37,40,40,30,30,33,24,
			21,24,16,19, 16, 7, 0, 0,10,10, 3,14,10};
	private int[] landerYS = { 5, 0,0, 5, 20,20,35,35,40,40,35,35,20,
			20,25,25,20,20,35,35,40,40,35,35,20,20};
	

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	/**
	 * Paints various features on the canvas.
	 */
	@Override
	public void paint(Graphics g){
		drawBackground(g);
		drawTerrain(g);
		drawMoonLander(g);
		drawFuelGauge(g);
		drawThruster(g);
	}

	/**
	 * Draws some terrain - "fore and mid ground".
	 * @param g
	 */
	private void drawTerrain(Graphics g) {
		int[] groundXS = {0 ,30 ,40 ,100,140,160,180,200,220,230,300,310,330,350,
				360,400,410,435,460,465,500,545,560,575,580,600,600,0};
		int[] groundYS = {500,450,480,510,350,400,395,480,490,480,480,520,515,520,
				515,550,400,350,360,400,410,480,455,465,480,500,600,600};
		
		int[] midgroundXS = {10 ,40 ,60 ,120,145,170,200,200,220,230,300,310,430,350,
				360,400,410,435,460,465,500,545,560,575,580,600,600,0};
		int[] midgroundYS = {500,450,480,510,350,400,395,480,490,480,480,520,515,520,
				515,550,400,350,360,400,410,480,455,465,480,500,600,600};
		
		ground = new Polygon(groundXS, groundYS, groundXS.length);
		g.setColor(Color.GRAY);
		g.fillPolygon(ground);
		midGround = new Polygon(midgroundXS, midgroundYS, midgroundXS.length);
		g.setColor(Color.DARK_GRAY);
		g.fillPolygon(midGround);
	}

	/**
	 * Draws the background and the terrain
	 * @param g
	 */
	private void drawBackground(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0,getWidth(),getHeight());

		//draw stars of different positions, opacities and sizes
		g.setColor(Color.white);
		int numStars = 40;
		Random r = new Random();
		
		for(int i = 0; i < numStars; ++i){
			r = new Random();
			int x = r.nextInt(getWidth());
			int y = r.nextInt(getWidth());
			int diameter = r.nextInt(3-1) + 1;
			g.drawOval(x, y, diameter, diameter);
		}
	}

	/**
	 * Draw the spacecraft.
	 * @param g
	 */
	private void drawMoonLander(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.translate(x, y);
		g.fillPolygon(landerXS, landerYS, landerYS.length);
	
	}

	/**
	 * If the thruster is used, draw a flame below the spacecraft.
	 * @param g
	 */
	private void drawThruster(Graphics g) {
		if(thrusting){
//			Boring Triangle 
//			g.setColor(Color.orange);
//			g.translate(-13, 50);
//			g.fillPolygon(new int[] {10, 30, 20}, new int[] {10, 10, 20}, 3);
			
			g.translate(4, 50);
			g.setColor(Color.orange);
			int numStars = 40;
			Random r = new Random();
			
			for(int i = 0; i < numStars; ++i){
				r = new Random();
				int x = r.nextInt(10);
				int y = r.nextInt(20);
				int diameter = r.nextInt(3-1) + 1;
				g.drawOval(x, y, diameter, diameter);
			}
		}
	}

	/**
	 * Draws the fuel gauge.
	 * @param g
	 */
	private void drawFuelGauge(Graphics g) {
//		g.setColor(Color.black);
//		g.translate(FUEL_X/2 - 2, FUEL_Y/2);
//		g.drawString(String.format("%d", this.fuel), FUEL_X, FUEL_Y);

		g.setColor(Color.red);
		g.translate(FUEL_X, 2*(-FUEL_Y));
		g.fillRect(0, 0, this.fuel, 2);
	}

	/**
	 * Set the fuel parameter for updating
	 * @param fuel The remaining fuel
	 */
	public void setFuel(int fuel) {
		this.fuel = fuel; 		
	}
	
	public void translateY(int amount) {
		y += amount;
	}

	public void translateX(int amount){
		x += amount;
	}

	/**
	 * Returns whether or not the ship intersects with the ground.
	 * @return
	 */
	public boolean hitGround() {		
		//If any points on the feet are contained within the polygon, you have landed.
		for(int i = 0; i < landerXS.length; ++i ){
			if(ground.contains(landerXS[i] + x, landerYS[i] + y-2)){
				return true;
			}
		}
		return false;
	}

	public void setThrusting(boolean bool) {
		this.thrusting = bool;

	}
}