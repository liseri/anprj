(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LoanApplyDetailController', LoanApplyDetailController);

    LoanApplyDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LoanApply'];

    function LoanApplyDetailController($scope, $rootScope, $stateParams, previousState, entity, LoanApply) {
        var vm = this;

        vm.loanApply = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('anprjApp:loanApplyUpdate', function(event, result) {
            vm.loanApply = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
