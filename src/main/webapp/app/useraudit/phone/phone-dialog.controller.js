(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('PhoneDialogController', PhoneDialogController);

    PhoneDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Phone'];

    function PhoneDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Phone) {
        var vm = this;

        vm.phone = entity;
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
            if (vm.phone.id !== null) {
                Phone.update(vm.phone, onSaveSuccess, onSaveError);
            } else {
                Phone.save(vm.phone, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('anprjApp:phoneUpdate', result);
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
