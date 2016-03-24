<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<% System.out.println("Stars JSP.."); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- Custom styles -->
    <link type="text/css" rel="stylesheet" href="css/app.css"/>
</head>
<body>

<nav>
    <div class="nav-wrapper container">
        <ul id="nav-mobile" class="left">
            <li class="active"><a href="/s">List of stars</a></li>
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
            <h5>List of stars</h5>
            <table>
                <thead>
                <tr>
                    <th data-field="id">ID</th>
                    <th data-field="commonName">Common Name</th>
                    <th data-field="distanceInLightYears">Distance in light years</th>
                    <th data-field="description">Description</th>
                    <th data-field="description">Actions</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="star" items="${stars}">
                    <tr>
                        <td><c:out value="${star.id}"/></td>
                        <td><c:out value="${star.commonName}"/></td>
                        <td><c:out value="${star.distanceInLightYears}"/></td>
                        <td><a href="#" onclick="openDescription(<c:out value="${star.id}"/>); return false;">Description</a></td>
                        <td><a href="s?id=<c:out value="${star.id}"/>">Edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row" style="display: none;" id="description-box">
        <div class="col s12 m12 l8 offset-l2 card">
            <div class="card-content">
                <span class="card-title">Description</span>
                <form>
                    <div class="row">
                        <div class="input-field col s12 m12 l12">
                            <input id="star-id" type="number" name="id" readonly value="77"/>
                            <label for="star-id">ID</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m12 l12">
                        <textarea id="star-description" class="materialize-textarea" name="description" readonly>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a
                        </textarea>
                            <label for="star-description">Description</label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-action">
                <a href="#" id="close-description">Close</a>
            </div>
        </div>
    </div>
</div>

<div class="hiddendiv" style="display: none;"></div>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>

<script type="text/javascript" src="js/stars.js"></script>
</body>
</html>