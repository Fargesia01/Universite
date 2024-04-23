class Stock {
    private int nbFood;
    private String name;

    public Stock(String name, int nbFood) {
        this.nbFood = nbFood;
        this.name = name;
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

    // Returns the stock name
    public String getName() {
        return (name);
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
