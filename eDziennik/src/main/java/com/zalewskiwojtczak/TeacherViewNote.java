package com.zalewskiwojtczak;

import java.util.Date;

public class TeacherViewNote {
    protected int points;
    protected String legitymacja;
    protected String studentName;
    protected String studentSurname;
    protected String className;
    protected String comment;

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
