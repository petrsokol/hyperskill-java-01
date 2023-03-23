package machine;

import static machine.Beverage.*;

public class Machine {
    private MachineState state;
    private int water;
    private int milk;
    private int coffee;
    private int cups;
    private int money;

    public Machine(int water, int milk, int coffee, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.money = money;

        setMainState();
    }

    public boolean isWorking() {
        return state != MachineState.OFF;
    }

    public void execute(String input) {
        switch (state) {
            case MAIN -> {
                setState(input);
            }
            case BUYING -> {
                handleBuying(input);
                setMainState();
            }
            case FILL_WATER -> {
                water += Integer.parseInt(input);
                System.out.print("Write how many ml of milk do you want to add:\n> ");
                state = MachineState.FILL_MILK;
            }
            case FILL_MILK -> {
                milk += Integer.parseInt(input);
                System.out.print("Write how many grams of coffee you want to add:\n> ");
                state = MachineState.FILL_COFFEE;
            }
            case FILL_COFFEE -> {
                coffee += Integer.parseInt(input);
                System.out.print("Write how many disposable cups of coffee do you want to add:\n> ");
                state = MachineState.FILL_CUPS;
            }
            case FILL_CUPS -> {
                cups += Integer.parseInt(input);
                setMainState();
            }
        }
    }

    //buy, fill, take, remaining, exit
    public void setState(String command) {
        switch (command) {
            case "remaining" -> {
                printState();
                setMainState();
            }
            case "exit" -> {
                state = MachineState.OFF;
            }
            case "take" -> {
                giveMoney();
                setMainState();
            }
            case "fill" -> {
                System.out.print("Write how many ml of water do you want to add:\n> ");
                state = MachineState.FILL_WATER;
            }
            case "buy" -> {
                System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:\n> ");
                state = MachineState.BUYING;
            }
            default -> {
                System.out.println("Unexpected action.");
                setMainState();
            }
        }
    }

    private void handleBuying(String input) {
        Beverage recipe;

        switch (input) {
            case "back" -> {
                setMainState();
                return;
            }
            case "1" -> recipe = ESPRESSO;
            case "2" -> recipe = LATTE;
            case "3" -> recipe = CAPPUCCINO;
            default -> {
                System.out.println("Unexpected option.");
                return;
            }
        }

        makeCoffee(recipe);
    }

    private void makeCoffee(Beverage recipe) {
        if (water < recipe.getWater()) {
            System.out.println("Sorry, not enough water!");
            return;
        }
        if (milk < recipe.getMilk()) {
            System.out.println("Sorry, not enought milk!");
            return;
        }
        if (coffee < recipe.getCoffee()) {
            System.out.println("Sorry, not enough coffee beans!");
            return;
        }
        if (cups < 1) {
            System.out.println("Sorry, not enough cups!");
            return;
        }

        System.out.println("I have enough resources, making you a coffee");

        water -= recipe.getWater();
        milk -= recipe.getMilk();
        coffee -= recipe.getCoffee();
        money += recipe.getPrice();
        cups--;
    }

    private void printState() {
        System.out.printf("The coffee machine has:\n%d of water\n%d of milk\n", water, milk);
        System.out.printf("%d of coffee beans\n%d of disposable cups\n%d of money\n", coffee, cups, money);
    }

    private void setMainState() {
        state = MachineState.MAIN;
        System.out.print("\nWrite action (buy, fill, take, remaining, exit):\n> ");
    }

    private void giveMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }
}
