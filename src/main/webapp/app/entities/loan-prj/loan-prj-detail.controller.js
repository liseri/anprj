(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LoanPrjDetailController', LoanPrjDetailController);

    LoanPrjDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LoanPrj'];

    function LoanPrjDetailController($scope, $rootScope, $stateParams, previousState, entity, LoanPrj) {
        var vm = this;

        vm.loanPrj = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('anprjApp:loanPrjUpdate', function(event, result) {
            vm.loanPrj = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
