'use strict';

angular.module('app.services.deviceService', []).
    factory('deviceService', ['httpService',
        function (httpService) {

            var instance = {
                getAll: function(successCallback, errorCallback) {
                    httpService.makeGet('repair-api/devices', {}, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            errorCallback(response);
                        }
                    }, errorCallback);
                },

                get: function(id, successCallback, errorCallback) {
                    httpService.makeGet('repair-api/devices/' + id, {}, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            errorCallback(response);
                        }
                    }, errorCallback);
                },

                update: function(device, successCallback, errorCallback) {
                    httpService.makePost('repair-api/devices', device, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            errorCallback(response);
                        }
                    }, errorCallback);
                },

                search: function(search, successCallback, errorCallback) {
                    httpService.makeGet('repair-api/devices/search', search, function(response) {
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
