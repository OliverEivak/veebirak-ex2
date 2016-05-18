'use strict';

angular.module('app.logs', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/logs', {
            templateUrl: 'view/logs/logs.html',
            controller: 'LogsCtrl'
        });
    }])

    .controller('LogsCtrl', ['$scope',
        function ($scope) {

        }]);
