package common;

public class Employee {
    private String firstName;
    private String lastName;
    private String nationalId;
    private String phoneNumber;
    private int workExperience;
    private String virtualAssets;
    private String position;

    public Employee(String firstName, String lastName, String nationalId, String phoneNumber,
                    int workExperience, String virtualAssets, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.workExperience = workExperience;
        this.virtualAssets = virtualAssets;
        this.position = position;
    }

    public String getEmployeeInfo() {
        return "نام: " + firstName + " " + lastName +
                "\nکد ملی: " + nationalId +
                "\nشماره همراه: " + phoneNumber +
                "\nسابقه کار: " + workExperience + " سال" +
                "\nسرمایه‌های مجازی: " + virtualAssets +
                "\nسمت: " + position;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getNationalId() { return nationalId; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getWorkExperience() { return workExperience; }
    public String getVirtualAssets() { return virtualAssets; }
    public String getPosition() { return position; }
}