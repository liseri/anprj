(function() {
    'use strict';

    angular
        .module('anprjApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('loan-prj', {
            parent: 'loan',
            url: '/loan-prj',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.loanPrj.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/loan/loan-prj/loan-prjs.html',
                    controller: 'LoanPrjController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('loanPrj');
                    $translatePartialLoader.addPart('lOANPRJTYPE');
                    $translatePartialLoader.addPart('rEPAYMENTTYPE');
                    $translatePartialLoader.addPart('durationUnit');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('loan-prj-detail', {
            parent: 'entity',
            url: '/loan-prj/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.loanPrj.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/loan/loan-prj/loan-prj-detail.html',
                    controller: 'LoanPrjDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('loanPrj');
                    $translatePartialLoader.addPart('lOANPRJTYPE');
                    $translatePartialLoader.addPart('rEPAYMENTTYPE');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LoanPrj', function($stateParams, LoanPrj) {
                    return LoanPrj.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'loan-prj',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('loan-prj-detail.edit', {
            parent: 'loan-prj-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/loan/loan-prj/loan-prj-dialog.html',
                    controller: 'LoanPrjDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LoanPrj', function(LoanPrj) {
                            return LoanPrj.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loan-prj.new', {
            parent: 'loan-prj',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/loan/loan-prj/loan-prj-dialog.html',
                    controller: 'LoanPrjDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                loanType: null,
                                maxAmount: null,
                                rate: null,
                                durationUnit: null,
                                durationNum: null,
                                replayType: null,
                                activated: false,
                                activateDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('loan-prj', null, { reload: 'loan-prj' });
                }, function() {
                    $state.go('loan-prj');
                });
            }]
        })
        .state('loan-prj.edit', {
            parent: 'loan-prj',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/loan/loan-prj/loan-prj-dialog.html',
                    controller: 'LoanPrjDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LoanPrj', function(LoanPrj) {
                            return LoanPrj.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loan-prj', null, { reload: 'loan-prj' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loan-prj.delete', {
            parent: 'loan-prj',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/loan/loan-prj/loan-prj-delete-dialog.html',
                    controller: 'LoanPrjDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LoanPrj', function(LoanPrj) {
                            return LoanPrj.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loan-prj', null, { reload: 'loan-prj' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
