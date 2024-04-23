class Kitchen {
    Stock stockInput = new Stock("input", 16);

    // Created new intermediate "C" stock 
    Stock intermediate = new Stock("intermediate", 0);
    Stock stockOutput = new Stock("output", 0);

    // Now each stove needs to process 16 food to get all of the food from A to C
    Stove stove1 = new Stove(stockInput, intermediate, 16);
    Stove stove2 = new Stove(intermediate, stockOutput, 8);

    // Third stove that shares work with stove 2
    Stove stove3 = new Stove(intermediate, stockOutput, 8);
    
    public void work() {
    	System.out.println("Starting kitchen work ...");
    	long initialTime = System.currentTimeMillis();

   		stove2.start();
        stove3.start();

        // Launches stove 1 last
   		stove1.start();

        try {
            stove1.join();
            stove2.join();
            stove3.join();
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
