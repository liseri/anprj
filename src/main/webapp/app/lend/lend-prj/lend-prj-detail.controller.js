(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LendPrjDetailController', LendPrjDetailController);

    LendPrjDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LendPrj'];

    function LendPrjDetailController($scope, $rootScope, $stateParams, previousState, entity, LendPrj) {
        var vm = this;

        vm.lendPrj = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('anprjApp:lendPrjUpdate', function(event, result) {
            vm.lendPrj = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
