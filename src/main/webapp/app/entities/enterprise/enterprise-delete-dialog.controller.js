(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('EnterpriseDeleteController',EnterpriseDeleteController);

    EnterpriseDeleteController.$inject = ['$uibModalInstance', 'entity', 'Enterprise'];

    function EnterpriseDeleteController($uibModalInstance, entity, Enterprise) {
        var vm = this;

        vm.enterprise = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Enterprise.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
