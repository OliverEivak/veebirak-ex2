'use strict';

angular.module('app.services.starService', []).
    factory('starService', ['httpService',
        function (httpService) {

            var instance = {
                get : function(id, successCallback, failCallback) {
                    httpService.makeGet('star-api/stars/' + id, {}, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            failCallback();
                        }
                    }, failCallback);
                },

                getAll : function(successCallback) {
                    httpService.makeGet('star-api/stars', {}, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            getAllFail();
                        }
                    }, getAllFail);
                },

                update : function(star, successCallback, failCallback) {
                    httpService.makePost('star-api/stars', star, function(response) {
                        if (response.data) {
                            successCallback(response.data);
                        } else {
                            failCallback();
                        }
                    }, failCallback);
                }
            };

            function getAllFail() {
                console.error('Failed to get stars');
            }

            return instance;

        }]);
