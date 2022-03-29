package DTO;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable,Comparable<Student>{
    private String studentID;
    private String name;
    private int sex;
    private LocalDate birthdate;
    private int semester;

    public Student() {
    }

    public Student(String studentID, String name, int sex, LocalDate birthdate, int semester) {
        this.studentID = studentID;
        this.name = name;
        this.sex = sex;
        this.birthdate = birthdate;
        this.semester = semester;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        String sSex;
        if (sex == 1){
            sSex = "male";
        } else if (sex == 2){
            sSex = "Female";
        } else sSex = "Other";
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sSex + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", semester=" + semester +
                '}';
    }

    @Override
    public int compareTo(Student student) {
        return this.getStudentID().compareTo(studentID);
    }

}
