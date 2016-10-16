(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LendPrjDeleteController',LendPrjDeleteController);

    LendPrjDeleteController.$inject = ['$uibModalInstance', 'entity', 'LendPrj'];

    function LendPrjDeleteController($uibModalInstance, entity, LendPrj) {
        var vm = this;

        vm.lendPrj = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LendPrj.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
