package com.zalewskiwojtczak;

import java.util.Date;

public class Grade {
    protected float note;
    protected String teacherName;
    protected String teacherSurname;
    protected String subject;
    protected Date date;
    protected String comment;

    Grade(float note, String teacherName, String teacherSurname, String subject, Date date, String comment) {
        this.note = note;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.subject = subject;
        this.date = date;
        this.comment = comment;
    }

    public float getNote(){
        return note;
    }

    public String getTeacherName(){
        return teacherName;
    }

    public String getTeacherSurname(){
        return teacherSurname;
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

