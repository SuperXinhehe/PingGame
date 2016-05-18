// client ping game to send request to the servers (vm1,vm2,vm3)
// sending message to servers
import java.lang.*;
import java.io.*;
import java.net.*;

// SWING
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PingGameHost {
	// GUI
	private static JFrame frame = null;
	private static JButton btn = null;
	private static JPanel panel = null;
	private static JLabel label = null;

	// CONNECTION
	public static int port = 1234;
	public static String host = "localhost";

	// SERVER
	// protected Socket socket;

	// data
	String color = "None";
	PingBallMsg msg;
	int amount = 2;
	// public void setSocket(Socket socket) {
	// 	this.socket = socket;
	// }

	public void initGUI() {
		btn = new JButton("Red");
		panel = new JPanel(new FlowLayout());
		label = new JLabel("port: "+port+", host: "+host+"...", SwingConstants.LEFT);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = btn.getText();
				msg = new PingBallMsg(color,amount);
				System.out.println("requested a "+color+" ball");
				connect();
			}
		});
		panel.add(btn);
		panel.add(label);
		frame = new JFrame();
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(200,200);
		frame.pack();
		frame.setVisible(true);
	}
	// connect to the server and send the message to server
	// includes the color of the ball and amount of balls
	public void connect () {
		try {
			Socket socket = new Socket(host, port);
			System.out.println("Server Has Connected!");
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(msg);
			// get the response from server
			// should be a string of success or not
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
			System.out.println(reader.readLine());
			reader.close();
			oos.close();
			os.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PingGameHost client = new PingGameHost();
		if (args.length < 2) {
			System.out.println("Using default setting");
		}
		else {
			host = args[0];
			port = Integer.parseInt(args[1]);
		}
		client.initGUI();
	}
}