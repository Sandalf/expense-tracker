<%-- 
    Document   : singup
    Created on : Nov 30, 2019, 12:28:53 PM
    Author     : luissandoval
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/bulma.css" rel="stylesheet" />
        <link type="text/css" href="css/main.css" rel="stylesheet" />
        <title>Registro | Control de gastos</title>
    </head>
    <body>
        <div id="alert"></div>
        <div class="container">
            <div class="columns is-mobile is-centered">
                <div class="column is-one-third">
                    <div class="mt-4 has-text-centered">
                        <h1 class="has-text-weight-bold">Crear una cuenta</h1>
                    </div>
                    <div class="mt-4">
                        <div class="field">
                            <p class="control has-icons-left has-icons-right">
                                <input id="email" class="input" type="email" placeholder="Correo">
                                <span class="icon is-small is-left">
                                    <i class="fas fa-envelope"></i>
                                </span>
                            </p>
                        </div>
                        <div class="field">
                            <p class="control has-icons-left">
                                <input id="password" class="input" type="password" placeholder="Contraseña">
                                <span class="icon is-small is-left">
                                    <i class="fas fa-lock"></i>
                                </span>
                            </p>
                        </div>
                        <div class="field">
                            <p class="control has-text-centered">
                                <button id="submit" class="button is-success">
                                    Crear cuenta
                                </button><br>
                                <a href="login.jsp">Ya tengo una cuenta</a>
                            </p>                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
        <script src="./js/hash.js"></script>
        <script src="./js/login.js"></script>
        <script>
            document.querySelector("#submit").addEventListener("click", () => {
                login("signup");
            });
        </script>
    </body>
</html>
