package com.revature;

public class RequestDAOFactory {

    private static RequestDAO dao;

    public static RequestDAO getRequestDAO(){
        if(dao==null)
        {
            dao= new RequestDAOImpl();
        }
        return dao;
    }
}
