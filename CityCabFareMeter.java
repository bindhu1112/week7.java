import java.util.*;

interface NightService {
    double applyNightCharge(double fare);
}

abstract class Cab {
    double km;
    static final double MIN_FARE = 100.0;

    Cab(double km) {
        this.km = km;
    }

    abstract double calculateBaseFare();

    double calculateFare(boolean night) {
        double fare = Math.max(calculateBaseFare(), MIN_FARE);

        if (night && this instanceof NightService) {
            fare = ((NightService) this).applyNightCharge(fare);
        }

        return fare;
    }
}

class Mini extends Cab {
    Mini(double km) {
        super(km);
    }

    double calculateBaseFare() {
        return km * 10.0;
    }
}

class Sedan extends Cab implements NightService {
    Sedan(double km) {
        super(km);
    }

    double calculateBaseFare() {
        return km * 14.0;
    }

    public double applyNightCharge(double fare) {
        return fare * 1.20;
    }
}

class SUV extends Cab implements NightService {
    SUV(double km) {
        super(km);
    }

    double calculateBaseFare() {
        return km * 18.0;
    }

    public double applyNightCharge(double fare) {
        return fare * 1.20;
    }
}

public class CityCabFareMeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Cab[] cabs = new Cab[n];
        String[] types = new String[n];
        boolean[] night = new boolean[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            types[i] = sc.next().toUpperCase();
            double km = sc.nextDouble();
            String time = sc.next().toUpperCase();
            night[i] = time.equals("NIGHT");

            switch (types[i]) {
                case "MINI":
                    cabs[i] = new Mini(km);
                    break;
                case "SEDAN":
                    cabs[i] = new Sedan(km);
                    break;
                case "SUV":
                    cabs[i] = new SUV(km);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid cab type");
            }
        }

        for (int i = 0; i < n; i++) {
            if (night[i] && !(cabs[i] instanceof NightService)) {
                System.out.println(types[i] + ": night service not available");
            } else {
                double fare = cabs[i].calculateFare(night[i]);
                System.out.printf(Locale.US, "%s: %.2f%n",
                        types[i], fare);
                total += fare;
            }
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}