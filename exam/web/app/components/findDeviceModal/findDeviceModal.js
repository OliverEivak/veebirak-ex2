'use strict';

angular.module('app.findDeviceModal', [])

    .directive('findDeviceModal', ['deviceService', 'deviceTypeService',
        function (deviceService, deviceTypeService) {
            return {
                scope: {
                    deviceSelectCallback: '='
                },
                templateUrl: 'components/findDeviceModal/findDeviceModal.html',
                controller: function ($scope) {

                    $scope.data = {};
                    $scope.search = {};

                    init();

                    function init() {
                        deviceTypeService.getAll(function(deviceTypes) {
                            $scope.data.deviceTypes = deviceTypes;
                            groupDeviceTypes();
                        }, function() {
                            console.error('Failed to get device types');
                        });
                    }

                    $scope.doSearch = function() {
                        deviceService.search($scope.search, function(devices) {
                            $scope.data.devices = devices;
                        }, function() {
                            console.error('Device search failed');
                        });
                    };

                    $scope.choose = function(device) {
                        $scope.deviceSelectCallback(device);
                        $("#find-device-modal-close").click();
                    };

                    $scope.clear = function() {
                        $scope.search = {};
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
