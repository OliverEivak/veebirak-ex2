'use strict';

angular.module('app.serviceRequestDetail', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        var serviceRequestDetail = {
            templateUrl: 'view/serviceRequest/detail/serviceRequestDetail.html',
            controller: 'ServiceRequestDetailCtrl'
        };

        $routeProvider.when('/serviceRequests/new', serviceRequestDetail);
        $routeProvider.when('/serviceRequests/:serviceRequestID', serviceRequestDetail);
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

            $scope.reject = function() {
                $scope.serviceRequest.serviceRequestStatusType = {
                    id: 2
                };
                $scope.save();
            };

            $scope.createOrder = function() {
                serviceRequestService.update($scope.serviceRequest, function(serviceRequest) {
                    $location.url('/serviceOrders/new?serviceRequest=' + serviceRequest.id);
                }, function() {
                    console.error('Failed to save ServiceRequest')
                });
            };

            $scope.delete = function() {
                serviceRequestService.delete($scope.serviceRequest.id, function() {
                    $location.url('/serviceRequests');
                }, function() {
                    console.error('Failed to delete ServiceRequest')
                });
            };

        }]);
