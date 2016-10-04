(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LoanLimitDeleteController',LoanLimitDeleteController);

    LoanLimitDeleteController.$inject = ['$uibModalInstance', 'entity', 'LoanLimit'];

    function LoanLimitDeleteController($uibModalInstance, entity, LoanLimit) {
        var vm = this;

        vm.loanLimit = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LoanLimit.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
