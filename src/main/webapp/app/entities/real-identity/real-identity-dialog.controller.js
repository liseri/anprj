(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('RealIdentityDialogController', RealIdentityDialogController);

    RealIdentityDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RealIdentity'];

    function RealIdentityDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RealIdentity) {
        var vm = this;

        vm.realIdentity = entity;
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
            if (vm.realIdentity.id !== null) {
                RealIdentity.update(vm.realIdentity, onSaveSuccess, onSaveError);
            } else {
                RealIdentity.save(vm.realIdentity, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('anprjApp:realIdentityUpdate', result);
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
