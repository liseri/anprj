(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LoanPrjController', LoanPrjController);

    LoanPrjController.$inject = ['$scope', '$state', 'LoanPrj', 'ParseLinks', 'AlertService'];

    function LoanPrjController ($scope, $state, LoanPrj, ParseLinks, AlertService) {
        var vm = this;

        vm.loanPrjs = [];
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
            LoanPrj.query({
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
                    vm.loanPrjs.push(data[i]);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function reset () {
            vm.page = 0;
            vm.loanPrjs = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }

        function activate(project) {
            project.activated = false;
            LoanPrj.activate(project, function(result){
                reset();
            });
        }

        function unactivate(project) {
            LoanPrj.unactivate(project, function(result){
                reset();
            });
        }
    }
})();
