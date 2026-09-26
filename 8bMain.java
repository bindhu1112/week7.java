import java.util.*;
import java.time.LocalDate;

abstract class LibraryItem {
    String title;

    LibraryItem(String title) {
        this.title = title;
    }

    abstract int getLoanPeriod();

    void display() {
        LocalDate currentDate = LocalDate.of(2023, 10, 26);
        LocalDate dueDate = currentDate.plusDays(getLoanPeriod());
        System.out.println(title + ": " + dueDate);
    }
}

class Book extends LibraryItem {
    Book(String title) {
        super(title);
    }

    int getLoanPeriod() {
        return 14;
    }
}

class DVD extends LibraryItem {
    DVD(String title) {
        super(title);
    }

    int getLoanPeriod() {
        return 7;
    }
}

class Magazine extends LibraryItem {
    Magazine(String title) {
        super(title);
    }

    int getLoanPeriod() {
        return 3;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        LibraryItem[] items = new LibraryItem[n];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();

            String[] parts = line.split(" ", 2);
            String type = parts[0];
            String title = parts[1].replace("\"", "");

            if (type.equals("BOOK")) {
                items[i] = new Book(title);
            } else if (type.equals("DVD")) {
                items[i] = new DVD(title);
            } else {
                items[i] = new Magazine(title);
            }
        }

        for (LibraryItem item : items) {
            item.display();
        }
    }
}