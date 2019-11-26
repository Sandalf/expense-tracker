<%-- 
    Document   : category
    Created on : Nov 25, 2019, 9:10:13 PM
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
        <title>Categoría</title>
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

                        <a class="navbar-item" href="CategoriesController?action=list">
                            Categorias
                        </a>
                    </div>
                </div>
            </div>            
        </nav>
        <div class="container">
            <div class="column is-2">
                <form method="POST" action="CategoriesController" name="frmAddCategory">
                    <input class="input" type = "hidden" name="id" hidden
                           value="<c:out value="${category.id}" />" />
                    <div class="field">
                        <label class="label">Id</label>
                        <div class="control">
                            <input class="input" disabled type="text" name="id"
                                   value="<c:out value="${category.id}" />" />
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Nombre</label>
                        <div class="control">
                            <input class="input" type="text" name="name"
                                   value="<c:out value="${category.name}" />" />
                        </div>
                    </div>
                    <button class="button is-primary" type="submit">
                        <c:out value="${empty category.id ? 'Crear' : 'Actualizar'}"/>                    
                    </button>
                </form>
            </div>
        </div>
    </body>
</html>
