'use strict';

angular.module('app.customerModal', [])

    .directive('customerModal', ['customerService',
        function (customerService) {
            return {
                scope: {
                    customer: '='
                },
                templateUrl: 'components/customerModal/customerModal.html',
                controller: function ($scope) {

                    $scope.data = {};

                    function search(name) {
                        customerService.searchByName(name, function(customers) {
                            $scope.data.customers = customers;
                        }, function() {
                            console.error('Customer search failed');
                        });
                    }

                    $scope.$watch('data.query', function(newValue, oldValue) {
                        if (newValue !== oldValue && newValue) {
                            search(newValue);
                        }
                    });

                    $scope.choose = function(customer) {
                        $scope.customer = customer;
                        $("#customer-modal-close").click();
                    }

                }
            }
        }]);
