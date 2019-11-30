<%-- 
    Document   : listExpenses
    Created on : Nov 21, 2019, 10:13:11 PM
    Author     : luissandoval
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/bulma.css" rel="stylesheet" />
        <link type="text/css" href="css/main.css" rel="stylesheet" />
        <title>Gastos | Control de gastos</title>
    </head>
    <body>
        <nav class="navbar is-light" role="navigation" aria-label="main navigation">
            <div class="container">
                <div class="navbar-brand">
                    <a class="navbar-item" href="ExpensesController?action=list">
                        <strong>Control de gastos</strong>
                    </a>
                </div>

                <div class="navbar-menu">
                    <div class="navbar-start">
                        <a class="navbar-item is-active" href="ExpensesController?action=list">
                            Gastos
                        </a>

                        <a class="navbar-item" href="CategoriesController?action=list">
                            Categorias
                        </a>
                    </div>
                </div>
            </div>            
        </nav>
        <div class="container">
            <div class="columns mt-4">
                <div class="column">
                    <div class="level">
                        <div class="level-left">
                            <div class="level-item">
                                <strong>Total: $${total}</strong>                                
                            </div>
                        </div>
                        <div class="level-right">
                            <div class="level-item">
                                <a class="button is-primary" href="ExpensesController?action=insert">Agregar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <table class="table is-bordered is-fullwidth" border=1>
                    <thead>
                        <tr>
                            <th style="width: 100px" scope="col">Id</th>
                            <th scope="col">Descripción</th>
                            <th scope="col">Monto</th>
                            <th scope="col">Categoria</th>                            
                            <th style="width: 230px" scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${expenses}" var="expense">
                            <tr>
                                <td><c:out value="${expense.id}" /></td>
                                <td><c:out value="${expense.description}" /></td>
                                <td><c:out value="$${expense.amount}" /></td>
                                <td><c:out value="${expense.category.name}" /></td>
                                <td>
                                    <a class="button is-success" href="ExpensesController?action=edit&expenseId=<c:out value='${expense.id}'/>">Actualizar</a>
                                    <a class="button is-danger" href="ExpensesController?action=delete&expenseId=<c:out value='${expense.id}'/>">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>  
            </div>
        </div>
    </body>
</html>
