(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('LendApplyController', LendApplyController);

    LendApplyController.$inject = ['$scope', '$state', 'LendApply', 'ParseLinks', 'AlertService', 'pagingParams', 'paginationConstants'];

    function LendApplyController ($scope, $state, LendApply, ParseLinks, AlertService, pagingParams, paginationConstants) {
        var vm = this;

        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.auditPass = auditPass;
        vm.auditReject = auditReject;
        vm.lend = lend;
        vm.complete = complete;

        loadAll();

        function loadAll () {
            LendApply.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
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
                vm.queryCount = vm.totalItems;
                vm.lendApplies = data;
                vm.page = pagingParams.page;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function loadPage (page) {
            vm.page = page;
            vm.transition();
        }

        function transition () {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }

        function auditPass(lendApply) {
            LendApply.auditPass(lendApply, function(result){
                loadAll();
            });
        }
        function auditReject(lendApply) {
            LendApply.auditReject(lendApply, function(result){
                loadAll();
            });
        }
        function lend(lendApply) {
            LendApply.lend(lendApply, function(result){
                loadAll();
            });
        }
        function complete(lendApply) {
            LendApply.complete(lendApply, function(result){
                loadAll();
            });
        }
    }
})();
