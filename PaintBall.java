import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintBall {

	private Color color;
	private int amount;

	public PaintBall (Color color, int amount) {
		this.color = color;
		this.amount = amount;
	}

	public void draw () {
		JFrame frame = new JFrame("Ping Ball");
		frame.setPreferredSize(new Dimension(600,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(new PaintPanel(color,amount));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// public static void main(String[] args) {
	// 	PaintBall paint = new PaintBall();
	// 	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	// 		@Override
	// 		public void run() {
	// 			new PaintBall().draw();
	// 		}
	// 	});
	// }
}