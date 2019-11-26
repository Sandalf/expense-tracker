/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Category;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author luissandoval
 */
public class CategoryDao {
     public void save(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(category);
        transaction.commit();
        session.close();
    }

    public List<Category> showAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From Category");
        List<Category> categories = query.list();
        return categories;
    }

    public Category getById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Category category = (Category) session.load(Category.class, id); 
        return category;
    }
    
    public void update(int id, String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        category.setName(name);
        transaction.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Category category = (Category) session.load(Category.class, id);
        session.delete(category);
        transaction.commit();
        session.close();
    }       
}
