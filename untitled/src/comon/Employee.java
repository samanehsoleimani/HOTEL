package comon;

import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String nationalId;
    private String phoneNumber;
    private int workExperience;
    private String skills;
    private String position;

    public Employee(String firstName, String lastName, String nationalId,
                    String phoneNumber, int workExperience, String skills,
                    String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.workExperience = workExperience;
        this.skills = skills;
        this.position = position;
    }

    public String getEmployeeInfo() {
        return "نام و نام خانوادگی: " + firstName + " " + lastName + "\n" +
                "کد ملی: " + nationalId + "\n" +
                "شماره تماس: " + phoneNumber + "\n" +
                "سابقه کار: " + workExperience + " سال\n" +
                "مهارت‌ها: " + skills + "\n" +
                "سمت: " + position;
    }

    // Getter methods
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getNationalId() { return nationalId; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getWorkExperience() { return workExperience; }
    public String getSkills() { return skills; }
    public String getPosition() { return position; }
}