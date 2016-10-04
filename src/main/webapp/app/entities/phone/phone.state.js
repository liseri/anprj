(function() {
    'use strict';

    angular
        .module('anprjApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('phone', {
            parent: 'entity',
            url: '/phone',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.phone.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/phone/phones.html',
                    controller: 'PhoneController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('phone');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('phone-detail', {
            parent: 'entity',
            url: '/phone/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.phone.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/phone/phone-detail.html',
                    controller: 'PhoneDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('phone');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Phone', function($stateParams, Phone) {
                    return Phone.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'phone',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('phone-detail.edit', {
            parent: 'phone-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/phone/phone-dialog.html',
                    controller: 'PhoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Phone', function(Phone) {
                            return Phone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('phone.new', {
            parent: 'phone',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/phone/phone-dialog.html',
                    controller: 'PhoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                login: null,
                                phone: null,
                                activated: false,
                                activateDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('phone', null, { reload: 'phone' });
                }, function() {
                    $state.go('phone');
                });
            }]
        })
        .state('phone.edit', {
            parent: 'phone',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/phone/phone-dialog.html',
                    controller: 'PhoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Phone', function(Phone) {
                            return Phone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('phone', null, { reload: 'phone' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('phone.delete', {
            parent: 'phone',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/phone/phone-delete-dialog.html',
                    controller: 'PhoneDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Phone', function(Phone) {
                            return Phone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('phone', null, { reload: 'phone' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
