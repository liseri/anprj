(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LoanPrjDialogController', LoanPrjDialogController);

    LoanPrjDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'LoanPrj'];

    function LoanPrjDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, LoanPrj) {
        var vm = this;

        vm.loanPrj = entity;
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
            if (vm.loanPrj.id !== null) {
                LoanPrj.update(vm.loanPrj, onSaveSuccess, onSaveError);
            } else {
                LoanPrj.save(vm.loanPrj, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('anprjApp:loanPrjUpdate', result);
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
