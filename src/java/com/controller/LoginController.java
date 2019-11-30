/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.ExpenseDao;
import com.dao.UserDao;
import com.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    /* Actions */
    private static final String ACTION_LOGIN = "login";
    private static final String ACTION_SIGNUP = "signup";

    /* Routes */
    private static final String ROUTE_LOGIN = "/login.jsp";
    private static final String ROUTE_SINGUP = "/signup.jsp";
    private static final String ROUTE_LIST_EXPENSES = "/listExpenses.jsp";

    /* DAOs */
    private UserDao dao;
    private ExpenseDao expenseDao;

    public LoginController() {
        super();
        dao = new UserDao();
        expenseDao = new ExpenseDao();
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
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        boolean isCorrectPassword = false;
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        User dbUser = dao.getByEmail(user.getEmail());

        RequestDispatcher view = null;

        if (dbUser != null) {
            isCorrectPassword = user.getPassword().equals(dbUser.getPassword());
        }

        switch (action) {
            case ACTION_SIGNUP:
                if (dbUser == null) {
                    user.setCreatedAt(new Date());
                    dao.save(user);
//                    view = request.getRequestDispatcher(ROUTE_LIST_EXPENSES);
//                    request.setAttribute("expenses", expenseDao.showAll());
//                    view = request.getRequestDispatcher(ROUTE_SINGUP);
//                    request.setAttribute("success", "Usuario registrado exitosamente");
                    out.print("{ \"success\": \"Usuario registrado exitosamente\" }");
                } else {
//                    view = request.getRequestDispatcher(ROUTE_SINGUP);
//                    request.setAttribute("error", "Ya existe un usuario con estos datos");
                    out.print("{ \"error\": \"Ya existe un usuario con estos datos\" }");
                }
                break;
            case ACTION_LOGIN:
                if (dbUser != null && isCorrectPassword) {
//                    view = request.getRequestDispatcher(ROUTE_LIST_EXPENSES);
//                    request.setAttribute("expenses", expenseDao.showAll());
//                    view = request.getRequestDispatcher(ROUTE_LOGIN);
//                    request.setAttribute("success", "Contraseña correcta");
                    out.print("{ \"success\": \"Contraseña correcta\" }");
                } else {
//                    view = request.getRequestDispatcher(ROUTE_LOGIN);
//                    request.setAttribute("error", "Usuario o contraseña incorrectos");
                    out.print("{ \"error\": \"Usuario o contraseña incorrectos\" }");
                }
                break;
            default:
//                view = request.getRequestDispatcher(ROUTE_LOGIN);
//                request.setAttribute("error", "Ocurrio un error");
                out.print("{ \"error\": \"Ocurrio un error\" }");
                break;
        }

        out.flush();

        //view.forward(request, response);
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
