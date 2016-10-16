(function() {
    'use strict';
    angular
        .module('anprjApp')
        .factory('LendPrj', LendPrj);

    LendPrj.$inject = ['$resource', 'DateUtils'];

    function LendPrj ($resource, DateUtils) {
        var resourceUrl =  'api/lend-prjs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.activateDate = DateUtils.convertLocalDateFromServer(data.activateDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.activateDate = DateUtils.convertLocalDateToServer(copy.activateDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.activateDate = DateUtils.convertLocalDateToServer(copy.activateDate);
                    return angular.toJson(copy);
                }
            },
            'activate': {
                url: 'api/lend-prjs/activate/:id',
                method: 'GET'
            },
            'unactivate': {
                url: 'api/lend-prjs/unactivate/:id',
                method: 'GET'
            }
        });
    }
})();
