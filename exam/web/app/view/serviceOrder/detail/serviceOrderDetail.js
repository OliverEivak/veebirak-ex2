'use strict';

angular.module('app.serviceOrderDetail', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        var serviceOrderDetail = {
            templateUrl: 'view/serviceOrder/detail/serviceOrderDetail.html',
            controller: 'ServiceOrderDetailCtrl'
        };

        $routeProvider.when('/serviceOrders/new', serviceOrderDetail);
        $routeProvider.when('/serviceOrders/:serviceOrderID', serviceOrderDetail);
    }])

    .controller('ServiceOrderDetailCtrl', ['$scope', '$location', 'serviceOrderService', '$routeParams', 'serviceDeviceStatusTypeService',
        'serviceRequestService',
        function ($scope, $location, serviceOrderService, $routeParams, serviceDeviceStatusTypeService,
                  serviceRequestService) {

            $scope.data = {};
            $scope.serviceOrder = {
                serviceDevices: []
            };

            init();

            function init() {
                if ($routeParams.serviceOrderID) {
                    serviceOrderService.get($routeParams.serviceOrderID, function(serviceOrder) {
                        $scope.serviceOrder = serviceOrder;
                    }, function() {
                        console.error('Failed to get ServiceOrder')
                    });
                }

                var searchObject = $location.search();
                if (searchObject.serviceRequest) {
                    $scope.serviceOrder.serviceRequest = {
                        id: searchObject.serviceRequest
                    };

                    serviceRequestService.get(searchObject.serviceRequest, function(serviceRequest) {
                        $scope.serviceOrder.serviceRequest = serviceRequest;
                    }, function() {
                        console.error('Failed to get ServiceRequest');
                    });
                }

                serviceDeviceStatusTypeService.getAll(function(types) {
                    $scope.data.serviceDeviceStatusTypes = types;
                }, function() {
                    console.error('Failed to get ServiceDeviceStatusTypes')
                });
            }

            $scope.save = function() {
                serviceOrderService.update($scope.serviceOrder, function() {
                    $location.url('/serviceOrders');
                }, function() {
                    console.error('Failed to save ServiceOrder')
                });
            };

            $scope.deviceSelected = function(device) {
                if (device) {
                    var serviceDevice = {
                        device: device,
                        serviceDeviceStatusType: {
                            id: 1
                        }
                    };
                    $scope.serviceOrder.serviceDevices.push(serviceDevice);
                }
            };

            $scope.remove = function(serviceDeviceIndex) {
                $scope.serviceOrder.serviceDevices.splice(serviceDeviceIndex, 1);
            };

        }]);
