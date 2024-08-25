import java.util.*;

class Student {
    private String name;
    private Map<String, List<Double>> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addGrade(String subject, double grade) {
        grades.computeIfAbsent(subject, k -> new ArrayList<>()).add(grade);
    }

    public double calculateAverage() {
        double total = 0;
        int count = 0;
        for (List<Double> gradeList : grades.values()) {
            for (double grade : gradeList) {
                total += grade;
                count++;
            }
        }
        return count == 0 ? 0 : total / count;
    }

    public char calculateLetterGrade(double average) {
        if (average >= 90) return 'A';
        else if (average >= 80) return 'B';
        else if (average >= 70) return 'C';
        else if (average >= 60) return 'D';
        else return 'F';
    }

    public double calculateGPA(double average) {
        if (average >= 90) return 4.0;
        else if (average >= 80) return 3.0;
        else if (average >= 70) return 2.0;
        else if (average >= 60) return 1.0;
        else return 0.0;
    }

    public void displayStudentInfo() {
        double average = calculateAverage();
        char letterGrade = calculateLetterGrade(average);
        double gpa = calculateGPA(average);

        System.out.println("Student Name: " + name);
        System.out.println("Grades by Subject: " + grades);
        System.out.println("Overall Average Grade: " + average);
        System.out.println("Letter Grade: " + letterGrade);
        System.out.println("GPA: " + gpa);
    }
}

public class GradeManager {
    private List<Student> students;

    public GradeManager() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayAllStudentsInfo() {
        for (Student student : students) {
            student.displayStudentInfo();
            System.out.println("----------------------------");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeManager manager = new GradeManager();

        while (true) {
            System.out.print("Enter student's name (or type 'exit' to finish): ");
            String studentName = scanner.nextLine();
            if (studentName.equalsIgnoreCase("exit")) {
                break;
            }

            Student student = new Student(studentName);

            while (true) {
                System.out.print("Enter subject name (or type 'done' to finish): ");
                String subject = scanner.nextLine();
                if (subject.equalsIgnoreCase("done")) {
                    break;
                }

                System.out.print("Enter grade for " + subject + ": ");
                double grade = scanner.nextDouble();
                scanner.nextLine();  

                student.addGrade(subject, grade);
            }

            manager.addStudent(student);
        }

        manager.displayAllStudentsInfo();
        scanner.close();
    }
}
