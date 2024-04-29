/**
 * Objects that are instances of the Stove class represent stoves to prepare
 * food. The principle is as follows: the call to prepare() picks an element
 * from Stock A, waits for 64 ms, and puts an element to stock B. The work() method
 * runs nbPrepare times the prepare() method, nbPrepare being set by the constructor.
 */
class Stove extends Thread {

    private Stock A;
    private Stock B;
    private int nbPrepare;

    public Stove(Stock A, Stock B, int nbPrepare) {
        this.A = A;
        this.B = B;
        this.nbPrepare = nbPrepare;
    }

    // Proceeds a single A to B
    // First locks the stock, then puts or removes food from it 
    // Then prints out the food left in stock 
    // Finally unlocks the stock
    // PS: I did use sychronized first, but I want to keep the stock locked until I've printed out the amount of food left, so using a lock seemd llike a good approach for this
    public void prepare() {
        A.lock();
        A.get();
        System.out.println(Thread.currentThread().getName() + ": stock A contains " + A.getNbFood() + " food.");
        A.unlock();

        try { Thread.sleep(64); } catch(InterruptedException e) {}

        B.lock();
        B.put();
        System.out.println(Thread.currentThread().getName() + ": stock B contains " + B.getNbFood() + " food.");
        B.unlock();
    }

    //Proceeds to nbPrepare preparations
    public void run() {
        for(; nbPrepare > 0; nbPrepare--)
            prepare();
    }

    // "Unit test" for class Stove (has no use for the rest of the lab!)
    static public void main(String[] args) {
        Stock stockInput = new Stock("input", 4);
        Stock stockOutput = new Stock("output", 1);
        Stove stove = new Stove(stockInput, stockOutput, 2);
        stove.start();
        try {
            stove.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stockInput.display();
        stockOutput.display();
    }
}
