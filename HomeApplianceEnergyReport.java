import java.util.*;

interface SaverMode {
    double applySaver(double units);
}

abstract class Appliance {
    double hours;
    static final double COST_PER_UNIT = 8.0;

    Appliance(double hours) {
        this.hours = hours;
    }

    abstract double getPower();

    double calculateUnits() {
        return getPower() * hours / 1000.0;
    }

    double calculateCost(double units) {
        return units * COST_PER_UNIT;
    }
}

class Fridge extends Appliance {
    Fridge(double hours) {
        super(hours);
    }

    double getPower() {
        return 150.0;
    }
}

class AC extends Appliance implements SaverMode {
    AC(double hours) {
        super(hours);
    }

    double getPower() {
        return 1500.0;
    }

    public double applySaver(double units) {
        return units * 0.75;
    }
}

class TV extends Appliance {
    TV(double hours) {
        super(hours);
    }

    double getPower() {
        return 100.0;
    }
}

class Washer extends Appliance implements SaverMode {
    Washer(double hours) {
        super(hours);
    }

    double getPower() {
        return 500.0;
    }

    public double applySaver(double units) {
        return units * 0.75;
    }
}

public class HomeApplianceEnergyReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        Appliance[] appliances = new Appliance[n];
        String[] names = new String[n];
        boolean[] saver = new boolean[n];
        boolean[] supported = new boolean[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().trim().split("\\s+");

            names[i] = input[0].toUpperCase();
            double hours = Double.parseDouble(input[1]);
            saver[i] = input.length > 2
                    && input[2].equalsIgnoreCase("SAVER");

            switch (names[i]) {
                case "FRIDGE":
                    appliances[i] = new Fridge(hours);
                    break;
                case "AC":
                    appliances[i] = new AC(hours);
                    break;
                case "TV":
                    appliances[i] = new TV(hours);
                    break;
                case "WASHER":
                    appliances[i] = new Washer(hours);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Invalid appliance type");
            }

            supported[i] = appliances[i] instanceof SaverMode;
        }

        for (int i = 0; i < n; i++) {
            if (saver[i] && !supported[i]) {
                System.out.println(names[i] +
                        ": saver mode not supported");
                continue;
            }

            Appliance a = appliances[i];
            double units = a.calculateUnits();

            if (saver[i]) {
                units = ((SaverMode) a).applySaver(units);
            }

            double cost = a.calculateCost(units);

            System.out.printf(Locale.US,
                    "%s: Units=%.2f Cost=%.2f%n",
                    names[i], units, cost);

            total += cost;
        }

        System.out.printf(Locale.US, "Total Cost: %.2f%n", total);
        sc.close();
    }
}