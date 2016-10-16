(function() {
    'use strict';

    angular
        .module('anprjApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('enterprise', {
            parent: 'useraudit',
            url: '/enterprise?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.enterprise.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/useraudit/enterprise/enterprises.html',
                    controller: 'EnterpriseController',
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
                    $translatePartialLoader.addPart('enterprise');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('enterprise-detail', {
            parent: 'entity',
            url: '/enterprise/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.enterprise.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/useraudit/enterprise/enterprise-detail.html',
                    controller: 'EnterpriseDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('enterprise');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Enterprise', function($stateParams, Enterprise) {
                    return Enterprise.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'enterprise',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('enterprise-detail.edit', {
            parent: 'enterprise-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/useraudit/enterprise/enterprise-dialog.html',
                    controller: 'EnterpriseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Enterprise', function(Enterprise) {
                            return Enterprise.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('enterprise.new', {
            parent: 'enterprise',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/useraudit/enterprise/enterprise-dialog.html',
                    controller: 'EnterpriseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                login: null,
                                name: null,
                                address: null,
                                postcode: null,
                                phone: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('enterprise', null, { reload: 'enterprise' });
                }, function() {
                    $state.go('enterprise');
                });
            }]
        })
        .state('enterprise.edit', {
            parent: 'enterprise',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/useraudit/enterprise/enterprise-dialog.html',
                    controller: 'EnterpriseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Enterprise', function(Enterprise) {
                            return Enterprise.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('enterprise', null, { reload: 'enterprise' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('enterprise.delete', {
            parent: 'enterprise',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/useraudit/enterprise/enterprise-delete-dialog.html',
                    controller: 'EnterpriseDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Enterprise', function(Enterprise) {
                            return Enterprise.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('enterprise', null, { reload: 'enterprise' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
