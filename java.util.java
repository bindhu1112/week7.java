import java.util.*;

abstract class Vehicle {
    int hours;

    Vehicle(int hours) {
        this.hours = hours;
    }

    abstract double calculateCharge();

    abstract String getType();

    void display() {
        System.out.printf("%s: %.2f%n", getType(), calculateCharge());
    }
}

class Bike extends Vehicle {
    Bike(int hours) {
        super(hours);
    }

    double calculateCharge() {
        return hours * 10;
    }

    String getType() {
        return "BIKE";
    }
}

class Car extends Vehicle {
    Car(int hours) {
        super(hours);
    }

    double calculateCharge() {
        return 30 + (hours - 1) * 20;
    }

    String getType() {
        return "CAR";
    }
}

class Truck extends Vehicle {
    Truck(int hours) {
        super(hours);
    }

    double calculateCharge() {
        return Math.max(100, hours * 50);
    }

    String getType() {
        return "TRUCK";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Vehicle[] vehicles = new Vehicle[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hours = sc.nextInt();

            if (type.equals("BIKE"))
                vehicles[i] = new Bike(hours);
            else if (type.equals("CAR"))
                vehicles[i] = new Car(hours);
            else
                vehicles[i] = new Truck(hours);
        }

        for (Vehicle v : vehicles) {
            v.display();
            total += v.calculateCharge();
        }

        System.out.printf("Total: %.2f%n", total);
    }
}