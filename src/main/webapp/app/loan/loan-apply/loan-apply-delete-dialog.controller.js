(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LoanApplyDeleteController',LoanApplyDeleteController);

    LoanApplyDeleteController.$inject = ['$uibModalInstance', 'entity', 'LoanApply'];

    function LoanApplyDeleteController($uibModalInstance, entity, LoanApply) {
        var vm = this;

        vm.loanApply = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LoanApply.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
