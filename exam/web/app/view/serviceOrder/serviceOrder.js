'use strict';

angular.module('app.serviceOrder', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/serviceOrders', {
            templateUrl: 'view/serviceOrder/serviceOrder.html',
            controller: 'ServiceOrderCtrl'
        });
    }])

    .controller('ServiceOrderCtrl', ['$scope', 'serviceOrderService',
        function ($scope, serviceOrderService) {

            $scope.data = {};

            init();

            function init() {
                serviceOrderService.getAll(function(serviceOrders) {
                    $scope.data.serviceOrders = serviceOrders;
                }, function() {
                    console.error('Failed to get service orders');
                });
            }

        }]);
