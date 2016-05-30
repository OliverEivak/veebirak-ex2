'use strict';

(function(angular){

    angular.module('app', [
            'ngRoute',
            'ngMessages',
            'ui.materialize',
            'angularMoment',
            'app.version',
            'app.services',
            'app.header',
            'app.home',
            'app.modal',
            'app.serviceRequest',
            'app.serviceRequestDetail',
            'app.customerModal',
            'app.serviceOrder',
            'app.serviceOrderDetail',
            'app.findDeviceModal',
            'app.createDeviceModal'
        ])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.otherwise({redirectTo: '/'});
        }])

        .config(['$locationProvider', function ($locationProvider) {
            $locationProvider.html5Mode(true);
        }])

})(angular);