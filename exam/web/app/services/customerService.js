'use strict';

angular.module('app.services.customerService', []).
    factory('customerService', ['httpService',
        function (httpService) {

            var instance = {
                searchByName: function(name, successCallback, errorCallback) {
                    var params = {
                        name: name
                    };

                    httpService.makeGet('repair-api/customers', params, function(response) {
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
