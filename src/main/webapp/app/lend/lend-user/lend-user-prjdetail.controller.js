(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LendUserPrjDetailController', LendUserPrjDetailController);

    LendUserPrjDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LendUserPrj'];

    function LendUserPrjDetailController($scope, $rootScope, $stateParams, previousState, entity, LendUserPrj) {
        var vm = this;

        vm.lendPrj = entity;
        vm.previousState = previousState.name;
    }
})();
