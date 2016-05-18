'use strict';


(function(angular){

    angular.module('app', [
            'ngRoute',
            'ngMessages',
            'ui.materialize',
            'app.version',
            'app.services',
            'app.header',
            'app.home',
            'app.star',
            'app.logs'
        ])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.otherwise({redirectTo: '/'});
        }])

        .config(['$locationProvider', function ($locationProvider) {
            $locationProvider.html5Mode(true);
        }])

})(angular);