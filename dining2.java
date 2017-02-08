
class dining2 {

	public static final Thread[] philosophers = new Thread[miscsubs2.NUMBER_PHILOSOPHERS];
	public static final Chopstick2[] chopsticks = new Chopstick2[miscsubs2.NUMBER_PHILOSOPHERS];
	
	public static void main(String args[]) {
		System.out.println("Starting the Dining Philosophers Simulation\n");
        miscsubs2.InitializeChecking();

		// init philosopher threads.
		for (int i = 0; i < miscsubs2.NUMBER_PHILOSOPHERS; i++) {
			philosophers[i] = new Thread(new PhilosopherRunnable2(i));
			philosophers[i].start();
		}

		// End of your code
		System.out.println("Simulation Ends..");
		miscsubs2.LogResults();
	}
	

};
