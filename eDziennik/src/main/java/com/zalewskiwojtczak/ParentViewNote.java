package com.zalewskiwojtczak;

import java.util.Date;

public class ParentViewNote extends TeacherViewNote{
	protected String teacherName;
	protected String teacherSurname;
	
	ParentViewNote(String studentName, String studentSurname, int points, String teacherName,
			String teacherSurname,String comment)
	{
		super(points,"",studentName,studentSurname,"",comment);
		this.teacherName=teacherName;
		this.teacherSurname=teacherSurname;
	}
	public String getTeacherName()
	{
		return teacherName;
	}
	public String getTeacherSurname()
	{
		return teacherSurname;
	}
	public int getPoints(){
        return super.getPoints();
    }

    public String getStudentName(){
        return super.getStudentName();
    }

    public String getStudentSurname(){
        return super.getStudentSurname();
    }

    public String getComment(){
        return super.getComment();
    }

}
