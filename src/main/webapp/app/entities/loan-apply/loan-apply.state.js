(function() {
    'use strict';

    angular
        .module('anprjApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('loan-apply', {
            parent: 'entity',
            url: '/loan-apply?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.loanApply.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/loan-apply/loan-applies.html',
                    controller: 'LoanApplyController',
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
                    $translatePartialLoader.addPart('loanApply');
                    $translatePartialLoader.addPart('loanApplyStatu');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('loan-apply-detail', {
            parent: 'entity',
            url: '/loan-apply/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.loanApply.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/loan-apply/loan-apply-detail.html',
                    controller: 'LoanApplyDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('loanApply');
                    $translatePartialLoader.addPart('loanApplyStatu');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LoanApply', function($stateParams, LoanApply) {
                    return LoanApply.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'loan-apply',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('loan-apply-detail.edit', {
            parent: 'loan-apply-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-apply/loan-apply-dialog.html',
                    controller: 'LoanApplyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LoanApply', function(LoanApply) {
                            return LoanApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loan-apply.new', {
            parent: 'loan-apply',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-apply/loan-apply-dialog.html',
                    controller: 'LoanApplyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                login: null,
                                loanPrjId: null,
                                amount: null,
                                applyStatu: null,
                                applyDate: null,
                                auditDate: null,
                                loanDate: null,
                                completeDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('loan-apply', null, { reload: 'loan-apply' });
                }, function() {
                    $state.go('loan-apply');
                });
            }]
        })
        .state('loan-apply.edit', {
            parent: 'loan-apply',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-apply/loan-apply-dialog.html',
                    controller: 'LoanApplyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LoanApply', function(LoanApply) {
                            return LoanApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loan-apply', null, { reload: 'loan-apply' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loan-apply.delete', {
            parent: 'loan-apply',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-apply/loan-apply-delete-dialog.html',
                    controller: 'LoanApplyDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LoanApply', function(LoanApply) {
                            return LoanApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loan-apply', null, { reload: 'loan-apply' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
