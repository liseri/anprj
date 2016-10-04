(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('RealIdentityDeleteController',RealIdentityDeleteController);

    RealIdentityDeleteController.$inject = ['$uibModalInstance', 'entity', 'RealIdentity'];

    function RealIdentityDeleteController($uibModalInstance, entity, RealIdentity) {
        var vm = this;

        vm.realIdentity = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RealIdentity.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
