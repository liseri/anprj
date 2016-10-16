(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('DrawApplyDetailController', DrawApplyDetailController);

    DrawApplyDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DrawApply'];

    function DrawApplyDetailController($scope, $rootScope, $stateParams, previousState, entity, DrawApply) {
        var vm = this;

        vm.drawApply = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('anprjApp:drawApplyUpdate', function(event, result) {
            vm.drawApply = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
