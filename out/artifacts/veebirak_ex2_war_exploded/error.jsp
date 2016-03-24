<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<% System.out.println("Star JSP.."); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- Custom styles -->
    <link type="text/css" rel="stylesheet" href="css/app.css" />
</head>
<body>

<nav>
    <div class="nav-wrapper container">
        <ul id="nav-mobile" class="left">
            <li><a href="/s">List of stars</a></li>
            <li><a href="/star.log">Logs</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col s12">

        </div>
    </div>
    <div class="row">
        <div class="col s12 m12 l8 offset-l2">
            <h5 class="valign-wrapper"><i class="medium material-icons valign">warning</i><span class="valign">Star not found</span></h5>

            <p>
                Unfortunately we could not find the star you were looking for.
                <a href="s">Click here</a> to return.
            </p>
        </div>
    </div>
</div>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
</body>
</html>