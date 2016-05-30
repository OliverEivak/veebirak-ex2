'use strict';

angular.module('app.createDeviceModal', [])

    .directive('createDeviceModal', ['deviceService', 'deviceTypeService',
        function (deviceService, deviceTypeService) {
            return {
                scope: {
                    deviceSelectCallback: '='
                },
                templateUrl: 'components/createDeviceModal/createDeviceModal.html',
                controller: function ($scope) {

                    $scope.data = {};

                    init();

                    function init() {
                        deviceTypeService.getAll(function(deviceTypes) {
                            $scope.data.deviceTypes = deviceTypes;
                            groupDeviceTypes();
                        }, function() {
                            console.error('Failed to get device types');
                        });
                    }

                    $scope.choose = function(device) {
                        $scope.deviceSelectCallback(device);
                        $("#create-device-modal-close").click();
                    };

                    // TODO: save
                    $scope.save = function() {
                        console.log('saving');
                    };

                    function groupDeviceTypes() {
                        var level1 = [];
                        var level2 = [];

                        $scope.data.deviceTypes.forEach(function(deviceType) {
                            if (deviceType.level === 1) {
                                level1[deviceType.id] = deviceType;
                                level1[deviceType.id].deviceTypes = [];
                            } else {
                                level2.push(deviceType);
                            }
                        });

                        level2.forEach(function(deviceType) {
                            level1[deviceType.parent.id].deviceTypes.push(deviceType);
                        });

                        $scope.data.topLevelDeviceTypes = level1;
                    }

                }
            }
        }]);
