/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.CategoryDao;
import com.dao.ExpenseDao;
import com.model.Category;
import com.model.Expense;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luissandoval
 */
@WebServlet(name = "CategoriesController", urlPatterns = {"/CategoriesController"})
public class CategoriesController extends HttpServlet {

    /* Actions */
    private static final String ACTION_DELETE = "delete";
    private static final String ACTION_EDIT = "edit";
    private static final String ACTION_LIST = "list";
    
    /* Routes */
    private static final String ROUTE_EDIT = "/category.jsp";    
    private static final String ROUTE_LIST = "/listCategories.jsp";
    
    /* DAOs */
    private CategoryDao dao;
    private ExpenseDao expensesDao;
    
    public CategoriesController() {
        super();
        dao = new CategoryDao();
        expensesDao = new ExpenseDao();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase(ACTION_LIST)) {
            // List          
            List<Category> categories = dao.showAll();
            
            categories.forEach((cat) -> {
                cat.setTotal(expensesDao.getCategorySum(cat.getId()));
            });
          
            request.setAttribute("categories", categories);
            
            forward = ROUTE_LIST;
        } else if (action.equalsIgnoreCase(ACTION_EDIT)) {
            // Edit            
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            Category category = dao.getById(categoryId);                                 
            request.setAttribute("category", category);
            request.setAttribute("action", "edit");
            
            forward = ROUTE_EDIT;
        } else if (action.equalsIgnoreCase(ACTION_DELETE)) {
            // Delete
            int expenseId = Integer.parseInt(request.getParameter("categoryId"));
            dao.delete(expenseId);       
            
            List<Category> categories = dao.showAll();
            
            categories.forEach((cat) -> {
                cat.setTotal(expensesDao.getCategorySum(cat.getId()));
            });
            
            request.setAttribute("categories", categories);
            
            forward = ROUTE_LIST;
        } else {
            // Insert
            forward = ROUTE_EDIT;
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Category category = new Category();
        category.setName(request.getParameter("name"));
        
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            category.setCreatedAt(new Date());
            dao.save(category);
        } else {
            category.setUpdatedAt(new Date());            
            dao.update(Integer.parseInt(id), category.getName());
        }
        
        List<Category> categories = dao.showAll();
            
        categories.forEach((cat) -> {
            cat.setTotal(expensesDao.getCategorySum(cat.getId()));
        });

        RequestDispatcher view = request.getRequestDispatcher(ROUTE_LIST);
        request.setAttribute("categories", categories);
        view.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
