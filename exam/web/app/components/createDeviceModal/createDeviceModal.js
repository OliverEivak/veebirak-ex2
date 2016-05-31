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
                    $scope.device = {};

                    init();

                    function init() {
                        deviceTypeService.getAll(function(deviceTypes) {
                            $scope.data.deviceTypes = deviceTypes;
                            groupDeviceTypes();
                        }, function() {
                            console.error('Failed to get device types');
                        });
                    }

                    $scope.save = function() {
                        if ($scope.data.form.$valid) {
                            console.log('saving');

                            var device = angular.copy($scope.device);

                            // Replace with actual deviceType object.
                            // (option value can only have a string and we need optgroups so can't use ng-options)
                            for (var i = 0; i < $scope.data.deviceTypes.length; i++) {
                                if ($scope.data.deviceTypes[i].id == device.deviceType) {
                                    device.deviceType = $scope.data.deviceTypes[i];
                                }
                            }

                            deviceService.update(device, function(createdDevice) {
                                $scope.deviceSelectCallback(createdDevice);
                                $("#create-device-modal-close").click();
                            }, function() {
                                console.error('Failed to save device');
                            });
                        }
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
