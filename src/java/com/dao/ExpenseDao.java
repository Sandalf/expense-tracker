/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Category;
import com.model.Expense;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author luissandoval
 */
public class ExpenseDao {
    public void save(Expense expense) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(expense);
        transaction.commit();
        session.close();
    }

    public List<Expense> showAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From Expense");
        List<Expense> expenses = query.list();
        return expenses;
    }

    public Expense getById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Expense expense = (Expense) session.load(Expense.class, id); 
        return expense;
    }
    
    public void update(int id, String description, Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Expense expense = (Expense) session.get(Expense.class, id);
        expense.setDescription(description);
        expense.setCategory(category);
        transaction.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Expense expense = (Expense) session.load(Expense.class, id);
        session.delete(expense);
        transaction.commit();
        session.close();
    }
    
    public Double getTotal() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select sum(exp.amount) from Expense exp");
        Double total = (Double)query.list().get(0);
        return total;
    }
}
