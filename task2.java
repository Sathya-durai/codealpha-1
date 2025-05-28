package codealpha;
import java.util.*;
class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double cash;

    Portfolio(double initialCash) {
        this.cash = initialCash;
    }

    void buy(String symbol, int quantity, double price) {
        double cost = quantity * price;
        if (cash >= cost) {
            cash -= cost;
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    void sell(String symbol, int quantity, double price) {
        int owned = holdings.getOrDefault(symbol, 0);
        if (owned >= quantity) {
            holdings.put(symbol, owned - quantity);
            cash += quantity * price;
            System.out.println("Sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("You don't own enough shares.");
        }
    }

    void showPortfolio(Map<String, Stock> market) {
        System.out.println("\n--- Portfolio ---");
        double total = cash;
        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            double price = market.get(symbol).price;
            double value = qty * price;
            total += value;
            System.out.printf("%s: %d shares @ %.2f = %.2f\n", symbol, qty, price, value);
        }
        System.out.printf("Cash: %.2f\n", cash);
        System.out.printf("Total Portfolio Value: %.2f\n", total);
    }
}
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);

	        // Simulated Market Data
	        Map<String, Stock> market = new HashMap<>();
	        market.put("AAPL", new Stock("AAPL", 150.0));
	        market.put("GOOGL", new Stock("GOOGL", 2800.0));
	        market.put("TSLA", new Stock("TSLA", 700.0));
	        market.put("AMZN", new Stock("AMZN", 3400.0));

	        Portfolio portfolio = new Portfolio(10000.0); // Starting with $10,000

	        while (true) {
	            System.out.println("\n1. View Market");
	            System.out.println("2. Buy Stock");
	            System.out.println("3. Sell Stock");
	            System.out.println("4. View Portfolio");
	            System.out.println("5. Exit");
	            System.out.print("Select an option: ");

	            int choice = scanner.nextInt();

	            if (choice == 1) {
	                System.out.println("\n--- Market Data ---");
	                for (Stock stock : market.values()) {
	                    System.out.printf("%s: %.2f\n", stock.symbol, stock.price);
	                }
	            } else if (choice == 2) {
	                System.out.print("Enter stock symbol to buy: ");
	                String symbol = scanner.next().toUpperCase();
	                if (!market.containsKey(symbol)) {
	                    System.out.println("Invalid stock symbol.");
	                    continue;
	                }
	                System.out.print("Enter quantity: ");
	                int qty = scanner.nextInt();
	                portfolio.buy(symbol, qty, market.get(symbol).price);
	            } else if (choice == 3) {
	                System.out.print("Enter stock symbol to sell: ");
	                String symbol = scanner.next().toUpperCase();
	                if (!market.containsKey(symbol)) {
	                    System.out.println("Invalid stock symbol.");
	                    continue;
	                }
	                System.out.print("Enter quantity: ");
	                int qty = scanner.nextInt();
	                portfolio.sell(symbol, qty, market.get(symbol).price);
	            } else if (choice == 4) {
	                portfolio.showPortfolio(market);
	            } else if (choice == 5) {
	                System.out.println("Exiting. Happy Trading!");
	                break;
	            } else {
	                System.out.println("Invalid option.");
	            }
	        }

	        scanner.close();

	}

}
