(function() {
    'use strict';

    angular
        .module('anprjApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('lend-apply', {
            parent: 'lend',
            url: '/lend-apply?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.lendApply.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/lend/lend-apply/lend-applies.html',
                    controller: 'LendApplyController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('lendApply');
                    $translatePartialLoader.addPart('lendApplyStatu');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('lend-apply-detail', {
            parent: 'entity',
            url: '/lend-apply/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.lendApply.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/lend/lend-apply/lend-apply-detail.html',
                    controller: 'LendApplyDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('lendApply');
                    $translatePartialLoader.addPart('lendApplyStatu');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LendApply', function($stateParams, LendApply) {
                    return LendApply.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'lend-apply',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('lend-apply-detail.edit', {
            parent: 'lend-apply-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/lend/lend-apply/lend-apply-dialog.html',
                    controller: 'LendApplyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LendApply', function(LendApply) {
                            return LendApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('lend-apply.new', {
            parent: 'lend-apply',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/lend/lend-apply/lend-apply-dialog.html',
                    controller: 'LendApplyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                login: null,
                                lendPrjId: null,
                                amount: null,
                                lendStatu: null,
                                applyDate: null,
                                startDate: null,
                                completeDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('lend-apply', null, { reload: 'lend-apply' });
                }, function() {
                    $state.go('lend-apply');
                });
            }]
        })
        .state('lend-apply.edit', {
            parent: 'lend-apply',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/lend/lend-apply/lend-apply-dialog.html',
                    controller: 'LendApplyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LendApply', function(LendApply) {
                            return LendApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('lend-apply', null, { reload: 'lend-apply' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('lend-apply.delete', {
            parent: 'lend-apply',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/lend/lend-apply/lend-apply-delete-dialog.html',
                    controller: 'LendApplyDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LendApply', function(LendApply) {
                            return LendApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('lend-apply', null, { reload: 'lend-apply' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
