class Stove extends Thread {

    private Stock A;
    private Stock B;
    private int nbPrepare;


    // Constructor that initialiases attributes
    public Stove(Stock A, Stock B, int nbPrepare) {
        this.A = A;
        this.B = B;
        this.nbPrepare = nbPrepare;
    }

    // Proceeds a single A to B
    // Threads 1 and 2 process food from A to C, and threads 3 and 4 from C to B.
    // The notifyAll() function is used here because we don't want thread 3 to wake up thread 4 when he emptied C.
    // Same goes for the first part, we don't want thread 1 to wake up thread 2 after filling C
    public void prepare() {
        synchronized (A) {
            try { 
                while (A.getNbFood() == 0) {
                    A.wait();
                }   
                A.get();
                A.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (B) {
            try {
                while (B.isFull()) {
                    B.wait();
                }
                B.put();
                B.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } 
    }

    // Proceeds to nbPrepare preparations
    public void run() {
        for(; nbPrepare > 0; nbPrepare--) {
            prepare();
        }
    }

    // "Unit test" for class Stove (has no use for the rest of the lab!)
    /*static public void main(String[] args) {
        Stock stockInput = new Stock("input", 4, 4);
        Stock stockOutput = new Stock("output", 1, 1);
        Stove stove = new Stove(stockInput, stockOutput, 2);
        stove.start();
        try {
            stove.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stockInput.display();
        stockOutput.display();
    }*/
}
