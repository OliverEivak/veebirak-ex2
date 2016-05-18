'use strict';

angular.module('app.header', [])

    .directive('header', ['$location',
        function ($location) {
            return {
                scope: true,
                templateUrl: 'components/header/header.html',
                controller: function ($scope) {

                    $scope.isPage = function(page) {
                        return $location.url().startsWith(page);
                    };

                    $scope.isHomePage = function(page) {
                        return $location.url() === '/';
                    };

                }
            }
        }]);
