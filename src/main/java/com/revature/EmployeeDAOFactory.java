package com.revature;

public class EmployeeDAOFactory {

    private static EmployeeDAO dao;

    public static EmployeeDAO getEmployeeDAO(){
        if (dao == null){
            dao = new EmployeeDAOImpl();
        }

        return dao;
    }
}
