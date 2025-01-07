// Muskan Zehra 62553
public class TrafficLight {
	private String state;
	private int timer;
	
	public TrafficLight() {
		this.state = "Red";
		this.timer = 10;
	}
	
	public void updateState() {
		switch(state) {
		case "Red":
			state = "Green";
			timer = 10;
			break;
		case "Green":
			state = "Yellow";
			timer = 5;
			break;
		case "Yellow":
			state = "Red";
			timer = 10;
			break;
		}
	}
	
	public String getState() {
		return state;
	}
}
