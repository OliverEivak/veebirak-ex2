'use strict';

angular.module('app.services.serviceOrderService', []).
    factory('serviceOrderService', ['httpService',
        function (httpService) {

            var instance = {
                getAll: function(successCallback, errorCallback) {
                    httpService.makeGet('repair-api/serviceOrders', {}, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            errorCallback(response);
                        }
                    }, errorCallback);
                },

                get: function(id, successCallback, errorCallback) {
                    httpService.makeGet('repair-api/serviceOrders/' + id, {}, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            errorCallback(response);
                        }
                    }, errorCallback);
                },

                update: function(serviceOrder, successCallback, errorCallback) {
                    httpService.makePost('repair-api/serviceOrders', serviceOrder, function(response) {
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
