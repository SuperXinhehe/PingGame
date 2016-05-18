import java.io.Serializable;

public class PingBallMsg implements Serializable {
	String color = "";
	int amount = 0;
	public PingBallMsg (String color, int amount) {
		this.color = color;
		this.amount = amount;
	}
}