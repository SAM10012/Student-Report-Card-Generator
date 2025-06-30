import java.util.*;

/* Project Title: Student Report Card Generator (Java) */

/* • Designed a console-based Java application to generate student report cards using object-oriented principles.
   • Used Collections (List, Set, LinkedHashMap) to store and process dynamic user inputs and validate entries.
   • Implemented logic for grade calculation, input sanitization, and real-time report generation based on roll numbers.*/

// Check input marks valid or not
public class StudentReportCardGenerator {

    public static int checkMarks(int num) {
        if (num < 0 || num > 100) {
            System.out.println("Marks should be between 0 and 100 only.");
            return -1;
        }

        return num;
    }


    // Calculate Grade
    public static void calculateGrade(int total, int num_of_subj) {

        String grade = "";
        //double percentage = ((double)total)/(num_of_subj);// If total = 380 and 5 subjects => 380/500 => 0.76 but this is integer division so 0.  Multiplying and dividing by 100 is redundant.
        double percentage = ((double) total / (num_of_subj * 100)) * 100;
        if (percentage >= 90) {
            grade = "AA";
        } else if (percentage >= 80) {
            grade = "A+";
        } else if (percentage >= 70) {
            grade = "A";
        } else if (percentage >= 60) {
            grade = "B+";
        } else if (percentage >= 50) {
            grade = "B";
        } else if (percentage >= 40) {
            grade = "Pass";
        } else {
            grade = "Fail";
        }

        System.out.printf("\nPercentage Obtained: %.2f%%", percentage);
        System.out.print("\nGrade: " + grade);

    }

    // Print Student Details in Student Report Card
    public static void showReportCard(Student student_name, int subj_num) {
        System.out.print("\n\n================== GENERATING REPORT CARD =====================");
        System.out.print("\n\nStudent Name: " + student_name.getName());
        System.out.print("\nRoll Number: " + student_name.getRoll());

        int total = 0;
        int passed = 1;


        for (Map.Entry<String, Integer> e : student_name.getMarks().entrySet()) {

            System.out.print("\n" + e.getKey() + " : " + e.getValue());
            if (e.getValue() < 40) {
                passed = 0;

            }
            total = total + e.getValue();


        }

        System.out.println("\n===============================================================");
        System.out.print("Total Marks: " + total);

        if (passed == 0) {
            System.out.println("\nComments: " + student_name.getName() + " has scored less than 40 in at least one subject.");
            System.out.println("Grade : Fail");
        } else {
            // Calculate grade wrt total marks obtained
            calculateGrade(total, subj_num);
        }

        System.out.println("\n===============================================================");

    }

    public static class Student {
        String name;
        int roll;
        LinkedHashMap<String, Integer> marks = new LinkedHashMap<>();


        // Constructor
        public Student(String name, int roll, LinkedHashMap<String, Integer> marks) {
            this.name = name;
            this.roll = roll;
            this.marks = marks;

        }

        // Getters and Setters
        public LinkedHashMap<String, Integer> getMarks() {
            return marks;
        }

        public void setMarks(LinkedHashMap<String, Integer> marks) {
            this.marks = marks;
        }

        public int getRoll() {
            return roll;
        }

        public void setRoll(int roll) {
            this.roll = roll;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int student_num = sc.nextInt();
        sc.nextLine();

        System.out.print("\nEnter the number of subjects: ");
        int subject_num = sc.nextInt();
        sc.nextLine();

        // List of Subjects - Same for all students
        List<String> subjects = new ArrayList<>();
        for (int i = 0; i < subject_num; i++) {
            System.out.print("\nEnter the subject " + (i + 1) + " name: ");
            subjects.add(sc.nextLine());
        }

        // List of Students
        List<Student> list_of_students = new ArrayList<>();

        // Keeping a Set of entered Roll Numbers so that Rolls are not duplicated
        Set<Integer> roll_set = new HashSet<>();


        // User provides each Student name, roll and marks obtained in each subject
        for (int i = 0; i < student_num; i++) {

            String s_name = "";

            // Check Student Name is not Integer
            while (true) {
                System.out.print("\nEnter name of Student " + (i + 1) + ": ");
                s_name = sc.nextLine();
                if (s_name.matches(".*\\d.*")) {
                    System.out.println("Student name should not contain digits. Please retry.");
                    continue;
                } else {
                    break;
                }

            }
            int s_roll = -1;
            // Check if Roll Number already exists
            while (true) {
                System.out.print("\nEnter roll of Student " + (i + 1) + ": ");
                s_roll = Integer.parseInt(sc.nextLine());

                if (roll_set.contains(s_roll)) {
                    System.out.println("This Roll Number is already assigned. Roll Numbers must be unique. Please retry.");
                    continue;
                } else {
                    roll_set.add(s_roll);
                    break;
                }
            }


            // HashMap for Subject and Marks obtained in that subject
            LinkedHashMap<String, Integer> s_marks = new LinkedHashMap<>();
            for (int j = 0; j < subject_num; ) {

                int marks = -1;
                System.out.print("\nEnter marks of Student " + (i + 1) + " in " + subjects.get(j) + " (out of 100) : ");
                // Validating marks (0 - 100)

                // Check if marks is integer or not
                if (!sc.hasNextInt()) {
                    System.out.println("Please enter integer type only. Try again.");
                    sc.nextLine();
                    continue;
                }
                marks = checkMarks(sc.nextInt());
                sc.nextLine();

                // If valid marks, insert in HashMap
                if (marks != -1) {
                    s_marks.put(subjects.get(j), marks);
                    j++;
                }

                // If invalid marks, loop keeps repeating for same subject.

                /*else
                {
                    if(j!=0)
                    j--;
                    else
                        j=0;

                }*/ // => This way j will not increase and remain same only.


            }

            // Creating Student object
            Student student = new Student(s_name, s_roll, s_marks);

            // Adding Student object to ArrayList of Student objects
            list_of_students.add(student);
        }

        // Generating the Student report Card


        while (true) {

            // Uses Roll No. to generate Report Card (Duplicate Name issue removed)
            int stud_roll = 0;
            System.out.print("\n\nGenerate Report Card for Roll Number: ");

            // Checks if user provided Roll Number is integer or not
            if (!sc.hasNextInt()) {
                System.out.println("Please enter valid integer entry. Try again.");
                sc.nextLine();
                continue;
            }

            stud_roll = sc.nextInt();
            sc.nextLine();


            // Check if that Roll No. exists or not
            int index = -1;
            int found = 0;
            for (int i = 0; i < student_num; i++) {
                if (list_of_students.get(i).getRoll() == stud_roll) {
                    index = i;
                    found = 1;
                }
            }

            // Student Roll not found in the ArrayList of Student objects
            if (found == 0) {
                System.out.println("No such Roll Number exists! Try again");
                continue;
            }

            // If valid Roll No., show the report card, sending no. of subjects to calculate percentage of the student
            showReportCard(list_of_students.get(index), subject_num);

            System.out.println("Do you want to generate another Report Card? (Y/N): ");
            String cont = sc.nextLine().toLowerCase();
            if (cont.equalsIgnoreCase("Y")) {
                continue;
            } else {
                System.out.println("Terminating the process");
                System.out.println("\n ===== Thank you! =====");
                break;
            }
            /*else if (cont.equalsIgnoreCase("Y")) {
                continue;
            } else {
                System.out.println("Please select Y or N to continue.");

            }*/


        }


    }
}
