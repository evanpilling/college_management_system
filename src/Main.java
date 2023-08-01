import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Collections;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        ArrayList<Person> array = new ArrayList<>(100);
        ArrayList<Student> studentArray = new ArrayList<>(100);
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");
        LocalDate currentDate = LocalDate.now();
        File file = new File("report.txt");
        PrintWriter pw = new PrintWriter(file);

        int initialInput = 0;
        int initialCH;
        String initialFullName;
        String initialID;
        String initialRank;
        String initialDepartment;
        String initialGPA;
        String initialStatus;
        String reportRequest;
        int gpaOrName = 0;
        int totalStudents = 0;
        int totalFaculty = 0;
        int totalStaff = 0;
        int i = 1;
        Boolean invalidInput = true;

        System.out.println("Welcome to my Personal Management Program\n");
        System.out.println("Choose one of the options:");
        while (true) {
            Boolean personFound = false;
            System.out.println("1- Enter the information a faculty\n" +
                    "2- Enter the information of a student\n" +
                    "3- Print tuition invoice for a student\n" +
                    "4- Print faculty information\n" +
                    "5- Enter the information of a staff member\n" +
                    "6- Print the information of a staff member\n" +
                    "7- Delete a person\n" +
                    "8- Exit Program");
            try {
                initialInput = scanner.nextInt();
                while (initialInput > 8 || initialInput < 1) {
                    System.out.println("Invalid input. Please try again: ");
                    initialInput = scanner.nextInt();
                }
            } catch (Exception e) {
                System.out.println("Please enter a number!");
                scanner.nextLine();
                continue;
            }
            switch (initialInput) {
                case 1:
                    Faculty faculty = new Faculty();
                    System.out.println("Enter the faculty info: ");
                    System.out.println("Name of the faculty: ");
                    scanner.nextLine();
                    initialFullName = scanner.nextLine();
                    faculty.setFullName(initialFullName);
                    System.out.println("ID: ");
                    initialID = scanner.nextLine();
                    while (!isValidIdFormat(initialID)) {
                        System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
                        System.out.println("ID: ");
                        initialID = scanner.nextLine();
                    }
                    for (Person p : array) {
                        while (p.getId().equals(initialID)) {
                            System.out.println("Duplicate ID detected. Please enter again: ");
                            initialID = scanner.nextLine();
                        }
                    }
                    faculty.setId(initialID);
                    System.out.println("Rank: ");
                    initialRank = scanner.nextLine().toLowerCase();
                    while (!initialRank.equals("professor") && !initialRank.equals("adjunct")) {
                        System.out.println(initialRank + " is invalid");
                        System.out.println("Rank: ");
                        initialRank = scanner.nextLine();
                    }
                    faculty.setRank(initialRank);
                    System.out.println("Department: ");
                    initialDepartment = scanner.nextLine().toLowerCase();
                    while (!initialDepartment.equals("mathematics") && !initialDepartment.equals("engineering")
                            && !initialDepartment.equals("english")) {
                        System.out.println(initialDepartment + "is invalid");
                        System.out.println("Department: ");
                        initialDepartment = scanner.nextLine();
                    }
                    faculty.setDepartment(initialDepartment);
                    array.add(faculty);
                    totalFaculty++;
                    System.out.println("Faculty added!");
                    break;
                case 2:
                    Student student = new Student();
                    System.out.println("Enter the student info: ");
                    System.out.println("Name of the student: ");
                    scanner.nextLine();
                    initialFullName = scanner.nextLine();
                    student.setFullName(initialFullName);
                    System.out.println("ID: ");
                    initialID = scanner.nextLine();
                    while (!isValidIdFormat(initialID)) {
                        System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
                        System.out.println("ID: ");
                        initialID = scanner.nextLine();
                    }
                    for (Person p : array) {
                        while (p.getId().equals(initialID)) {
                            System.out.println("Duplicate ID detected. Please enter again: ");
                            initialID = scanner.nextLine();
                        }
                    }
                    student.setId(initialID);
                    while (true) {
                        System.out.println("GPA: ");
                        initialGPA = scanner.nextLine();
                        try {
                            df.setParseBigDecimal(true);
                            Number gpaNumber = df.parse(initialGPA);
                            double gpaValue = gpaNumber.doubleValue();
                            if (gpaValue < 0 || gpaValue > 4) {
                                System.out.println("Please enter a valid GPA between 0 and 4.");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid numeric value for GPA.");
                        }
                    }
                    student.setGpa(initialGPA);
                    while (true) {
                        System.out.println("Credit hours: ");
                        try {
                            initialCH = scanner.nextInt();
                            if (initialCH <= 0) {
                                System.out.println("Please enter a positive value for credit hours.");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid integer for credit hours.");
                            scanner.nextLine();
                        }
                    }
                    student.setCreditHours(initialCH);
                    array.add(student);
                    totalStudents++;
                    System.out.println("Student added!");
                    break;
                case 3:
                    System.out.println("Enter the student's ID: ");
                    scanner.nextLine();
                    initialID = scanner.nextLine();
                    while (!isValidIdFormat(initialID)) {
                        System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
                        System.out.println("Enter the student's ID: ");
                        initialID = scanner.nextLine();
                    }
                    for (Person p : array) {
                        if (p instanceof Student && p.getId().equals(initialID)) {
                            p.print();
                        } else if (!array.contains(p.getId().equals(initialID)))
                            System.out.println("No student matched!");
                    }
                    break;
                case 4:
                    System.out.println("Enter the faculty's ID: ");
                    scanner.nextLine();
                    initialID = scanner.nextLine();
                    while (!isValidIdFormat(initialID)) {
                        System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
                        System.out.println("ID: ");
                        initialID = scanner.nextLine();
                    }
                    for (Person x : array) {
                        if (x instanceof Faculty && x.getId().equals(initialID)) {
                            System.out.println("===============");
                            x.print();
                            System.out.println("===============");
                            personFound = true;
                        }
                    }
                    if (!personFound)
                        System.out.println("No faculty matched!");
                    break;
                case 5:
                    Staff staff = new Staff();
                    System.out.println("Name of the staff member: ");
                    scanner.nextLine();
                    initialFullName = scanner.nextLine();
                    staff.setFullName(initialFullName);
                    System.out.println("Enter the ID: ");
                    initialID = scanner.nextLine();
                    while (!isValidIdFormat(initialID)) {
                        System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
                        System.out.println("ID: ");
                        initialID = scanner.nextLine();
                    }
                    for (Person p : array) {
                        while (p.getId().equals(initialID)) {
                            System.out.println("Duplicate ID detected. Please enter again: ");
                            initialID = scanner.nextLine();
                        }
                    }
                    staff.setId(initialID);
                    System.out.println("Department: ");
                    initialDepartment = scanner.nextLine().toLowerCase();
                    while (!initialDepartment.equals("mathematics") && !initialDepartment.equals("engineering")
                            && !initialDepartment.equals("english")) {
                        System.out.println(initialDepartment + "is invalid");
                        System.out.println("Department: ");
                        initialDepartment = scanner.nextLine();
                    }
                    staff.setDepartment(initialDepartment);
                    System.out.println("Status, enter P for part-time, or enter F for full-time: ");
                    initialStatus = scanner.nextLine().toLowerCase();
                    while (!initialStatus.equals("p") && !initialStatus.equals("f")) {
                        System.out.println("Invalid entry. Please try again: ");
                        initialStatus = scanner.nextLine().toLowerCase();
                    }

                    if (initialStatus.equals("p"))
                        staff.setStatus("Part Time");
                    else if (initialStatus.equals("f"))
                        staff.setStatus("Full Time");

                    array.add(staff);
                    totalStaff++;
                    System.out.println("Staff member added!");
                    break;
                case 6:
                    System.out.println("Enter the staff's ID: ");
                    scanner.nextLine();
                    initialID = scanner.nextLine();
                    while (!isValidIdFormat(initialID)) {
                        System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
                        System.out.println("ID: ");
                        initialID = scanner.nextLine();
                    }
                    for (Person x : array) {
                        if (x instanceof Staff && x.getId().equals(initialID)) {
                            System.out.println("===============");
                            x.print();
                            System.out.println("===============");
                            personFound = true;
                        }
                    }
                    if (!personFound)
                        System.out.println("No staff member matched!");
                    break;
                case 7:
                    System.out.println("Enter the ID of the person to delete: ");
                    scanner.nextLine();
                    initialID = scanner.nextLine();
                    while (!isValidIdFormat(initialID)) {
                        System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
                        System.out.println("ID: ");
                        initialID = scanner.nextLine();
                    }
                    for (Person p : array) {
                        if ((p.getId().equals(initialID))) {
                            array.remove(p);
                            personFound = true;
                            System.out.println("Person removed!");
                            break;
                        }
                    }
                    if (!personFound)
                        System.out.println("Sorry no such person exists.");
                    break;
                case 8:
                    System.out.println("Would you like to create a report?");
                    scanner.nextLine();
                    reportRequest = scanner.nextLine().toLowerCase();
                    while (!reportRequest.toLowerCase().equals("no") &&
                            !reportRequest.toLowerCase().equals("yes") &&
                            !reportRequest.toLowerCase().equals("n") &&
                            !reportRequest.toLowerCase().equals("y") &&
                            !reportRequest.toLowerCase().equals("nope") &&
                            !reportRequest.toLowerCase().equals("yeah")) {
                        System.out.println("Invalid input. Please enter yes or no: ");
                        reportRequest = scanner.nextLine();
                    }
                    if (reportRequest.equals("y") || reportRequest.equals("yes") || reportRequest.equals("yeah")) {
                        System.out.println(
                                "Would like to sort your students by descending gpa or name (1 for gpa, 2 for name): ");
                        pw.println("Report created on " + currentDate);
                        pw.print("\n");
                        try {
                            gpaOrName = scanner.nextInt();
                            while (gpaOrName != 1 && gpaOrName != 2) {
                                System.out.println("Invalid input. Please try again: ");
                                gpaOrName = scanner.nextInt();
                            }
                        } catch (Exception e) {
                            System.out.println("Please enter either 1 or 2!");
                            scanner.nextLine();
                            break;
                        }
                        if (gpaOrName == 1) {
                            if (totalFaculty > 0) {
                                pw.println("Faculty Members");
                                pw.println("---------------");
                                for (Person p : array) {
                                    if (p instanceof Faculty) {
                                        pw.print(i + ". ");
                                        pw.println(p.getFullName());
                                        pw.println("ID: " + p.getId());
                                        switch (((Faculty) p).getRank().toLowerCase()) {
                                            case "professor" -> pw.print("Professor, ");
                                            case "adjunct" -> pw.print("Adjunct, ");
                                        }
                                        switch (((Faculty) p).getDepartment().toLowerCase()) {
                                            case "mathematics" -> pw.print("Mathematics");
                                            case "engineering" -> pw.print("Engineering");
                                            case "english" -> pw.print("English");
                                        }
                                        pw.print("\n");
                                        i++;
                                    }
                                }
                                pw.print("\n");
                            }
                            if (totalStaff > 0) {
                                pw.println("Staff Members");
                                pw.println("---------------");
                                i = 1;
                                for (Person p : array) {
                                    if (p instanceof Staff) {
                                        pw.print(i + ". ");
                                        pw.println(p.getFullName());
                                        pw.println("ID: " + p.getId());
                                        switch (((Staff) p).getDepartment().toLowerCase()) {
                                            case "mathematics" -> pw.print("Mathematics, ");
                                            case "engineering" -> pw.print("Engineering, ");
                                            case "english" -> pw.print("English, ");
                                        }
                                        switch (((Staff) p).getStatus()) {
                                            case "Full Time" -> pw.println("Full Time");
                                            case "Part Time" -> pw.println("Part Time");
                                        }
                                        pw.print("\n");
                                        i++;
                                    }
                                }
                                pw.print("\n");
                            }
                            if (totalStudents > 0) {
                                for (Person p : array) {
                                    if (p instanceof Student)
                                        studentArray.add((Student) p);
                                }
                                Collections.sort(studentArray, new MyGpaComparator());
                                pw.println("Students");
                                pw.println("---------------");
                                i = 1;
                                for (Student s : studentArray) {
                                    pw.print(i + ". ");
                                    pw.println(s.getFullName());
                                    pw.println("ID: " + s.getId());
                                    pw.println("GPA: " + s.getGpa());
                                    pw.println("Credit hours: " + s.getCreditHours());
                                    pw.print("\n");
                                    i++;
                                }
                                pw.print("\n");
                            }
                        } else if (gpaOrName == 2) {
                            Collections.sort(array, new MyObjectComparator());
                            if (totalFaculty > 0) {
                                pw.println("Faculty Members");
                                pw.println("---------------");
                                for (Person p : array) {
                                    if (p instanceof Faculty) {
                                        pw.print(i + ". ");
                                        pw.println(p.getFullName());
                                        pw.println("ID: " + p.getId());
                                        switch (((Faculty) p).getRank().toLowerCase()) {
                                            case "professor" -> pw.print("Professor, ");
                                            case "adjunct" -> pw.print("Adjunct, ");
                                        }
                                        switch (((Faculty) p).getDepartment().toLowerCase()) {
                                            case "mathematics" -> pw.print("Mathematics");
                                            case "engineering" -> pw.print("Engineering");
                                            case "english" -> pw.print("English");
                                        }
                                        pw.print("\n");
                                        i++;
                                    }
                                }
                                pw.print("\n");
                            }
                            if (totalStaff > 0) {
                                pw.println("Staff Members");
                                pw.println("---------------");
                                i = 1;
                                for (Person p : array) {
                                    if (p instanceof Staff) {
                                        pw.print(i + ". ");
                                        pw.println(p.getFullName());
                                        pw.println("ID: " + p.getId());
                                        switch (((Staff) p).getDepartment().toLowerCase()) {
                                            case "mathematics" -> pw.print("Mathematics, ");
                                            case "engineering" -> pw.print("Engineering, ");
                                            case "english" -> pw.print("English, ");
                                        }
                                        switch (((Staff) p).getStatus()) {
                                            case "Full Time" -> pw.println("Full Time");
                                            case "Part Time" -> pw.println("Part Time");
                                        }
                                        pw.print("\n");
                                        i++;
                                    }
                                }
                                pw.print("\n");
                            }
                            if (totalStudents > 0) {
                                for (Person p : array) {
                                    if (p instanceof Student)
                                        studentArray.add((Student) p);
                                }
                                Collections.sort(studentArray, new MyGpaComparator());
                                pw.println("Students");
                                pw.println("---------------");
                                i = 1;
                                for (Student s : studentArray) {
                                    pw.print(i + ". ");
                                    pw.println(s.getFullName());
                                    pw.println("ID: " + s.getId());
                                    pw.println("GPA: " + s.getGpa());
                                    pw.println("Credit hours: " + s.getCreditHours());
                                    pw.print("\n");
                                    i++;
                                }
                                pw.print("\n");
                            }
                        }
                        System.out.println("Report created and saved on your hard drive!");
                        System.out.println("Goodbye!");
                        pw.close();
                        return;
                    } else if (reportRequest.equals("n") || reportRequest.equals("no")
                            || reportRequest.equals("nope")) {
                        System.out.println("Goodbye!");
                        return;
                    }
            }
        }
    }

    public static boolean isValidIdFormat(String id) {
        String pattern = "[A-Za-z]{2}\\d{4}";
        return id.matches(pattern);
    }
}

abstract class Person {
    String fullName;
    String id;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person() {
    }

    public Person(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }

    public abstract void print();
}

class Student extends Person {
    String gpa;
    int creditHours;
    DecimalFormat df = new DecimalFormat("0.00");

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public Student() {
    }

    public Student(String fullName, String id, String gpa, int creditHours) {
        super(fullName, id);
        this.gpa = gpa;
        this.creditHours = creditHours;
    }

    public void print() {
        System.out.println("Here is the tuition invoice for " + this.getFullName());
        System.out.println("===============");
        System.out.println(this.getFullName() + "\t" + this.getId());
        System.out.println("Credit hours: " + (this.getCreditHours() + "  ($236.45/credit hour)"));
        System.out.println("Fees: $52");
        System.out.print("Total payment (after discount): $");
        if (Double.parseDouble(this.getGpa()) >= 3.85) {
            System.out.print(df.format(0.75 * (this.getCreditHours() * 236.45 + 52)));
            System.out.println("\t($" + df.format(0.25 * (this.getCreditHours() * 236.45 + 52)) + " discount applied)");
        } else {
            Double tempCH = (this.getCreditHours() * 236.45);
            System.out.print(df.format(tempCH));
            System.out.println("\t($0 discount applied)");
        }
        System.out.println("===============");
    }
}

abstract class Employee extends Person {
    String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Employee() {
    }

    public Employee(String fullName, String id, String department) {
        super(fullName, id);
        this.department = department;
    }
}

class Faculty extends Employee {
    String rank;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Faculty() {
    }

    public Faculty(String fullName, String id, String department, String rank) {
        super(fullName, id, department);
        this.rank = rank;
    }

    public void print() {
        System.out.println(this.getFullName() + "\t" + this.getId());
        switch (this.getDepartment().toLowerCase()) {
            case "mathematics" -> System.out.print("Mathematics ");
            case "engineering" -> System.out.print("Engineering ");
            case "english" -> System.out.print("English ");
        }
        System.out.print("Department, ");
        switch (this.getRank().toLowerCase()) {
            case "professor" -> System.out.println("Professor");
            case "adjunct" -> System.out.println("Adjunct");
        }
    }
}

class Staff extends Employee {
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Staff() {
    }

    public Staff(String fullName, String id, String department, String status) {
        super(fullName, id, department);
        this.status = status;
    }

    public void print() {
        System.out.println(this.getFullName() + "\t" + this.getId());
        switch (this.getDepartment().toLowerCase()) {
            case "mathematics" -> System.out.print("Mathematics ");
            case "engineering" -> System.out.print("Engineering ");
            case "english" -> System.out.print("English ");
        }
        System.out.print("Department, ");
        switch (this.getStatus()) {
            case "Full Time" -> System.out.println("Full Time");
            case "Part Time" -> System.out.println("Part Time");
        }
    }
}

class MyObjectComparator implements Comparator<Person> {
    @Override
    public int compare(Person obj1, Person obj2) {
        return obj2.getFullName().compareTo(obj1.getFullName());
    }
}

class MyGpaComparator implements Comparator<Person> {
    @Override
    public int compare(Person obj1, Person obj2) {
        return ((Student) obj2).getGpa().compareTo(((Student) obj1).getGpa());
    }
}