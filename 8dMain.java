import java.util.*;

abstract class Question {
    String correctAnswer;
    String studentAnswer;
    double points;

    Question(String correctAnswer, String studentAnswer, double points) {
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }

    abstract double calculateScore();

    abstract String getType();
}

class MCQ extends Question {
    MCQ(String correctAnswer, String studentAnswer, double points) {
        super(correctAnswer, studentAnswer, points);
    }

    double calculateScore() {
        return studentAnswer.equalsIgnoreCase(correctAnswer) ? points : 0;
    }

    String getType() {
        return "MCQ";
    }
}

class TF extends Question {
    TF(String correctAnswer, String studentAnswer, double points) {
        super(correctAnswer, studentAnswer, points);
    }

    double calculateScore() {
        return studentAnswer.equalsIgnoreCase(correctAnswer) ? points : 0;
    }

    String getType() {
        return "TF";
    }
}

class Essay extends Question {
    Essay(String correctAnswer, String studentAnswer, double points) {
        super(correctAnswer, studentAnswer, points);
    }

    double calculateScore() {
        String answer = studentAnswer.toLowerCase();
        String[] keywords = correctAnswer.split(",");

        int count = 0;

        for (String keyword : keywords) {
            if (answer.contains(keyword.trim().toLowerCase())) {
                count++;
            }
        }

        if (count >= 2) {
            return points * 0.75;
        } else if (count == 1) {
            return points * 0.50;
        }

        return 0;
    }

    String getType() {
        return "ESSAY";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        Question[] questions = new Question[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();

            String[] parts = line.split("\"");

            String type = parts[0].trim().split(" ")[0];

            String correctAnswer = parts[3].trim();
            String studentAnswer = parts[5].trim();
            double points = Double.parseDouble(parts[6].trim());

            if (type.equals("MCQ")) {
                questions[i] = new MCQ(correctAnswer, studentAnswer, points);
            } else if (type.equals("TF")) {
                questions[i] = new TF(correctAnswer, studentAnswer, points);
            } else {
                questions[i] = new Essay(correctAnswer, studentAnswer, points);
            }
        }

        for (Question q : questions) {
            double score = q.calculateScore();
            System.out.printf("%s: %.2f%n", q.getType(), score);
            total += score;
        }

        System.out.printf("Total Score: %.2f%n", total);
    }
}