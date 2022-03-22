package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{
    @Override
    public boolean isEmployee(String username, String password) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();


        List list = session.createQuery("From Employee where username='" + username + "' and password='" + password + "'").getResultList();



        t.commit();
        session.close();

        return (list.size() > 0);
    }

    @Override
    public boolean isAdmin(String username) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction() ;

        List list = session.createQuery("From Employee where username='"+username + "'").getResultList();
        Employee employee = (Employee) list.get(0);


        t.commit();
        session.close();

        return employee.isAdministrator();
    }

    @Override
    public List listAllEmployees() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction() ;

        List list = session.createQuery("From Employee").getResultList();


        t.commit();
        session.close();

        return list;
    }

    @Override
    public Employee getEmployee(String username) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction() ;

        List list = session.createQuery("From Employee where username='"+username+"'").getResultList();

        t.commit();
        session.close();

        return  (Employee) list.get(0);
    }

    @Override
    public void updateProfile(Employee employee) {

        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        session.update(employee);

        t.commit();
        session.close();

    }
}
