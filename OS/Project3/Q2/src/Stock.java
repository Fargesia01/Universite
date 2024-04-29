/**
 * Objects of class Stock represent a set of food. Food is not effectively stored,
 * only a counter is used to represent how much food is available.
 * 
 * It could be possible to use a more realistic queue (FIFO) for the Stock representation.
 * This is left as an exercise for home work. *
 */
class Stock {
    private int nbFood;
    private String name;

    public Stock(String name, int nbFood) {
        this.nbFood = nbFood;
        this.name = name;		
    }

    // Added synchronized keyword to lock the ressource while executing
    public synchronized void put() {
        nbFood++;
    }

    // Added synchronized keyword to lock the ressource while executing
    public synchronized void get() {
        nbFood--;
    }

    public void display() {
        System.out.println("The stock " + name + " contains " + nbFood + " food.");
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
