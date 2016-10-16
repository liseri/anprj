(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LoanPrjDeleteController',LoanPrjDeleteController);

    LoanPrjDeleteController.$inject = ['$uibModalInstance', 'entity', 'LoanPrj'];

    function LoanPrjDeleteController($uibModalInstance, entity, LoanPrj) {
        var vm = this;

        vm.loanPrj = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LoanPrj.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
