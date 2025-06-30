📘 Student Report Card Generator (Java)

A console-based Java application to generate report cards for multiple students. It uses object-oriented principles and Java Collections to manage dynamic inputs, validate entries, and produce clean report cards with grade and percentage calculations.

---------------------------------------------------------------------------------------------------
🧰 Tech Stack
Language: Java

Collections Used: List, Set, LinkedHashMap

Concepts: OOP, Input Validation, Conditional Logic, Console I/O
---------------------------------------------------------------------------------------------------

🚀 Features

1. Collects student details: name, roll number, subject marks.

2. Validates:

Name must not contain digits.

Roll numbers must be unique.

Marks must be between 0 and 100.

Supports multiple students and subjects.

3. Calculates:

Total marks

Percentage

Grade based on standard criteria

4. Displays a formatted report card based on roll number input.

5. Allows multiple report generations in a single run.
--------------------------------------------------------------------------------------------------------

📊 Grade Criteria
Percentage	Grade
90+	AA
80–89	A+
70–79	A
60–69	B+
50–59	B
40–49	Pass
Below 40	Fail
--------------------------------------------------------------------------------------------------------------

Follow the console prompts to enter:

Number of students

Number of subjects

Subject names

Student name, roll, and marks

------------------------------------------------------------------------------------------------------------------
✅ Sample Validation Highlights

🔐 Student name: must not contain digits (e.g., John123 → ❌ Invalid)

🔐 Marks: must be integers between 0–100

🔐 Roll number: must be unique

🧮 Report card displayed only by roll number
---------------------------------------------------------------------------------------------------------------------

📝 Example Console Output

Enter the number of students: 2
Enter the number of subjects: 3
Enter the subject 1 name: Math
Enter the subject 2 name: Science
Enter the subject 3 name: English

Enter name of Student 1: Alice
Enter roll of Student 1: 101
Enter marks in Math: 78
...

Generate Report Card for Roll Number: 101

================== GENERATING REPORT CARD =====================
Student Name: Alice
Roll Number: 101
Math : 78
Science : 82
English : 74
===============================================================
Total Marks: 234
Percentage Obtained: 78.00%
Grade: A
===============================================================


----------------------------------------------------------------------------------------------------------

