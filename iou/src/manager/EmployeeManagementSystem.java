package manager;

import common.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementSystem {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(String firstName, String lastName, String nationalId,
                            String phoneNumber, int workExperience, String virtualAssets, String position) {
        employees.add(new Employee(firstName, lastName, nationalId, phoneNumber,
                workExperience, virtualAssets, position));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public List<Employee> searchByExperience(int minExperience) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getWorkExperience() >= minExperience) {
                result.add(emp);
            }
        }
        return result;
    }

    public Employee searchByNationalId(String nationalId) {
        for (Employee emp : employees) {
            if (emp.getNationalId().equals(nationalId)) {
                return emp;
            }
        }
        return null;
    }
}