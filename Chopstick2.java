
public class Chopstick2 {

	// private static final boolean CHOPSTICK_DOWN = false;

	int chopid;
	boolean CHOPSTICK_UP = false;

	public Chopstick2(int id) {
		chopid = id;
	}

	public synchronized void pickUp() {
		CHOPSTICK_UP = true;
		System.out.println("Pick up the chopstick " + chopid + " Chopstick");
	}

	public synchronized void ready() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Pick up the chopstick " + chopid + " Chopstick");
	}

	public boolean isUp() {
		return CHOPSTICK_UP;
	}

	public synchronized void putDown() {
		this.CHOPSTICK_UP = false;
		this.notifyAll();
		System.out.println("Put down the chopstick " + chopid + " Chopstick");
	}

}
