'use strict';

angular.module('app.home', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'view/home/home.html',
            controller: 'HomeCtrl'
        });
    }])

    .controller('HomeCtrl', ['$scope', 'starService', '$timeout',
        function ($scope, starService, $timeout) {

            init();

            function init() {
                starService.getAll(function(stars) {
                    $scope.stars = stars;
                });
            }

            $scope.openDescription = function(starID) {
                starService.get(starID, function(star) {
                    $scope.star = star;
                    $timeout(function() {
                        resizeTextarea($("#star-description"));
                    });
                });
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
