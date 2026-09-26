import java.util.*;

abstract class Transport {
    double distance;

    Transport(double distance) {
        this.distance = distance;
    }

    abstract double calculateFare();

    abstract String getType();
}

class Bus extends Transport {
    Bus(double distance) {
        super(distance);
    }

    double calculateFare() {
        return Math.min(10, 2 + (0.10 * distance));
    }

    String getType() {
        return "BUS";
    }
}

class Train extends Transport {
    Train(double distance) {
        super(distance);
    }

    double calculateFare() {
        return 3 + (0.15 * distance);
    }

    String getType() {
        return "TRAIN";
    }
}

class Metro extends Transport {
    double peakHourFactor;

    Metro(double distance, double peakHourFactor) {
        super(distance);
        this.peakHourFactor = peakHourFactor;
    }

    double calculateFare() {
        return (1.50 + (0.20 * distance)) * peakHourFactor;
    }

    String getType() {
        return "METRO";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Transport[] transports = new Transport[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double distance = sc.nextDouble();

            if (type.equals("BUS")) {
                transports[i] = new Bus(distance);
            } else if (type.equals("TRAIN")) {
                transports[i] = new Train(distance);
            } else {
                double factor = sc.nextDouble();
                transports[i] = new Metro(distance, factor);
            }
        }

        for (Transport t : transports) {
            double fare = t.calculateFare();
            System.out.printf("%s: %.2f%n", t.getType(), fare);
            total += fare;
        }

        System.out.printf("Total: %.2f%n", total);
    }
}