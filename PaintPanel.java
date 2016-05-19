import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.util.Random;

public class PaintPanel extends JPanel {
	private Color color;
	private int amount = 0;
	// ball location and information
	private int ballRadius = 30;
   	private int ballX;
   	private int ballY; 
	Random rn = new Random();

	public PaintPanel (Color color, int amount) {
		this.color = color;
		this.amount = amount;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for(int i=0;i<amount;i++) {
			ballX = rn.nextInt((550 - 30) + 1) + 30;
			ballY = rn.nextInt((550 - 30) + 1) + 30;
    	  	g.setColor(color);
          	g.fillOval((int) (ballX - ballRadius), (int) (ballY - ballRadius),
                (int)(2 * ballRadius), (int) (2 * ballRadius));
      	}	
	}
}