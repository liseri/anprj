(function() {
    'use strict';

    angular
        .module('anprjApp')
        .directive('autoFocusWhen', ['$timeout', autoFocusWhen]);

    function autoFocusWhen($timeout) {
        var directive = {
            restrict: 'A',
            scope: {
                autoFocusWhen: '='
            },
            link: linkFunc
        };

        return directive;

        function linkFunc(scope, element, attrs, parentCtrl) {
            scope.$watch('autoFocusWhen', function(newValue) {
                if (newValue) {
                    $timeout(function(){
                        element[0].focus();
                    })
                }
            });
            element.on('blur', function() {
                scope.$apply(function() {
                    scope.autoFocusWhen = false;
                })
            });
        }
    }
})();
