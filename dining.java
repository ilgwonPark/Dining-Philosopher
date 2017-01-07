
class dining {

	public static final Thread[] philosophers = new Thread[5];
	public static final Chopstick[] chopsticks = new Chopstick[5];

	public static void main(String args[]) {
		System.out.println("Starting the Dining Philosophers Simulation\n");
		miscsubs.InitializeChecking();
		// Your code here...

		// init chopsticks
		for (int i = 0; i < miscsubs.NUMBER_CHOPSTICKS; i++) {
			chopsticks[i] = new Chopstick(i);
		}

		// init philosopher threads.
		for (int i = 0; i < miscsubs.NUMBER_PHILOSOPHERS; i++) {
			philosophers[i] = new Thread(new PhilosopherRunnable(i));
			philosophers[i].start();
		}

		synchronized (philosophers[0]) {
			try {
				philosophers[0].wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// End of your code
		System.out.println("Simulation Ends..");
		miscsubs.LogResults();
	}
};
