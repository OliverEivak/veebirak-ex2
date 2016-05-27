'use strict';

angular.module('app.services.modalService', []).
    factory('modalService', [
        function () {

            var modal = {
                title: null,
                content: null,
                successButton: null,
                cancelButton: null,
                successCallback: null,
                cancelCallback: null
            };

            var instance = {
                showConfirmation : function(title, content, successButton, cancelButton, successCallback, cancelCallback) {
                    modal = {
                        title: title,
                        content: content,
                        successButton: successButton,
                        cancelButton: cancelButton,
                        successCallback: successCallback,
                        cancelCallback: cancelCallback
                    };

                    instance.open = true;
                },

                getModal : function() {
                    return modal;
                },

                open: false
            };

            return instance;

    }]);
