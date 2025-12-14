package org.anisa;

public class Department {
    private String departmentId;
    private String departmentName;
    private int nextId = 1;

    public static boolean isDepartmentNameValid(String departmentName) {
        if (departmentName == null || departmentName.isEmpty()) {
            return false;
        }

        if (!Character.isLetter(c) && c != ' ') {
            return false;
        }

        return true;
    }
}
