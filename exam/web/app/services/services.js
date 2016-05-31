'use strict';

angular.module('app.services', [
    'app.services.authenticationService',
    'app.services.loginService',
    'app.services.logoutService',
    'app.services.httpService',
    'app.services.modalService',
    'app.services.serviceRequestService',
    'app.services.customerService',
    'app.services.serviceOrderService',
    'app.services.deviceService',
    'app.services.deviceTypeService',
    'app.services.serviceDeviceStatusTypeService',
    'app.services.serviceTypeService',
    'app.services.serviceActionStatusTypeService'
])

    .value('services', '0.1');
