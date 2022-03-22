package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.List;

public class RequestDAOImpl implements RequestDAO{
    @Override
    public List listRequests() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();


        List list = session.createQuery("From Request").getResultList();


        t.commit();
        session.close();

        return list;

    }

    @Override
    public List listMyResolvedRequests(String username) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();


        List list = session.createQuery("From Request where employee='" + username + "' and (status='accepted' or status='denied')").getResultList();


        t.commit();
        session.close();

        return list;
    }

    @Override
    public List listMyPendingRequests(String username) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();


        List list = session.createQuery("From Request where employee='" + username + "' and status='pending'").getResultList();


        t.commit();
        session.close();

        return list;
    }

    @Override
    public List listAllPendingRequests() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();


        List list = session.createQuery("From Request where status='pending'").getResultList();


        t.commit();
        session.close();

        return list;
    }

    @Override
    public List listAllResolvedRequests() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();


        List list = session.createQuery("From Request where status='accepted' or status='denied'").getResultList();


        t.commit();
        session.close();

        return list;
    }

    @Override
    public void newRequest(Request request) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        List list = session.createQuery("from Request").getResultList();
        int size = list.size();
        int id;
        if (size > 0)
        {
            Request lastRequest = (Request) list.get(size-1);
            id = lastRequest.getId() + 1;
        } else{
            id = 1;
        }

        request.setId(id);
        request.setStatus("Pending");

        session.persist(request);

        t.commit();
        session.close();

    }

    @Override
    public void resolveRequest(Request request) {

        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        session.update(request);

        t.commit();
        session.close();

    }

    @Override
    public Request getRequest(int id) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        List list = session.createQuery("From Request where id="+id).getResultList();

        t.commit();
        session.close();

        return (Request) list.get(0);
    }


}
