(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LendApplyDialogController', LendApplyDialogController);

    LendApplyDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'LendApply'];

    function LendApplyDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, LendApply) {
        var vm = this;

        vm.lendApply = entity;
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
            if (vm.lendApply.id !== null) {
                LendApply.update(vm.lendApply, onSaveSuccess, onSaveError);
            } else {
                LendApply.save(vm.lendApply, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('anprjApp:lendApplyUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.applyDate = false;
        vm.datePickerOpenStatus.startDate = false;
        vm.datePickerOpenStatus.completeDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
