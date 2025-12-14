package org.anisa;

public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;
    private static int nextId = 1;

    public boolean registerCourse (Course course) {
        if (couse == null || registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.add(course);

        course.registeredStudent(this);

        return true;
    }
}
