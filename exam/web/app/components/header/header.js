'use strict';

angular.module('app.header', [])

    .directive('header', ['$timeout', '$location', 'authenticationService', 'logoutService',
        function ($timeout, $location, authenticationService, logoutService) {
            return {
                scope: true,
                templateUrl: 'components/header/header.html',
                controller: function ($scope) {

                    $scope.isAuthenticated = authenticationService.isAuthenticated;
                    $scope.getUser = authenticationService.getUser;
                    $scope.logout = logoutService.logout;

                    $scope.isPage = function(page) {
                        return $location.url().startsWith(page);
                    };

                    $scope.isHomePage = function(page) {
                        return $location.url() === '/';
                    };

                }
            }
        }]);
