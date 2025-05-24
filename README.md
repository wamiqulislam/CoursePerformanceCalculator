A calculator that shows how well you're doing compared to average in your Course. You input your marks, avg marks, and total marks of each test of an assessment  along with its weightage and you get a report with you're weightage total percentage, weighted avg percentage of the class and the difference btw them.

For reference, you get a B if difference is >0%, B+ if difference is >5%-7%, A if difference is >12%-15% etc. 

(NOTE: These are not definite ranges, just something i got from my experience)


## 🖥️ Example Input

```
Enter course name (or 'done' to finish all courses): Database Systems
  Assessment name (or 'done' to finish this course): Assignments
  Total weightage (%): 5
  Number of tests in this assessment: 2
  Your marks (space-separated): 9 10
  Average marks (space-separated): 8 9
  Total marks (space-separated): 10 10
  Assessment name (or 'done' to finish this course): Mid Semester Exam
  Total weightage (%): 30
  Number of tests in this assessment: 1
  Your marks (space-separated): 27
  Average marks (space-separated): 25
  Total marks (space-separated): 30
  Assessment name (or 'done' to finish this course): End Semester Exam
  Total weightage (%): 65
  Number of tests in this assessment: 1
  Your marks (space-separated): 85
  Average marks (space-separated): 78
  Total marks (space-separated): 100
  Assessment name (or 'done' to finish this course): done
Finished course: Database Systems
Enter course name (or 'done' to finish all courses): done
```

## 📊 Example Output

```
==============================================================================================================
Report 1
=========
Course: Object Oriented Programming
Assessment           Weighted Achieved (%)     Weighted Average (%)      Difference (%)           
--------------------------------------------------------------------------------------------------------------
Assignments          9.17                      8.25                      0.92                     
Quizzes              12.75                     11.44                     1.31                     
Mid Semester Exam    21.00                     19.00                     2.00                     
End Semester Exam    40.00                     37.50                     2.50                     
--------------------------------------------------------------------------------------------------------------
TOTAL                82.92                     76.19                     6.73                     
Total Weightage Covered: 100.00%
Course: Database Systems
Assessment           Weighted Achieved (%)     Weighted Average (%)      Difference (%)           
--------------------------------------------------------------------------------------------------------------
Assignments          4.75                      4.25                      0.50                     
Mid Semester Exam    27.00                     25.00                     2.00                     
End Semester Exam    55.25                     50.70                     4.55                     
--------------------------------------------------------------------------------------------------------------
TOTAL                87.00                     79.95                     7.05                     
Total Weightage Covered: 100.00%
```

You can calculate this for mulitple courses and their assessments, and it provides a report in the cli as well as a file report.txt that contains all the reports generated including previous ones.
