import java.util.*;

interface Insurable {
    double calculateInsurance();
}

abstract class Parcel {
    double weight;
    double declaredValue;

    Parcel(double weight, double declaredValue) {
        this.weight = weight;
        this.declaredValue = declaredValue;
    }

    abstract double calculateCharge();

    double calculateTotal() {
        return calculateCharge();
    }

    double getInsurance() {
        return 0.0;
    }
}

class StandardParcel extends Parcel {
    StandardParcel(double weight, double value) {
        super(weight, value);
    }

    double calculateCharge() {
        return 40 + 10 * weight;
    }
}

class ExpressParcel extends Parcel implements Insurable {
    ExpressParcel(double weight, double value) {
        super(weight, value);
    }

    double calculateCharge() {
        return 80 + 15 * weight;
    }

    public double calculateInsurance() {
        return declaredValue * 0.02;
    }

    double getInsurance() {
        return calculateInsurance();
    }

    double calculateTotal() {
        return calculateCharge() + getInsurance();
    }
}

class FragileParcel extends Parcel implements Insurable {
    FragileParcel(double weight, double value) {
        super(weight, value);
    }

    double calculateCharge() {
        return 40 + 10 * weight + 50;
    }

    public double calculateInsurance() {
        return declaredValue * 0.02;
    }

    double getInsurance() {
        return calculateInsurance();
    }

    double calculateTotal() {
        return calculateCharge() + getInsurance();
    }
}

public class ParcelShippingDesk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Parcel[] parcels = new Parcel[n];
        String[] types = new String[n];
        double grandTotal = 0;

        for (int i = 0; i < n; i++) {
            types[i] = sc.next().toUpperCase();
            double weight = sc.nextDouble();
            double value = sc.nextDouble();

            switch (types[i]) {
                case "STANDARD":
                    parcels[i] = new StandardParcel(weight, value);
                    break;
                case "EXPRESS":
                    parcels[i] = new ExpressParcel(weight, value);
                    break;
                case "FRAGILE":
                    parcels[i] = new FragileParcel(weight, value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid parcel type");
            }
        }

        for (int i = 0; i < n; i++) {
            Parcel p = parcels[i];
            double charge = p.calculateCharge();
            double insurance = p.getInsurance();
            double amount = p.calculateTotal();

            System.out.printf(Locale.US,
                    "%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    types[i], charge, insurance, amount);

            grandTotal += amount;
        }

        System.out.printf(Locale.US, "Grand Total: %.2f%n", grandTotal);
        sc.close();
    }
}