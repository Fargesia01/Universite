class Kitchen {
    Stock stockInput = new Stock("input", 10000, 10000);

    // Created new intermediate "C" stock 
    Stock intermediate = new Stock("intermediate", 0, 1);
    Stock stockOutput = new Stock("output", 0, 10000);

    // First two stoves to move from A to C, then two stoves to move from C to B
    Stove stove1 = new Stove(stockInput, intermediate, 5000);
    Stove stove2 = new Stove(stockInput, intermediate, 5000);

    Stove stove3 = new Stove(intermediate, stockOutput, 5000);
    Stove stove4 = new Stove(intermediate, stockOutput, 5000);

    public void work() {
    	System.out.println("Starting kitchen work ...");
    	long initialTime = System.currentTimeMillis();


        // Launch thread 1 and 2 first, then 3 and 4 (the order does not change anything)
   		stove1.start();
   		stove2.start();

        stove3.start();
        stove4.start();

        try {
            stove1.join();
            stove2.join();
            stove3.join();
            stove4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

   		stockInput.display();
   		stockOutput.display();
   		System.out.println("... done ("+((double)(System.currentTimeMillis() - initialTime)/1000)+" second(s))");
    }
    
    public static void main(String[] args) {
    	new Kitchen().work();
    }
}
