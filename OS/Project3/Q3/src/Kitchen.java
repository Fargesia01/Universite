class Kitchen {
    Stock stockInput = new Stock("input", 16);
    Stock stockOutput = new Stock("output", 0);
    Stove stove1 = new Stove(stockInput, stockOutput, 8);
    Stove stove2 = new Stove(stockInput, stockOutput, 8);
    
    public void work() {
    	System.out.println("Starting kitchen work ...");
    	long initialTime = System.currentTimeMillis();

   		stove1.start();
   		stove2.start();

        try {
            stove1.join();
            stove2.join();
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
