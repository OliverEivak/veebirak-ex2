'use strict';

angular.module('app.services.serviceRequestService', []).
    factory('serviceRequestService', ['httpService',
        function (httpService) {

            var instance = {
                getAll: function(successCallback, errorCallback) {
                    httpService.makeGet('repair-api/serviceRequests', {}, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            errorCallback(response);
                        }
                    }, errorCallback);
                },

                get: function(id, successCallback, errorCallback) {
                    httpService.makeGet('repair-api/serviceRequests/' + id, {}, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            errorCallback(response);
                        }
                    }, errorCallback);
                },

                update: function(serviceRequest, successCallback, errorCallback) {
                    httpService.makePost('repair-api/serviceRequests', serviceRequest, function(response) {
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
