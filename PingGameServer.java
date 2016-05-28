import java.lang.*;
import java.io.*;
import java.net.*;
import java.awt.Color;

public class PingGameServer {
	// server 
	private static int port = 1234;
	public static PingBallMsg msg;

	// balls information
	static int amount = 0;
	static Color color;

	public static void main(String args[]) {
		while (true) {
		try {
			ServerSocket ss = new ServerSocket(port);
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(s.getOutputStream()));

				msg = (PingBallMsg) ois.readObject();
				if (msg!=null) {
					System.out.println(msg.color + " " + msg.amount);
					// convert to color
					switch (msg.color) {
						case "Red":
							color = Color.RED;
						break;
						case "Blue":
							color = Color.BLUE;
						break;
						case "Green":
							color = Color.GREEN;
						break;
					}
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							new PaintBall(color,msg.amount + amount).draw();
						}
					});
					writer.write("Draw "+msg.amount+" numbers of "+msg.color+" balls");

				}
				else {
					writer.write("ERROR FROM SERVER");
				}
				writer.close();
				is.close();
				s.close();
				ss.close();
		}
		catch(Exception e) {
			System.out.println("ERROR: NOT ABLE TO RECEIVE");
		}
		}
	}
}