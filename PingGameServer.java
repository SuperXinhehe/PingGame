import java.lang.*;
import java.io.*;
import java.net.*;

public class PingGameServer {
	// server 
	private static int port = 1234;
	public static PingBallMsg msg;

	public static void main(String args[]) {
		try {
			ServerSocket ss = new ServerSocket(port);
			// while (true) {
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(s.getOutputStream()));

				msg = (PingBallMsg) ois.readObject();
				if (msg!=null) {
					System.out.println(msg.color);
					System.out.println(msg.amount);
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
			// BufferedReader in = new BufferedReader(new 
			// 	InputStreamReader(skt.getInputStream()));
			// System.out.println("Received String: ");
			// while (!in.ready()) {
			// 	System.out.println(in.readLine());
			// 	System.out.println();
			// 	in.close();
			// }
		catch(Exception e) {
			System.out.println("ERROR: NOT ABLE TO RECEIVE");
		}
		// }
	}
}