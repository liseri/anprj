(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LendApplyDetailController', LendApplyDetailController);

    LendApplyDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LendApply'];

    function LendApplyDetailController($scope, $rootScope, $stateParams, previousState, entity, LendApply) {
        var vm = this;

        vm.lendApply = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('anprjApp:lendApplyUpdate', function(event, result) {
            vm.lendApply = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
