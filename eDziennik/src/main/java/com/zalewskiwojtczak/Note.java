package com.zalewskiwojtczak;

public class Note {
    private int minusPoints;
    private String teacherName;
    private String teacherSurname;
    private String comment;

    public Note(int minusPoints, String teacherName, String teacherSurname, String comment) {
        this.minusPoints = minusPoints;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.comment = comment;
    }

    public int getMinusPoints() {
        return minusPoints;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public String getComment() {
        return comment;
    }
}

