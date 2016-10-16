(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('PhoneController', PhoneController);

    PhoneController.$inject = ['$scope', '$state', 'Phone'];

    function PhoneController ($scope, $state, Phone) {
        var vm = this;
        
        vm.phones = [];

        loadAll();

        function loadAll() {
            Phone.query(function(result) {
                vm.phones = result;
            });
        }
    }
})();
