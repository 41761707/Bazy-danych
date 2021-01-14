package com.zalewskiwojtczak;

import java.util.Date;

public class Grade {
    private int note;
    private String teacherName;
    private String teacherSurname;
    private String subject;
    private Date date;
    private String comment;

    Grade(int note, String teacherName, String teacherSurname, String subject, Date date, String comment) {
        this.note = note;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.subject = subject;
        this.date = date;
        this.comment = comment;
    }

    public int getNote(){
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

