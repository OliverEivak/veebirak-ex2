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
                serviceDevices: [],
                serviceParts: []
            };

            init();

            function init() {
                getServiceOrder();
                getServiceRequest();
                getServiceDeviceStatusTypes();
            }

            function getServiceOrder() {
                if ($routeParams.serviceOrderID) {
                    serviceOrderService.get($routeParams.serviceOrderID, function(serviceOrder) {
                        $scope.serviceOrder = serviceOrder;
                    }, function() {
                        console.error('Failed to get ServiceOrder')
                    });
                }
            }

            function getServiceRequest() {
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
            }

            function getServiceDeviceStatusTypes() {
                serviceDeviceStatusTypeService.getAll(function(types) {
                    $scope.data.serviceDeviceStatusTypes = types;
                }, function() {
                    console.error('Failed to get ServiceDeviceStatusTypes')
                });
            }

            $scope.save = function() {
                if ($scope.data.form.$valid) {
                    serviceOrderService.update($scope.serviceOrder, function() {
                        $location.url('/serviceOrders');
                    }, function() {
                        console.error('Failed to save ServiceOrder')
                    });
                }
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

            $scope.remove = function(serviceDevice) {
                $scope.serviceOrder.serviceDevices.splice($scope.serviceOrder.serviceDevices.indexOf(serviceDevice), 1);
            };

            $scope.removeServicePart = function(servicePart) {
                $scope.serviceOrder.serviceParts.splice($scope.serviceOrder.serviceParts.indexOf(servicePart), 1);
            };

            $scope.addServicePartRow = function() {
                $scope.serviceOrder.serviceParts.push({
                    count: 1,
                    price: 0
                });
            };

            $scope.getMaxPartCount = function(servicePart) {
                if (servicePart && servicePart.serialNumber) {
                    return 1;
                }
                return Number.MAX_VALUE;
            };

        }]);
