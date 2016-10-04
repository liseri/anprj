(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('EnterpriseDialogController', EnterpriseDialogController);

    EnterpriseDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Enterprise'];

    function EnterpriseDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Enterprise) {
        var vm = this;

        vm.enterprise = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.enterprise.id !== null) {
                Enterprise.update(vm.enterprise, onSaveSuccess, onSaveError);
            } else {
                Enterprise.save(vm.enterprise, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('anprjApp:enterpriseUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
