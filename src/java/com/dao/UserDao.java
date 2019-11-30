/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Expense;
import com.model.User;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author luissandoval
 */
public class UserDao {
    public void save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public List<User> showAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From User");
        List<User> users = query.list();
        return users;
    }

    public User getById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.load(User.class, id); 
        return user;
    }
    
    public void update(int id, String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(Expense.class, id);
        user.setEmail(email);
        user.setPassword(password);
        transaction.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.load(Expense.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }
    
    public User getByEmail(String email){
        Session session = HibernateUtil.getSessionFactory().openSession();        
        Query query = session.createQuery(
                "select user "
                + "from User user "
                + "where user.email = " + "'" + email + "'");        
        User user = null;
        if (!query.list().isEmpty()) {
            user = (User) query.list().get(0);
        } 
        return user;
    }
}
