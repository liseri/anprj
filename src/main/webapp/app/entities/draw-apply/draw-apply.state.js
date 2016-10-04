(function() {
    'use strict';

    angular
        .module('anprjApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('draw-apply', {
            parent: 'entity',
            url: '/draw-apply?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.drawApply.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/draw-apply/draw-applies.html',
                    controller: 'DrawApplyController',
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
                    $translatePartialLoader.addPart('drawApply');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('draw-apply-detail', {
            parent: 'entity',
            url: '/draw-apply/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.drawApply.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/draw-apply/draw-apply-detail.html',
                    controller: 'DrawApplyDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('drawApply');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DrawApply', function($stateParams, DrawApply) {
                    return DrawApply.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'draw-apply',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('draw-apply-detail.edit', {
            parent: 'draw-apply-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/draw-apply/draw-apply-dialog.html',
                    controller: 'DrawApplyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DrawApply', function(DrawApply) {
                            return DrawApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('draw-apply.new', {
            parent: 'draw-apply',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/draw-apply/draw-apply-dialog.html',
                    controller: 'DrawApplyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                login: null,
                                orderAmount: null,
                                actualAmount: null,
                                orderDrawDate: null,
                                canceled: false,
                                completed: false,
                                applyDate: null,
                                canceledDate: null,
                                completedDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('draw-apply', null, { reload: 'draw-apply' });
                }, function() {
                    $state.go('draw-apply');
                });
            }]
        })
        .state('draw-apply.edit', {
            parent: 'draw-apply',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/draw-apply/draw-apply-dialog.html',
                    controller: 'DrawApplyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DrawApply', function(DrawApply) {
                            return DrawApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('draw-apply', null, { reload: 'draw-apply' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('draw-apply.delete', {
            parent: 'draw-apply',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/draw-apply/draw-apply-delete-dialog.html',
                    controller: 'DrawApplyDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DrawApply', function(DrawApply) {
                            return DrawApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('draw-apply', null, { reload: 'draw-apply' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
