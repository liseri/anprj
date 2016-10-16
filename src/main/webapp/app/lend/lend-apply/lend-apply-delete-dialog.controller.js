(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LendApplyDeleteController',LendApplyDeleteController);

    LendApplyDeleteController.$inject = ['$uibModalInstance', 'entity', 'LendApply'];

    function LendApplyDeleteController($uibModalInstance, entity, LendApply) {
        var vm = this;

        vm.lendApply = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LendApply.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
