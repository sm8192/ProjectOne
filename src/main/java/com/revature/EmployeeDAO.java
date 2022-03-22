package com.revature;

import java.util.List;

public interface EmployeeDAO {

    boolean isEmployee(String username, String password);
    boolean isAdmin(String username);
    List listAllEmployees();
    Employee getEmployee(String username);
    void updateProfile(Employee employee);
}
