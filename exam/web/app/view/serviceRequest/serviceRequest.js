'use strict';

angular.module('app.serviceRequest', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/serviceRequests', {
            templateUrl: 'view/serviceRequest/serviceRequest.html',
            controller: 'ServiceRequestCtrl'
        });
    }])

    .controller('ServiceRequestCtrl', ['$scope', 'serviceRequestService',
        function ($scope, serviceRequestService) {

            $scope.data = {};

            init();

            function init() {
                serviceRequestService.getAll(function(serviceRequests) {
                    $scope.data.serviceRequests = serviceRequests;
                }, function() {
                    console.error('Failed to get service requests');
                });
            }

        }]);
