(function() {
    'use strict';

    angular
        .module('anprjApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('lend-prj', {
            parent: 'lend',
            url: '/lend-prj',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.lendPrj.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/lend/lend-prj/lend-prjs.html',
                    controller: 'LendPrjController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('lendPrj');
                    $translatePartialLoader.addPart('rEPAYMENTTYPE');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('lend-prj-detail', {
            parent: 'entity',
            url: '/lend-prj/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.lendPrj.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/lend/lend-prj/lend-prj-detail.html',
                    controller: 'LendPrjDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('lendPrj');
                    $translatePartialLoader.addPart('rEPAYMENTTYPE');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LendPrj', function($stateParams, LendPrj) {
                    return LendPrj.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'lend-prj',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('lend-prj-detail.edit', {
            parent: 'lend-prj-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/lend/lend-prj/lend-prj-dialog.html',
                    controller: 'LendPrjDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LendPrj', function(LendPrj) {
                            return LendPrj.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('lend-prj.new', {
            parent: 'lend-prj',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/lend/lend-prj/lend-prj-dialog.html',
                    controller: 'LendPrjDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                startAmount: null,
                                rate: null,
                                durationUnit: null,
                                durationNum: null,
                                returnType: null,
                                activated: false,
                                activateDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('lend-prj', null, { reload: 'lend-prj' });
                }, function() {
                    $state.go('lend-prj');
                });
            }]
        })
        .state('lend-prj.edit', {
            parent: 'lend-prj',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/lend/lend-prj/lend-prj-dialog.html',
                    controller: 'LendPrjDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LendPrj', function(LendPrj) {
                            return LendPrj.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('lend-prj', null, { reload: 'lend-prj' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('lend-prj.delete', {
            parent: 'lend-prj',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/lend/lend-prj/lend-prj-delete-dialog.html',
                    controller: 'LendPrjDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LendPrj', function(LendPrj) {
                            return LendPrj.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('lend-prj', null, { reload: 'lend-prj' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
