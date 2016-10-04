(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('EnterpriseDetailController', EnterpriseDetailController);

    EnterpriseDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Enterprise'];

    function EnterpriseDetailController($scope, $rootScope, $stateParams, previousState, entity, Enterprise) {
        var vm = this;

        vm.enterprise = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('anprjApp:enterpriseUpdate', function(event, result) {
            vm.enterprise = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
