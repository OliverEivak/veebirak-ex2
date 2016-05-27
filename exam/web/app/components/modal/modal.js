'use strict';

angular.module('app.modal', [])

    .directive('plModal', ['modalService',
        function (modalService) {
            return {
                scope: {},
                templateUrl: 'components/modal/modal.html',
                controller: function ($scope) {

                    $scope.modalService = modalService;
                    $scope.getModal = modalService.getModal;

                }
            }
        }]);
