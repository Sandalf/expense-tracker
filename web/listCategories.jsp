<%-- 
    Document   : listCategories
    Created on : Nov 25, 2019, 8:44:58 PM
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
        <title>Categorías | Control de gastos</title>
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
                        <a class="navbar-item" href="ExpensesController?action=list">
                            Gastos
                        </a>

                        <a class="navbar-item is-active" href="CategoriesController?action=list">
                            Categorias
                        </a>
                    </div>
                </div>
            </div>            
        </nav>
        <div class="container is-widescreen">
            <div class="columns mt-4">
                <div class="column">
                    <div class="level">
                        <div class="level-left">
                            <div class="level-item">                           
                            </div>
                        </div>
                        <div class="level-right">
                            <div class="level-item">
                                <a class="button is-primary" href="CategoriesController?action=insert">Agregar</a>
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
                            <th scope="col">Nombre</th>
                            <th style="width: 230px" scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${categories}" var="category">
                            <tr>
                                <td><c:out value="${category.id}" /></td>
                                <td><c:out value="${category.name}" /></td>
                                <td>
                                    <a class="button is-success" href="CategoriesController?action=edit&categoryId=<c:out value='${category.id}'/>">Actualizar</a>
                                    <a class="button is-danger" href="CategoriesController?action=delete&categoryId=<c:out value='${category.id}'/>">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>  
            </div>
        </div>
    </body>
</html>
