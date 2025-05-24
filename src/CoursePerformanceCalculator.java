import java.util.*;
import java.io.*;

public class CoursePerformanceCalculator {

    static class Assessment {
        String name;
        double weightage;
        List<Double> yourMarks = new ArrayList<>();
        List<Double> avgMarks = new ArrayList<>();
        List<Double> totalMarks = new ArrayList<>();

        Assessment(String name, double weightage) {
            this.name = name;
            this.weightage = weightage;
        }

        void addAllMarks(List<Double> yours, List<Double> avgs, List<Double> totals) {
            yourMarks.addAll(yours);
            avgMarks.addAll(avgs);
            totalMarks.addAll(totals);
        }

        double getWeightedAchievedPercentage() {
            double total = 0;
            for (int i = 0; i < yourMarks.size(); i++) {
                total += yourMarks.get(i) / totalMarks.get(i);
            }
            return (total / yourMarks.size()) * weightage;
        }

        double getWeightedAveragePercentage() {
            double total = 0;
            for (int i = 0; i < avgMarks.size(); i++) {
                total += avgMarks.get(i) / totalMarks.get(i);
            }
            return (total / avgMarks.size()) * weightage;
        }

        double getDifference() {
            return getWeightedAchievedPercentage() - getWeightedAveragePercentage();
        }
    }

    static class Course {
        String name;
        List<Assessment> assessments = new ArrayList<>();

        Course(String name) {
            this.name = name;
        }

        void addAssessment(Assessment a) {
            assessments.add(a);
        }

        String generateReport() {
            StringBuilder sb = new StringBuilder();
            sb.append("Course: ").append(name).append("\n");
            sb.append(String.format("%-20s %-25s %-25s %-25s\n", "Assessment", "Weighted Achieved (%)", "Weighted Average (%)", "Difference (%)"));
            sb.append("--------------------------------------------------------------------------------------------------------------\n");

            double totalAchieved = 0, totalAverage = 0, totalDifference = 0, totalWeightage = 0;
            for (Assessment a : assessments) {
                double achieved = a.getWeightedAchievedPercentage();
                double average = a.getWeightedAveragePercentage();
                double diff = a.getDifference();

                sb.append(String.format("%-20s %-25.2f %-25.2f %-25.2f\n", a.name, achieved, average, diff));

                totalAchieved += achieved;
                totalAverage += average;
                totalDifference += diff;
                totalWeightage += a.weightage;
            }

            sb.append("--------------------------------------------------------------------------------------------------------------\n");
            sb.append(String.format("%-20s %-25.2f %-25.2f %-25.2f\n", "TOTAL", totalAchieved, totalAverage, totalDifference));
            sb.append(String.format("Total Weightage Covered: %.2f%%\n\n", totalWeightage));

            return sb.toString();
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();
        System.out.println("Course Performance Calculator\n");

        while (true) {
            System.out.print("Enter course name (or 'done' to finish all courses): ");
            String courseName = scanner.nextLine();
            if (courseName.equalsIgnoreCase("done")) break;

            Course course = new Course(courseName);

            while (true) {
                System.out.print("  Assessment name (or 'done' to finish this course): ");
                String assessmentName = scanner.nextLine();
                if (assessmentName.equalsIgnoreCase("done")) break;

                System.out.print("  Total weightage (%): ");
                double weightage = Double.parseDouble(scanner.nextLine());

                System.out.print("  Number of tests in this assessment: ");
                int numTests = Integer.parseInt(scanner.nextLine());

                System.out.print("  Your marks (space-separated): ");
                List<Double> yourMarks = parseLine(scanner.nextLine(), numTests);

                System.out.print("  Average marks (space-separated): ");
                List<Double> avgMarks = parseLine(scanner.nextLine(), numTests);

                System.out.print("  Total marks (space-separated): ");
                List<Double> totalMarks = parseLine(scanner.nextLine(), numTests);

                Assessment a = new Assessment(assessmentName, weightage);
                a.addAllMarks(yourMarks, avgMarks, totalMarks);
                course.addAssessment(a);

                System.out.println();
            }

            courses.add(course);
            System.out.println("Finished course: " + courseName + "\n");
        }

        // Count existing reports in the file
        int reportCount = 0;
        File file = new File("report.txt");
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (line.startsWith("Report ")) {
                        reportCount++;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading report file: " + e.getMessage());
            }
        }

        // Append new report to file
        try (PrintWriter writer = new PrintWriter(new FileWriter("report.txt", true))) {
            reportCount++;
            writer.println("==============================================================================================================");
            writer.println("Report " + reportCount);
            writer.println("=========");
            for (Course course : courses) {
                String report = course.generateReport();
                System.out.println(report);        // Print to console
                writer.println(report);            // Append to file
            }
            writer.println();  // Extra newline
            System.out.println("Reports appended to 'report.txt' as Report " + reportCount);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    private static List<Double> parseLine(String line, int expectedCount) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length != expectedCount) {
            throw new IllegalArgumentException("Expected " + expectedCount + " numbers, but got " + parts.length);
        }
        List<Double> numbers = new ArrayList<>();
        for (String part : parts) {
            numbers.add(Double.parseDouble(part));
        }
        return numbers;
    }
}
