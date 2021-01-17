package com.zalewskiwojtczak;

import java.util.Date;

public class ParentViewGrade extends Grade{
	protected String studentName;
	protected String studentSurname;
	protected float note;
	ParentViewGrade(String studentName,String studentSurname, float note,
			String teacherName, String teacherSurname,String subject,Date date, String comment)
	{
		super(note,teacherName,teacherSurname,subject,date,comment);
		this.studentName=studentName;
		this.studentSurname=studentSurname;
	}
	public String getStudentName()
	{
		return studentName;
	}
	public String getStudentSurname()
	{
		return studentSurname;
	}
	public float getNote(){
        return super.getNote();
    }

    public String getTeacherName(){
        return super.getTeacherName();
    }

    public String getTeacherSurname(){
        return super.getTeacherSurname();
    }

    public String getSubject(){
        return super.getSubject();
    }

    public Date getDate(){
        return super.getDate();
    }

    public String getComment(){
        return super.getComment();
    }
}
