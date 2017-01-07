
public class Chopstick {

	private static final int PHILOSOPHER_NUM_NONE = -1;

	private final int id;
	private int philosopherNum = PHILOSOPHER_NUM_NONE;

	public Chopstick(int id) {
		this.id = id;
	}

	public synchronized void pickUp(int philosopherNum) {
		if (philosopherNum != PHILOSOPHER_NUM_NONE) {
			this.philosopherNum = philosopherNum;
		}
	}

	public synchronized boolean isDown() {
		return this.philosopherNum == PHILOSOPHER_NUM_NONE;
	}

	public synchronized void putDown() {
		this.philosopherNum = PHILOSOPHER_NUM_NONE;
	}
}
