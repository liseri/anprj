(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('PhoneDeleteController',PhoneDeleteController);

    PhoneDeleteController.$inject = ['$uibModalInstance', 'entity', 'Phone'];

    function PhoneDeleteController($uibModalInstance, entity, Phone) {
        var vm = this;

        vm.phone = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Phone.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
