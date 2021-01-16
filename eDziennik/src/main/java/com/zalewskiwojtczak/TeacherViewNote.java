package com.zalewskiwojtczak;

import java.util.Date;

public class TeacherViewNote {
    private int points;
    private String legitymacja;
    private String studentName;
    private String studentSurname;
    private String className;
    private String comment;

    TeacherViewNote(int points, String legitymacja, String studentName, String studentSurname, String className, String comment) {
        this.points = points;
        this.legitymacja=legitymacja;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.className=className;
        this.comment = comment;
    }

    public int getPoints(){
        return points;
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

    public String getComment(){
        return comment;
    }
}
