(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('AddressDetailController', AddressDetailController);

    AddressDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Address'];

    function AddressDetailController($scope, $rootScope, $stateParams, previousState, entity, Address) {
        var vm = this;

        vm.address = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('anprjApp:addressUpdate', function(event, result) {
            vm.address = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
