'use strict';

angular.module('app.services.loginService', []).
    factory('loginService', ['services', '$location', '$timeout', 'httpService', 'authenticationService',
        function (services, $location, $timeout, httpService, authenticationService) {

            var instance = {
                login : function(username, password, errorCallback) {
                    var params = {
                        username: username,
                        password: password
                    };

                    httpService.makePost('repair-api/login', params, function(response) {
                        if (response.data) {
                            authenticationService.setAuthentication(response.data);
                            $timeout(function() {
                                $location.url('/');
                            }, 300);
                        } else {
                            errorCallback(response);
                        }
                    }, errorCallback);
                }
            };

            return instance;

        }]);
