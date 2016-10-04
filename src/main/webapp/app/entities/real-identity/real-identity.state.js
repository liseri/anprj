(function() {
    'use strict';

    angular
        .module('anprjApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('real-identity', {
            parent: 'entity',
            url: '/real-identity?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.realIdentity.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/real-identity/real-identities.html',
                    controller: 'RealIdentityController',
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
                    $translatePartialLoader.addPart('realIdentity');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('real-identity-detail', {
            parent: 'entity',
            url: '/real-identity/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.realIdentity.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/real-identity/real-identity-detail.html',
                    controller: 'RealIdentityDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('realIdentity');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'RealIdentity', function($stateParams, RealIdentity) {
                    return RealIdentity.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'real-identity',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('real-identity-detail.edit', {
            parent: 'real-identity-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/real-identity/real-identity-dialog.html',
                    controller: 'RealIdentityDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RealIdentity', function(RealIdentity) {
                            return RealIdentity.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('real-identity.new', {
            parent: 'real-identity',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/real-identity/real-identity-dialog.html',
                    controller: 'RealIdentityDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                login: null,
                                name: null,
                                identityNumber: null,
                                identityPicPath: null,
                                activated: false,
                                activateDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('real-identity', null, { reload: 'real-identity' });
                }, function() {
                    $state.go('real-identity');
                });
            }]
        })
        .state('real-identity.edit', {
            parent: 'real-identity',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/real-identity/real-identity-dialog.html',
                    controller: 'RealIdentityDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RealIdentity', function(RealIdentity) {
                            return RealIdentity.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('real-identity', null, { reload: 'real-identity' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('real-identity.delete', {
            parent: 'real-identity',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/real-identity/real-identity-delete-dialog.html',
                    controller: 'RealIdentityDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RealIdentity', function(RealIdentity) {
                            return RealIdentity.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('real-identity', null, { reload: 'real-identity' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
