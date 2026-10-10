import java.util.*;

abstract class Plot {
    String owner;
    String shape;

    Plot(String owner, String shape) {
        this.owner = owner;
        this.shape = shape;
    }

    abstract double calculateArea();
}

class CirclePlot extends Plot {
    double radius;

    CirclePlot(String owner, double radius) {
        super(owner, "CIRCLE");
        this.radius = radius;
    }

    double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class RectanglePlot extends Plot {
    double length, width;

    RectanglePlot(String owner, double length, double width) {
        super(owner, "RECTANGLE");
        this.length = length;
        this.width = width;
    }

    double calculateArea() {
        return length * width;
    }
}

class TrianglePlot extends Plot {
    double base, height;

    TrianglePlot(String owner, double base, double height) {
        super(owner, "TRIANGLE");
        this.base = base;
        this.height = height;
    }

    double calculateArea() {
        return 0.5 * base * height;
    }
}

public class GardenPlotAreaReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Plot[] plots = new Plot[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String shape = sc.next().toUpperCase();
            String owner = sc.next();

            switch (shape) {
                case "CIRCLE":
                    plots[i] = new CirclePlot(owner, sc.nextDouble());
                    break;
                case "RECTANGLE":
                    plots[i] = new RectanglePlot(
                        owner, sc.nextDouble(), sc.nextDouble());
                    break;
                case "TRIANGLE":
                    plots[i] = new TrianglePlot(
                        owner, sc.nextDouble(), sc.nextDouble());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid shape");
            }
        }

        for (Plot p : plots) {
            double area = p.calculateArea();
            System.out.printf(Locale.US, "%s (%s): %.2f%n",
                p.owner, p.shape, area);
            total += area;
        }

        System.out.printf(Locale.US, "Total Area: %.2f%n", total);
        sc.close();
    }
}