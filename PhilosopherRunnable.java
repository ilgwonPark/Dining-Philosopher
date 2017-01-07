public class PhilosopherRunnable implements Runnable {

	private final int id;
	private static int total;
	private static final Object philosopherLock = new Object();

	public PhilosopherRunnable(int id) {
		this.id = id;
	}

	private int getLeftChopstickId() {
		return id;
	}

	private int getRightChopstickId() {
		return (id + 1) % miscsubs.NUMBER_PHILOSOPHERS;
	}

	@Override
	public void run() {

		while (true) {
			miscsubs.RandomDelay();
			synchronized (philosopherLock) {
				if (miscsubs.TotalEats == miscsubs.MAX_EATS) {
					synchronized (this) {
						notifyAll(); // max reached, notify main thread.
					}
					return;
				}
				if (dining.chopsticks[getLeftChopstickId()].isDown()
						&& dining.chopsticks[getRightChopstickId()].isDown()) {

					dining.chopsticks[getRightChopstickId()].pickUp(id);
					dining.chopsticks[getLeftChopstickId()].pickUp(id);
					miscsubs.StartEating(id);
					miscsubs.RandomDelay();
					miscsubs.DoneEating(id);
					dining.chopsticks[getRightChopstickId()].putDown();
					dining.chopsticks[getLeftChopstickId()].putDown();
				}
			}
		}
	}
}
