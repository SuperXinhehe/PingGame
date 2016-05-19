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
	private static JTextField textbox = null;
	private static JComboBox color_selector = null;
	private static JTextField textbox1 = null;
	private static JTextField textbox2 = null;
	private static JTextField textbox3 = null;

	// CONNECTION
	public static int port = 1234;
	public static String host = "localhost";

	// SERVER
	// protected Socket socket;

	// data
	String color = "None";
	PingBallMsg msg;
	int amount = 1;

	public void initGUI() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(200,380));
		frame.setTitle("Ping Ball Game");
		btn = new JButton("Submit");
		panel = new JPanel(new FlowLayout());
		DefaultComboBoxModel colors = new DefaultComboBoxModel();
		colors.addElement("Red");
		colors.addElement("Green");
		colors.addElement("Blue");
		textbox = new JTextField(6);
		color_selector = new JComboBox(colors);
		color_selector.setSelectedIndex(0);
		label = new JLabel("port: "+port+", host: "+host+"...", SwingConstants.LEFT);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amount = Integer.parseInt(textbox.getText());
				color = (String) color_selector.getSelectedItem();
				msg = new PingBallMsg(color,amount);
				System.out.println("requested a "+color+" ball");
				connect();
			}
		});
		JLabel colorlabel = new JLabel("Ball Color:");
		JLabel amountlabel = new JLabel("Amount:");
		JLabel redvmlabel = new JLabel("VM Red IP Address: ");
		JLabel greenvmlabel = new JLabel("VM Green IP Address: ");
		JLabel bluevmlabel = new JLabel("VM Blue IP Address: ");
		textbox1 = new JTextField(10);
		textbox1.setText("localhost");
		textbox2 = new JTextField(10);
		textbox2.setText("localhost");
		textbox3 = new JTextField(10);
		textbox3.setText("localhost");
		textbox.setText("1");
		panel.add(redvmlabel);
		panel.add(textbox1);
		panel.add(greenvmlabel);
		panel.add(textbox2);
		panel.add(bluevmlabel);
		panel.add(textbox3);
		panel.add(colorlabel);
		panel.add(color_selector);
		panel.add(amountlabel);
		panel.add(textbox);
		panel.add(btn);
		panel.add(label);
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