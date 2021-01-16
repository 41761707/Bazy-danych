package com.zalewskiwojtczak;

import java.util.Date;

public class TeacherViewStudents {
    private String legitymacja;
    private String studentName;
    private String studentSurname;
    private String className;

    TeacherViewStudents(String legitymacja, String studentName, String studentSurname, String className)
    {
        this.legitymacja=legitymacja;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.className=className;
    }

    public String getLegitymacja()
    {
    	return legitymacja;
    }
    public String getStudentName(){
        return studentName;
    }
    public String getStudentSurname(){
        return studentSurname;
    }
    public String getClassName()
    {
    	return className;
    }
}