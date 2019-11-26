<%-- 
    Document   : index.jsp
    Created on : Nov 21, 2019, 9:29:03 PM
    Author     : luissandoval
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control de gastos</title>
    </head>
    <body>
        <jsp:forward page="/ExpensesController?action=list" />
    </body>
</html>
