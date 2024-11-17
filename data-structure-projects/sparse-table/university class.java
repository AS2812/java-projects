/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.data.structure;

/**
 *
 * @author ahmed
 */
public class university 
{
private student headStudent;
public university()
{
headStudent = null;
}   
public void addStudent(String StudentID)
{
    student newStudent = new student(StudentID);
    if(headStudent == null)
    {
        headStudent = newStudent;
    }
    else
    {
        student current = headStudent; //iterate through the list through a head node(head student) 
      while (current.NextStudent != null)
        {
             current = current.NextStudent;
        }
     current.NextStudent = newStudent;
    }
}
public void enrollCourse(String StudentID, String CourseCode)
{
student current = headStudent;
    while(current!=null)
    {
    if(current.StudentID.equals(StudentID))
    {//checks in the students' list for that ID
    course newCourse = new course(CourseCode);     
          if (current.courses == null)
          {   //uses the same "head" node of current as a reference
                                           //if there are no courses in the student's courses' linkedlist
            current.courses = newCourse;   // registers the course 
          }
          else
          {
            course currentCourse = current.courses;  //if there are courses, iterates and searches for it
             while (currentCourse.NextCourse != null) 
             {
             currentCourse = currentCourse.NextCourse;
             }
             currentCourse.NextCourse = newCourse;
                }
                return;
            }
            current = current.NextStudent; //next in the while loop
        }
        System.out.println("Student not found.");
    }


 public void listCourses(String studentID)
 {
        student current = headStudent;  //reference node
        while (current != null) {
            if (current.StudentID.equals(studentID)) { //checks if student exists
                System.out.println("Courses " + studentID + ":");
                course currentCourse = current.courses; //creates a reference
                while (currentCourse != null) { //prints the courses
                    System.out.println(currentCourse.CourseCode);
                    currentCourse = currentCourse.NextCourse;
                }
                return;
            }
            current = current.NextStudent;  //updates
        }
        System.out.println("Student not found.");
    }
 
 
  public void listStudents(String CourseCode)
  {
        System.out.println("Students enrolled in course " + CourseCode + ":");
        student current = headStudent;  //create a head reference
        while (current != null) {
            course currentCourse = current.courses; // creates a reference for the course's linked list
            while (currentCourse != null) {
                if (currentCourse.CourseCode.equals(CourseCode)) {
                    System.out.println(current.StudentID);
                    break;
                }
                currentCourse = currentCourse.NextCourse;
            }
            current = current.NextStudent;
}
}
}
