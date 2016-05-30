'use strict';

angular.module('app.services.deviceTypeService', []).
    factory('deviceTypeService', ['httpService',
        function (httpService) {

            var instance = {
                getAll: function(successCallback, errorCallback) {
                    httpService.makeGet('repair-api/deviceTypes', {}, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            errorCallback(response);
                        }
                    }, errorCallback);
                },

                get: function(id, successCallback, errorCallback) {
                    httpService.makeGet('repair-api/deviceTypes/' + id, {}, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            errorCallback(response);
                        }
                    }, errorCallback);
                }
            };

            return instance;

        }]);
