'use strict';

angular.module('app.services.serviceDeviceStatusTypeService', []).
    factory('serviceDeviceStatusTypeService', ['httpService',
        function (httpService) {

            var instance = {
                getAll: function(successCallback, errorCallback) {
                    httpService.makeGet('repair-api/serviceDeviceStatusTypes', {}, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            errorCallback(response);
                        }
                    }, errorCallback);
                },

                get: function(id, successCallback, errorCallback) {
                    httpService.makeGet('repair-api/serviceDeviceStatusTypes/' + id, {}, function(response) {
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
