import java.util.*;

abstract class Payment {
    double amount;

    Payment(double amount) {
        this.amount = amount;
    }

    abstract double calculate();

    abstract String getType();
}

class Card extends Payment {
    Card(double amount) {
        super(amount);
    }

    double calculate() {
        return amount * 1.02;
    }

    String getType() {
        return "CARD";
    }
}

class Wallet extends Payment {
    Wallet(double amount) {
        super(amount);
    }

    double calculate() {
        return amount * 1.01;
    }

    String getType() {
        return "WALLET";
    }
}

class BankTransfer extends Payment {
    BankTransfer(double amount) {
        super(amount);
    }

    double calculate() {
        return amount;
    }

    String getType() {
        return "BANKTRANSFER";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Payment[] payments = new Payment[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();

            if (type.equals("CARD")) {
                payments[i] = new Card(amount);
            } else if (type.equals("WALLET")) {
                payments[i] = new Wallet(amount);
            } else {
                payments[i] = new BankTransfer(amount);
            }
        }

        for (Payment p : payments) {
            double value = p.calculate();
            System.out.printf("%s: %.2f%n", p.getType(), value);
            total += value;
        }

        System.out.printf("Total: %.2f%n", total);
    }
}