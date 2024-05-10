/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project.data.structure;

/**
 *
 * @author ahmed
 */
public class ProjectDataStructure {


    public static void main(String[] args) {
        university universitySystem = new university();

        // Adding students
        universitySystem.addStudent("S001");
        universitySystem.addStudent("S002");

        // Enrolling courses
        universitySystem.enrollCourse("S001", "C001");
        universitySystem.enrollCourse("S001", "C002");
        universitySystem.enrollCourse("S002", "C001");

        // Listing courses for a student
        universitySystem.listCourses("S001");

        // Listing students for a course
        universitySystem.listStudents("C001");
} 
}
