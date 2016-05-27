'use strict';

angular.module('app.services.logoutService', []).
    factory('logoutService', ['services', '$location', 'httpService', 'authenticationService',
        function (services, $location, httpService, authenticationService) {

            function logoutDone() {
                authenticationService.removeAuthentication();
                $location.url('/');
            }

            var instance = {
                logout : function() {
                    httpService.makePost('repair-api/logout', {}, logoutDone, logoutDone);
                }
            };

            return instance;

        }]);
