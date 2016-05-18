'use strict';

angular.module('app.star', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/stars/:starID', {
            templateUrl: 'view/star/star.html',
            controller: 'StarCtrl'
        });
    }])

    .controller('StarCtrl', ['$scope', 'starService', '$timeout', '$routeParams',
        function ($scope, starService, $timeout, $routeParams) {

            $scope.starForm = {};

            init();

            function init() {
                starService.get($routeParams.starID, function(star) {
                    $scope.star = star;
                    $timeout(function() {
                        resizeTextarea($("#star-description"));
                        angular.element('#star-description').focus();
                    });
                });

            }

            $scope.update = function() {
                $scope.status = null;

                starService.update($scope.star, function(star) {
                    $scope.star = star;
                    $scope.status = 'success';
                }, function() {
                    $scope.status = 'fail';
                })
            };

            function resizeTextarea( textarea ) {
                var hiddenDiv = $('.hiddendiv').first();

                if (hiddenDiv.length) {
                    hiddenDiv.text(textarea.val() + '\n');
                    var content = hiddenDiv.html().replace(/\n/g, '<br>');
                    hiddenDiv.html(content);

                    hiddenDiv.css('width', textarea.width());
                    textarea.css('height', hiddenDiv.height());
                }
            }

        }]);
