(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('RealIdentityDetailController', RealIdentityDetailController);

    RealIdentityDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RealIdentity'];

    function RealIdentityDetailController($scope, $rootScope, $stateParams, previousState, entity, RealIdentity) {
        var vm = this;

        vm.realIdentity = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('anprjApp:realIdentityUpdate', function(event, result) {
            vm.realIdentity = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
