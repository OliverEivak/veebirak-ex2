'use strict';

angular.module('app.serviceRequestDetail', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/serviceRequests/new', {
            templateUrl: 'view/serviceRequest/detail/serviceRequestDetail.html',
            controller: 'ServiceRequestDetailCtrl'
        });
    }])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/serviceRequests/:serviceRequestID', {
            templateUrl: 'view/serviceRequest/detail/serviceRequestDetail.html',
            controller: 'ServiceRequestDetailCtrl'
        });
    }])

    .controller('ServiceRequestDetailCtrl', ['$scope', '$location', 'serviceRequestService', '$routeParams',
        function ($scope, $location, serviceRequestService, $routeParams) {

            $scope.data = {};

            init();

            function init() {
                if ($routeParams.serviceRequestID) {
                    serviceRequestService.get($routeParams.serviceRequestID, function(serviceRequest) {
                        $scope.serviceRequest = serviceRequest;
                    }, function() {
                        console.error('Failed to get ServiceRequest')
                    });
                }
            }

            $scope.save = function() {
                serviceRequestService.update($scope.serviceRequest, function() {
                    $location.url('/serviceRequests');
                }, function() {
                    console.error('Failed to save ServiceRequest')
                });
            };

        }]);
