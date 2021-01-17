package com.zalewskiwojtczak;

public class ParentViewBehaviour {
	private String studentName;
	private String studentSurname;
	private int points;
	
	ParentViewBehaviour(String studentName, String studentSurname, int points)
	{
		this.studentName=studentName;
		this.studentSurname=studentSurname;
		this.points=points;
	}
	
	public String getStudentName() {
		return studentName;
	}
	public String getStudentSurname() {
		return studentSurname;
	}
	public int getPoints() {
		return points;
	}
}
