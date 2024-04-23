import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Added a lock attribute to lock the object from outside
class Stock {
    private int nbFood;
    private String name;
    private Lock lock;

    public Stock(String name, int nbFood) {
        this.nbFood = nbFood;
        this.name = name;
        this.lock = new ReentrantLock();
    }

    public void put() {
        nbFood++;
    }

    public void get() {
        nbFood--;
    }

    public void display() {
        System.out.println("The stock " + name + " contains " + nbFood + " food.");
    }

    // Returns the amount of food left
    public int getNbFood()
    {
        return (nbFood);
    }

    // Locks the current instance
    public void lock() {
        lock.lock();
    }
    
    // Unlocks the current instance
    public void unlock() {
        lock.unlock();
    }

    // "Unit test" for class Stock
    static public void main(String[] args) {
        Stock stock = new Stock("test", 5);
        stock.put();
        stock.display();
        stock.get();
        stock.display();
    }
}
