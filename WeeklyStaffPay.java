import java.util.*;

abstract class Staff {
    String name;

    Staff(String name) {
        this.name = name;
    }

    abstract double calculatePay();
}

class FullTimeStaff extends Staff {
    double salary;

    FullTimeStaff(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    double calculatePay() {
        return salary;
    }
}

class HourlyStaff extends Staff {
    double hours, rate;

    HourlyStaff(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    double calculatePay() {
        if (hours <= 40) {
            return hours * rate;
        }
        return 40 * rate + (hours - 40) * rate * 1.5;
    }
}

class InternStaff extends Staff {
    double stipend;

    InternStaff(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    double calculatePay() {
        return stipend;
    }
}

public class WeeklyStaffPay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Staff[] staff = new Staff[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            String name = sc.next();

            switch (type) {
                case "FULLTIME":
                    staff[i] = new FullTimeStaff(name, sc.nextDouble());
                    break;
                case "HOURLY":
                    staff[i] = new HourlyStaff(
                        name, sc.nextDouble(), sc.nextDouble());
                    break;
                case "INTERN":
                    staff[i] = new InternStaff(name, sc.nextDouble());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid staff type");
            }
        }

        for (Staff s : staff) {
            double pay = s.calculatePay();
            System.out.printf(Locale.US, "%s: %.2f%n", s.name, pay);
            total += pay;
        }

        System.out.printf(Locale.US, "Total Payroll: %.2f%n", total);
        sc.close();
    }
}