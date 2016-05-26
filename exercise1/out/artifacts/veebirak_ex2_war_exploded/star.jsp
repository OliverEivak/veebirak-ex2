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
            <h5>Edit star</h5>

            <form action="s?action=save" method="POST">
                <c:set var="statusSuccess" value="success" />
                <c:set var="statusFail" value="fail" />

                <c:if test="${status != null && status.equals(statusSuccess)}">
                <div class="row">
                    <div class="col s12 m12 l12">
                        <div class="card-panel valign-wrapper">
                            <i class="small material-icons left">done</i>
                            <span>Star updated</span>
                        </div>
                    </div>
                </div>
                </c:if>

                <c:if test="${status != null && status.equals(statusFail)}">
                    <div class="row">
                        <div class="col s12 m12 l12">
                            <div class="card-panel red lighten-2 valign-wrapper">
                                <i class="small material-icons left md-light">error_outline</i>
                                <span class="white-text">Failed to update star</span>
                            </div>
                        </div>
                    </div>
                </c:if>

                <div class="row">
                    <div class="input-field col s12 m12 l12">
                        <input id="star-id" type="number" name="id" readonly value="<c:out value="${star.id}"/>">
                        <label for="star-id">ID</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12 m12 l12">
                        <input id="star-common-name" type="text" name="commonName" value="<c:out value="${star.commonName}"/>"
                               class="validate <c:if test="${errors['commonName'] != null}">invalid</c:if>" required="" aria-required="true" length="32">
                        <label for="star-common-name"
                               <c:if test="${errors['commonName'] != null}">data-error="<c:out value="${errors['commonName']}"/>"</c:if>
                        >Common name</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12 m12 l12">
                        <input id="star-distance-in-light-years" type="text" name="distanceInLightYears"
                               value="<c:out value="${star.distanceInLightYears}"/>"
                               class="validate <c:if test="${errors['distanceInLightYears'] != null}">invalid</c:if>" required="" aria-required="true">
                        <label for="star-distance-in-light-years"
                               <c:if test="${errors['distanceInLightYears'] != null}">data-error="<c:out value="${errors['distanceInLightYears']}"/>"</c:if>
                        >Distance in light years</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12 m12 l12">
                        <textarea id="star-description" class="materialize-textarea validate <c:if test="${errors['description'] != null}">invalid</c:if>" name="description"
                                  length="5000"><c:out value="${star.description}"/></textarea>
                        <label for="star-description">Description</label>
                    </div>
                </div>
                <c:if test="${errors['description'] != null}">
                    <span class="red-text">
                        <c:out value="${errors['description']}"/>
                    </span>
                </c:if>
                <div class="row">
                    <div class="input-field col s12 m12 l12">
                        <div class="right">
                            <button class="btn waves-effect waves-light" type="submit" name="button-save">
                                Save <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>

<script type="text/javascript" src="js/star.js"></script>
</body>
</html>