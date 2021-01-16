package com.zalewskiwojtczak;

import java.util.Date;

public class TeacherViewGrade {
    private float note;
    private String legitymacja;
    private String studentName;
    private String studentSurname;
    private String className;
    private String subject;
    private Date date;
    private String comment;

    TeacherViewGrade(float note, String legitymacja, String studentName, String studentSurname, String className,String subject, Date date, String comment) {
        this.note = note;
        this.legitymacja=legitymacja;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.className=className;
        this.subject = subject;
        this.date = date;
        this.comment = comment;
    }

    public float getNote(){
        return note;
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

    public String getSubject(){
        return subject;
    }

    public Date getDate(){
        return date;
    }

    public String getComment(){
        return comment;
    }
}
