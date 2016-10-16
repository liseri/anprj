(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('DrawApplyDialogController', DrawApplyDialogController);

    DrawApplyDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DrawApply'];

    function DrawApplyDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DrawApply) {
        var vm = this;

        vm.drawApply = entity;
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
            if (vm.drawApply.id !== null) {
                DrawApply.update(vm.drawApply, onSaveSuccess, onSaveError);
            } else {
                DrawApply.save(vm.drawApply, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('anprjApp:drawApplyUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.applyDate = false;
        vm.datePickerOpenStatus.canceledDate = false;
        vm.datePickerOpenStatus.completedDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
