(function() {
    'use strict';

    angular
        .module('anprjApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('lend-user', {
            parent: 'app',
            url: '/lend-user',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.lendUserPrj.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/lend/lend-user/lend-user-prjs.html',
                    controller: 'LendUserPrjController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('lendUserPrj');
                    $translatePartialLoader.addPart('rEPAYMENTTYPE');
                    $translatePartialLoader.addPart('global');
                    $translatePartialLoader.addPart('durationUnit');
                    return $translate.refresh();
                }]
            }
        })
        .state('lend-user-prjdetail', {
            parent: 'app',
            url: '/lend-user/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'anprjApp.lendUserPrj.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/lend/lend-user/lend-user-prjdetail.html',
                    controller: 'LendUserPrjDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('lendUserPrj');
                    $translatePartialLoader.addPart('rEPAYMENTTYPE');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LendUserPrj', function($stateParams, LendUserPrj) {
                    return LendUserPrj.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'lend-user',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        });
    }

})();
