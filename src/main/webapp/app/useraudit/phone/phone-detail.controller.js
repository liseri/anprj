(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('PhoneDetailController', PhoneDetailController);

    PhoneDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Phone'];

    function PhoneDetailController($scope, $rootScope, $stateParams, previousState, entity, Phone) {
        var vm = this;

        vm.phone = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('anprjApp:phoneUpdate', function(event, result) {
            vm.phone = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
