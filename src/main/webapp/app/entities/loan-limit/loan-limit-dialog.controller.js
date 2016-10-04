(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LoanLimitDialogController', LoanLimitDialogController);

    LoanLimitDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'LoanLimit'];

    function LoanLimitDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, LoanLimit) {
        var vm = this;

        vm.loanLimit = entity;
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
            if (vm.loanLimit.id !== null) {
                LoanLimit.update(vm.loanLimit, onSaveSuccess, onSaveError);
            } else {
                LoanLimit.save(vm.loanLimit, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('anprjApp:loanLimitUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
