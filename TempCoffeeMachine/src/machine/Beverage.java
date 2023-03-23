package machine;

public enum Beverage {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75,20,7),
    CAPPUCCINO(200, 100, 12, 6);
    //POJMENOVANÝ PŘEDKONSTRUOVANÝ INSTANCE

    private final int water;
    private final int milk;
    private final int coffee;
    private final int price;

    Beverage(int water, int milk, int coffee, int price) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.price = price;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffee() {
        return coffee;
    }

    public int getPrice() {
        return price;
    }
}
