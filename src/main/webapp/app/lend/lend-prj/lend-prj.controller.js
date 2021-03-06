(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LendPrjController', LendPrjController);

    LendPrjController.$inject = ['$scope', '$state', 'LendPrj', 'ParseLinks', 'AlertService'];

    function LendPrjController ($scope, $state, LendPrj, ParseLinks, AlertService) {
        var vm = this;

        vm.lendPrjs = [];
        vm.loadPage = loadPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'id';
        vm.reset = reset;
        vm.reverse = true;
        vm.activate = activate;
        vm.unactivate = unactivate;

        loadAll();

        function loadAll () {
            LendPrj.query({
                page: vm.page,
                size: 20,
                sort: sort()
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }

            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                    vm.lendPrjs.push(data[i]);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function reset () {
            vm.page = 0;
            vm.lendPrjs = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }

        function activate(project) {
            project.activated = false;
            LendPrj.activate(project, function(result){
                reset();
            });
        }

        function unactivate(project) {
            LendPrj.unactivate(project, function(result){
                reset();
            });
        }
    }
})();
