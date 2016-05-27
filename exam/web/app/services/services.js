'use strict';

angular.module('app.services', [
    'app.services.authenticationService',
    'app.services.loginService',
    'app.services.logoutService',
    'app.services.httpService',
    'app.services.modalService'
])

    .value('services', '0.1');
