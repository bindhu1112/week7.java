import java.util.*;

abstract class Delivery {
    double weight;
    double distance;

    Delivery(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    abstract double calculateFee();

    abstract String getType();
}

class Standard extends Delivery {
    Standard(double weight, double distance) {
        super(weight, distance);
    }

    double calculateFee() {
        return 5 + (0.50 * weight) + (0.10 * distance);
    }

    String getType() {
        return "STANDARD";
    }
}

class Express extends Delivery {
    Express(double weight, double distance) {
        super(weight, distance);
    }

    double calculateFee() {
        return 15 + weight + (0.20 * distance);
    }

    String getType() {
        return "EXPRESS";
    }
}

class International extends Delivery {
    double customsFee;

    International(double weight, double distance, double customsFee) {
        super(weight, distance);
        this.customsFee = customsFee;
    }

    double calculateFee() {
        return 25 + (2 * weight) + (0.50 * distance) + customsFee;
    }

    String getType() {
        return "INTERNATIONAL";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Delivery[] deliveries = new Delivery[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double distance = sc.nextDouble();

            if (type.equals("STANDARD")) {
                deliveries[i] = new Standard(weight, distance);
            } else if (type.equals("EXPRESS")) {
                deliveries[i] = new Express(weight, distance);
            } else {
                double customsFee = sc.nextDouble();
                deliveries[i] = new International(weight, distance, customsFee);
            }
        }

        for (Delivery d : deliveries) {
            double fee = d.calculateFee();
            System.out.printf("%s: %.2f%n", d.getType(), fee);
            total += fee;
        }

        System.out.printf("Total: %.2f%n", total);
    }
}