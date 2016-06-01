<!DOCTYPE html>
<html lang="en" data-ng-app="app">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Repairshop</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://localhost/styles/main.css">
    <base href="/">
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <a href="https://localhost/serviceRequests/new" class="waves-effect waves-light btn right">Create</a>
                    <h5>Service requests</h5>
                    <table>
                        <thead>
                            <tr>
                                <th data-field="id">ID</th>
                                <th data-field="name">Customer</th>
                                <th data-field="descriptionByClient">Description (customer)</th>
                                <th data-field="descriptionByEmployee">Description (employee)</th>
                                <th data-field="status">Status</th>
                                <th data-field="actions"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list serviceRequests as serviceRequest>
                            <tr>
                                <td>${serviceRequest.id}</td>
                                <td>${serviceRequest.customer.subject.name}</td>
                                <td>${serviceRequest.descriptionByCustomer}</td>
                                <td>${serviceRequest.descriptionByEmployee}</td>
                                <td>${serviceRequest.serviceRequestStatusType.name}</td>
                                <td>
                                    <a href="https://localhost/serviceRequests/${serviceRequest.id}" class="waves-effect waves-light btn right">Open</a>
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

<#include "footer.ftl">

</body>
</html>