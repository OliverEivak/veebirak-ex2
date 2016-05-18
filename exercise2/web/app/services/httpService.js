'use strict';

angular.module('app.services.httpService', []).
    factory('httpService', ["services", "$http",
        function (services, $http) {

            function makeCall(url, method, params, successCallback, errorCallback) {
                var headers = {};

                var config = {
                    method: method,
                    url: url,
                    headers: headers
                };

                if (method === 'POST' || method === 'PUT') {
                    config.data = params;
                } else {
                    config.params = params;
                }

                $http(config).then(successCallback, function (response) {
                    if (errorCallback) {
                        errorCallback(response);
                    } else {
                        console.log('HTTP ' + method + ' ' + url + ' failed.');
                    }
                });
            }

            var instance = {
                makePost: function(url, data, successCallback, errorCallback) {
                    makeCall(url, 'POST', data, successCallback, errorCallback);
                },

                makeGet: function(url, params, successCallback, errorCallback) {
                    makeCall(url, 'GET', params, successCallback, errorCallback);
                },

                makePut: function(url, data, successCallback, errorCallback) {
                    makeCall(url, 'PUT', data, successCallback, errorCallback);
                },
                makeDelete: function(url, data, successCallback, errorCallback) {
                    makeCall(url, 'DELETE', data, successCallback, errorCallback);
                }
            };

            return instance;

        }]);
