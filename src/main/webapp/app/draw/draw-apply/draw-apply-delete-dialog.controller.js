(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('DrawApplyDeleteController',DrawApplyDeleteController);

    DrawApplyDeleteController.$inject = ['$uibModalInstance', 'entity', 'DrawApply'];

    function DrawApplyDeleteController($uibModalInstance, entity, DrawApply) {
        var vm = this;

        vm.drawApply = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DrawApply.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
