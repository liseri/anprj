(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LoanApplyDialogController', LoanApplyDialogController);

    LoanApplyDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'LoanApply'];

    function LoanApplyDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, LoanApply) {
        var vm = this;

        vm.loanApply = entity;
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
            if (vm.loanApply.id !== null) {
                LoanApply.update(vm.loanApply, onSaveSuccess, onSaveError);
            } else {
                LoanApply.save(vm.loanApply, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('anprjApp:loanApplyUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.applyDate = false;
        vm.datePickerOpenStatus.auditDate = false;
        vm.datePickerOpenStatus.loanDate = false;
        vm.datePickerOpenStatus.completeDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
