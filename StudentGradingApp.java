import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentGradingApp {

    static class Student {
        private String name;
        private List<Double> grades;

        public Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void addGrade(double grade) {
            grades.add(grade);
        }

        public List<Double> getGrades() {
            return grades;
        }

        public double getAverageGrade() {
            if (grades.isEmpty()) return 0.0;
            double sum = 0;
            for (double g : grades) {
                sum += g;
            }
            return sum / grades.size();
        }
    }

    private Map<String, Student> students;
    private Scanner scanner;

    public StudentGradingApp() {
        students = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Choose an option: ");
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGradeToStudent();
                    break;
                case 3:
                    displayStudentGrades();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    displayClassAverage();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting the Student Grading App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, please select again.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("=== Student Grading App ===");
        System.out.println("1. Add new student");
        System.out.println("2. Add grade to student");
        System.out.println("3. Display grades for a student");
        System.out.println("4. Display all students");
        System.out.println("5. Display class average");
        System.out.println("6. Exit");
    }

    private void addStudent() {
        String name = getStringInput("Enter student name: ").trim();
        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty.");
            return;
        }
        if (students.containsKey(name)) {
            System.out.println("Student already exists.");
            return;
        }
        students.put(name, new Student(name));
        System.out.println("Student \"" + name + "\" added successfully.");
    }

    private void addGradeToStudent() {
        String name = getStringInput("Enter student name to add grade: ").trim();
        Student student = students.get(name);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        double grade = getDoubleInput("Enter grade (0 - 100): ");
        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade. Must be between 0 and 100.");
            return;
        }
        student.addGrade(grade);
        System.out.println("Grade added successfully to " + name + ".");
    }

    private void displayStudentGrades() {
        String name = getStringInput("Enter student name to display grades: ").trim();
        Student student = students.get(name);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        List<Double> grades = student.getGrades();
        if (grades.isEmpty()) {
            System.out.println(name + " has no grades recorded.");
            return;
        }
        System.out.println("Grades for " + name + ":");
        for (int i = 0; i < grades.size(); i++) {
            System.out.printf("  Grade %d: %.2f%n", i + 1, grades.get(i));
        }
        System.out.printf("Average grade: %.2f%n", student.getAverageGrade());
    }

    private void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }
        System.out.println("All students:");
        for (String name : students.keySet()) {
            System.out.println(" - " + name);
        }
    }

    private void displayClassAverage() {
        if (students.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }
        double totalSum = 0;
        int totalGradesCount = 0;
        for (Student s : students.values()) {
            for (double g : s.getGrades()) {
                totalSum += g;
                totalGradesCount++;
            }
        }
        if (totalGradesCount == 0) {
            System.out.println("No grades recorded yet.");
            return;
        }
        double classAverage = totalSum / totalGradesCount;
        System.out.printf("Class average grade: %.2f%n", classAverage);
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        StudentGradingApp app = new StudentGradingApp();
        app.run();
    }
}

