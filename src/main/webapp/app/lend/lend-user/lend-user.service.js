(function() {
    'use strict';
    angular
        .module('anprjApp')
        .factory('LendUserPrj', LendUserPrj);

    LendUserPrj.$inject = ['$resource', 'DateUtils'];

    function LendUserPrj ($resource, DateUtils) {
        var resourceUrl =  'api/lend-prjs/activated/:id';

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
            }
        });
    }
})();
