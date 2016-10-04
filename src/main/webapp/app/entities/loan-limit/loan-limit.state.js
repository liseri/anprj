(function() {
    'use strict';

    angular
        .module('anprjApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('loan-limit', {
            parent: 'entity',
            url: '/loan-limit',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.loanLimit.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/loan-limit/loan-limits.html',
                    controller: 'LoanLimitController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('loanLimit');
                    $translatePartialLoader.addPart('lOANPRJTYPE');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('loan-limit-detail', {
            parent: 'entity',
            url: '/loan-limit/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.loanLimit.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/loan-limit/loan-limit-detail.html',
                    controller: 'LoanLimitDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('loanLimit');
                    $translatePartialLoader.addPart('lOANPRJTYPE');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LoanLimit', function($stateParams, LoanLimit) {
                    return LoanLimit.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'loan-limit',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('loan-limit-detail.edit', {
            parent: 'loan-limit-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-limit/loan-limit-dialog.html',
                    controller: 'LoanLimitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LoanLimit', function(LoanLimit) {
                            return LoanLimit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loan-limit.new', {
            parent: 'loan-limit',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-limit/loan-limit-dialog.html',
                    controller: 'LoanLimitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                loanType: null,
                                minLimit: null,
                                maxLimit: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('loan-limit', null, { reload: 'loan-limit' });
                }, function() {
                    $state.go('loan-limit');
                });
            }]
        })
        .state('loan-limit.edit', {
            parent: 'loan-limit',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-limit/loan-limit-dialog.html',
                    controller: 'LoanLimitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LoanLimit', function(LoanLimit) {
                            return LoanLimit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loan-limit', null, { reload: 'loan-limit' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loan-limit.delete', {
            parent: 'loan-limit',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-limit/loan-limit-delete-dialog.html',
                    controller: 'LoanLimitDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LoanLimit', function(LoanLimit) {
                            return LoanLimit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loan-limit', null, { reload: 'loan-limit' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
