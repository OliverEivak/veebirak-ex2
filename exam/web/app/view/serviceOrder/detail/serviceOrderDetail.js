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
        'serviceRequestService', 'serviceTypeService', 'serviceActionStatusTypeService',
        function ($scope, $location, serviceOrderService, $routeParams, serviceDeviceStatusTypeService,
                  serviceRequestService, serviceTypeService, serviceActionStatusTypeService) {

            $scope.data = {};

            $scope.serviceOrder = {
                serviceDevices: [],
                serviceParts: [],
                serviceActions: []
            };

            init();

            function init() {
                getServiceOrder();
                getServiceRequest();
                getServiceDeviceStatusTypes();
                getServiceTypes();
                getServiceActionStatusTypes();
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

            function getServiceTypes() {
                serviceTypeService.getAll(function(types) {
                    $scope.data.serviceTypes = types;
                }, function() {
                    console.error('Failed to get ServiceTypes')
                });
            }

            function getServiceActionStatusTypes() {
                serviceActionStatusTypeService.getAll(function(types) {
                    $scope.data.serviceActionStatusTypes = types;
                }, function() {
                    console.error('Failed to get ServiceActionStatusTypes')
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

            $scope.removeServiceAction = function(serviceAction) {
                $scope.serviceOrder.serviceActions.splice($scope.serviceOrder.serviceActions.indexOf(serviceAction), 1);
            };

            $scope.addServicePartRow = function() {
                $scope.serviceOrder.serviceParts.push({
                    count: 1,
                    price: 0
                });
            };

            $scope.addServiceActionRow = function() {
                $scope.serviceOrder.serviceActions.push({
                    amount: 1,
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
