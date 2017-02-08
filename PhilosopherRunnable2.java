public class PhilosopherRunnable2 implements Runnable {

	private final int id;
	private int left;
	private int right;
	private static int total;
	private static final Object philosopherLock = new Object();
	public static final Chopstick2[] chopsticks = new Chopstick2[miscsubs2.NUMBER_PHILOSOPHERS];

	public PhilosopherRunnable2(int id) {
		this.id = id;
		left = this.id;
		right = (this.id + 1) % miscsubs2.NUMBER_PHILOSOPHERS;
		chopsticks[id] = new Chopstick2(id);
	}

	@Override
	public void run() {
		int LeftNeighbor = (id == 0) ? miscsubs2.NUMBER_PHILOSOPHERS - 1 : id - 1;
		int RightNeighbor = (id + 1) % miscsubs2.NUMBER_PHILOSOPHERS;

		while (true) {
			Loop1: for (int i = 0; i < miscsubs2.NUMBER_PHILOSOPHERS && i != id; i++) {
				if (miscsubs2.StarveCount[i] >= 16) {
					try {
						dining2.philosophers[i].start();
						break Loop1;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
			}

			miscsubs2.RandomDelay();
			if (miscsubs2.TotalEats == miscsubs2.MAX_EATS) {
				miscsubs2.LogResults();
				System.exit(0);
				// return;
			}

			if (chopsticks[left].isUp() || chopsticks[right].isUp()) {
				synchronized (this) {
					try {
						chopsticks[id].ready();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				chopsticks[right].pickUp();
				chopsticks[left].pickUp();

				miscsubs2.StartEating(id);
				miscsubs2.RandomDelay();
				miscsubs2.DoneEating(id);

				chopsticks[right].putDown();
				chopsticks[left].putDown();
				try {
					Thread.currentThread().sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}
