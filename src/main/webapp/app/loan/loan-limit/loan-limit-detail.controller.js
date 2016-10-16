(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LoanLimitDetailController', LoanLimitDetailController);

    LoanLimitDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LoanLimit'];

    function LoanLimitDetailController($scope, $rootScope, $stateParams, previousState, entity, LoanLimit) {
        var vm = this;

        vm.loanLimit = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('anprjApp:loanLimitUpdate', function(event, result) {
            vm.loanLimit = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
