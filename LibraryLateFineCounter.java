import java.util.*;

abstract class LibraryItem {
    String title;
    int daysLate;

    LibraryItem(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    abstract double calculateFine();
}

class Book extends LibraryItem {
    Book(String title, int daysLate) {
        super(title, daysLate);
    }

    double calculateFine() {
        return daysLate * 2.0;
    }
}

class DVD extends LibraryItem {
    DVD(String title, int daysLate) {
        super(title, daysLate);
    }

    double calculateFine() {
        return Math.min(daysLate * 5.0, 50.0);
    }
}

class Magazine extends LibraryItem {
    Magazine(String title, int daysLate) {
        super(title, daysLate);
    }

    double calculateFine() {
        return daysLate * 1.0;
    }
}

public class LibraryLateFineCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LibraryItem[] items = new LibraryItem[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            String title = sc.next();
            int days = sc.nextInt();

            switch (type) {
                case "BOOK":
                    items[i] = new Book(title, days);
                    break;
                case "DVD":
                    items[i] = new DVD(title, days);
                    break;
                case "MAGAZINE":
                    items[i] = new Magazine(title, days);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid item type");
            }
        }

        for (LibraryItem item : items) {
            double fine = item.calculateFine();
            System.out.printf(Locale.US, "%s: %.2f%n",
                item.title, fine);
            total += fine;
        }

        System.out.printf(Locale.US, "Total Fines: %.2f%n", total);
        sc.close();
    }
}