package com.zalewskiwojtczak;

public abstract class Person {
    private String id;
    private String firstname;
    private String lastname;
    private int address;
    private int classOrClassroom;
    private String pesel;
    private int phone;
    private String email;

    public Person(String id, String firstname, String lastname, int address, int studentsClass,
                   String pesel, int phone, String email){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.classOrClassroom = studentsClass;
        this.pesel = pesel;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public int getAddress() {
        return address;
    }

    public int getStudentsClass() {
        return classOrClassroom;
    }

    public String getPesel() {
        return pesel;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
