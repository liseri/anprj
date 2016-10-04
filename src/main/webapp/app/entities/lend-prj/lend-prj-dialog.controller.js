(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LendPrjDialogController', LendPrjDialogController);

    LendPrjDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'LendPrj'];

    function LendPrjDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, LendPrj) {
        var vm = this;

        vm.lendPrj = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.lendPrj.id !== null) {
                LendPrj.update(vm.lendPrj, onSaveSuccess, onSaveError);
            } else {
                LendPrj.save(vm.lendPrj, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('anprjApp:lendPrjUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.activateDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
