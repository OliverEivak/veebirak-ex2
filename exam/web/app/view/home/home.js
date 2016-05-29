'use strict';

angular.module('app.home', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'view/home/home.html',
            controller: 'HomeCtrl'
        });
    }])

    .controller('HomeCtrl', ['$scope', 'loginService', 'authenticationService',
        function ($scope, loginService, authenticationService) {

            $scope.isAuthenticated = authenticationService.isAuthenticated;

            $scope.loginForm = {};

            $scope.data = {
                formLogin: {}
            };

            init();

            function init() {

            }

            $scope.login = function () {
                if ($scope.data.formLogin.$valid) {
                    $scope.data.showLoginError = false;
                    loginService.login($scope.loginForm.username, $scope.loginForm.password, loginFail);
                }
            };

            $scope.cancel = function () {
                $scope.loginForm = {};
            };

            function loginFail(response) {
                $scope.data.showLoginError = true;
            }

        }]);
