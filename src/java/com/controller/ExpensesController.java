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
@WebServlet(name = "ExpensesController", urlPatterns = {"/ExpensesController"})
public class ExpensesController extends HttpServlet {
    
    /* Actions */
    private static final String ACTION_DELETE = "delete";
    private static final String ACTION_EDIT = "edit";
    private static final String ACTION_LIST = "list";
    
    /* Routes */
    private static final String ROUTE_EDIT = "/expense.jsp";    
    private static final String ROUTE_LIST = "/listExpenses.jsp";
    
    /* DAOs */
    private ExpenseDao dao;
    private CategoryDao categoryDao;    
    
    public ExpensesController() {
        super();
        dao = new ExpenseDao();
        categoryDao = new CategoryDao();
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
            Double total = dao.getTotal();            
            request.setAttribute("expenses", dao.showAll());
            request.setAttribute("total", total);
            
            forward = ROUTE_LIST;
        } else if (action.equalsIgnoreCase(ACTION_EDIT)) {
            // Edit            
            int expenseId = Integer.parseInt(request.getParameter("expenseId"));
            Expense expense = dao.getById(expenseId);            
            List<Category> categories = categoryDao.showAll();                        
            request.setAttribute("expense", expense);
            request.setAttribute("categories", categories);
            request.setAttribute("action", "edit");
            
            forward = ROUTE_EDIT;
        } else if (action.equalsIgnoreCase(ACTION_DELETE)) {
            // Delete
            int expenseId = Integer.parseInt(request.getParameter("expenseId"));
            dao.delete(expenseId);
            
            Double total = dao.getTotal();                        
            request.setAttribute("expenses", dao.showAll());
            request.setAttribute("total", total);
            
            forward = ROUTE_LIST;
        } else {
            // Insert
            List<Category> categories = categoryDao.showAll();
            request.setAttribute("categories", categories);
            
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
        Expense expense = new Expense();
        expense.setDescription(request.getParameter("description"));
        expense.setAmount(Double.parseDouble(request.getParameter("amount")));
        
        /* Set category */ 
        Category category = categoryDao.getById(Integer.parseInt(request.getParameter("category")));
        expense.setCategory(category);
        
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            expense.setCreatedAt(new Date());
            dao.save(expense);
        } else {
            expense.setUpdatedAt(new Date());            
            dao.update(Integer.parseInt(id), expense.getDescription(), expense.getCategory());
        }

        RequestDispatcher view = request.getRequestDispatcher(ROUTE_LIST);
        request.setAttribute("expenses", dao.showAll());
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
